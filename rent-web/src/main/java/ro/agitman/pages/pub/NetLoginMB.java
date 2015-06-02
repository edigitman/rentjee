package ro.agitman.pages.pub;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import ro.agitman.AbstractMB;
import ro.agitman.dto.NetTypeEnum;
import ro.agitman.facade.UserService;
import ro.agitman.model.NetUser;
import ro.agitman.model.User;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by AlexandruG on 5/27/2015.
 */
@ManagedBean(name = "netLogin", eager = true)
@SessionScoped
public class NetLoginMB extends AbstractMB {

    /**
     * GOOGLE CONSTANTS
     */
    //Please provide a value for the CLIENT_ID constant before proceeding, set this up at https://code.google.com/apis/console/
    private static final String GOOGLE_CLIENT_ID = "110601991148-k98gmuuci6jrkud13rlv7hfek6av9rmg.apps.googleusercontent.com";
    //Please provide a value for the CLIENT_SECRET constant before proceeding, set this up at https://code.google.com/apis/console/
    private static final String GOOGLE_CLIENT_SECRET = "72gGioXxD0YrF6bTkNyY5reK";
    //Callback URI that google will redirect to after successful authentication
    private static final String GOOGLE_CALLBACK_URI = "http://lachirie.ro/callback/g/";
    // start google authentication constants
    private static final Collection<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email".split(";"));
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    // end google authentication constants

    /**
     * TWITTER CONSTANTS
     */
    private static final String TWITTER_CLIENT_ID = "ao9wePfdfd5DpcDK2eNiM85dx";
    private static final String TWITTER_CLIENT_SECRET = "h7EjOYcmDQ8jeFxphLQqXuWVnd0EddnOfdx1pvBCCmpE35nKUV";
    private static final String TWITTER_CALLBACK_URI = "http://lachirie.ro/callback/t/";

    /**
     * FACEBOOK CONSTANTS
     */
    private static final String FACEBOOK_CLIENT_TOKEN = "6871c65216ce1479b5ed96048c7e3c11";
    private static final String FACEBOOK_APP_ID = "1078929275454685";
    private static final String FACEBOOK_APP_SECRET = "649624dada428322177646ade99ab56b";
    private static final String FACEBOOK_CALLBACK_URI = "http://lachirie.ro/callback/f/";

    private GoogleAuthorizationCodeFlow google;
    private Twitter twitter;
    private Facebook facebook;

    private String facebookStateToken = null;
    private String googleStateToken = null;
    private RequestToken twitterRequestToken = null;

    @EJB
    private UserService userService;

    @PostConstruct
    public void init() {
        google = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET, SCOPE).build();
        twitter = TwitterFactory.getSingleton();
        facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(FACEBOOK_APP_ID, FACEBOOK_APP_SECRET);
        facebook.setOAuthPermissions("public_profile,email");
    }

    public String getAuthRequestLink(NetTypeEnum netTypeEnum) throws TwitterException {
        SecureRandom sr1 = new SecureRandom();
        switch (netTypeEnum) {
            case FACEBOOK:
                facebookStateToken = "facebook;" + sr1.nextInt();
                return facebook.getOAuthAuthorizationURL(FACEBOOK_CALLBACK_URI, facebookStateToken);
            case GOOGLE:
                googleStateToken = "google;" + sr1.nextInt();
                final GoogleAuthorizationCodeRequestUrl urlGoogle = google.newAuthorizationUrl();
                return urlGoogle.setRedirectUri(GOOGLE_CALLBACK_URI).setState(googleStateToken).build();
            case TWITTER:
                try {
                    if(twitterRequestToken == null){
                        twitter.setOAuthConsumer(TWITTER_CLIENT_ID, TWITTER_CLIENT_SECRET);
                        twitterRequestToken = twitter.getOAuthRequestToken(TWITTER_CALLBACK_URI);
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                return requestToken.getAuthorizationURL();
        }
        return "";
    }


    /**
     * ================ FACEBOOK FLOW
     */
    public void facebookFlow(String oauthCode, final String state) throws FacebookException, ServletException {
        if(facebookStateToken==null || !facebookStateToken.equals(state)){
            //TODO invalid request
            redirectPretty("home");
        }

        facebook4j.auth.AccessToken accessToken = facebook.getOAuthAccessToken(oauthCode);

        registerLogin(NetTypeEnum.FACEBOOK, facebook.getId(), accessToken.getToken(), accessToken.getExpires(), facebook.getName());
    }

    /**
     * ================ GOOGLE FLOW
     */

    public void googleFlow(final String authCode, final String state) throws IOException, ServletException {
        if(googleStateToken==null || !googleStateToken.equals(state)){
            //TODO invalid request
            redirectPretty("home");
        }

        final GoogleTokenResponse response = google.newTokenRequest(authCode).setRedirectUri(GOOGLE_CALLBACK_URI).execute();
        final Credential credential = google.createAndStoreCredential(response, null);
        final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
        // Make an authenticated request
        final GenericUrl url = new GenericUrl(USER_INFO_URL);
        final HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().setContentType("application/json");
        final String jsonIdentity = request.execute().parseAsString();

        Gson gson=new Gson();
        Map<String,String> map=new HashMap<String,String>();
        map=(Map<String,String>) gson.fromJson(jsonIdentity, map.getClass());
        /**
         * "id": "xx",
         * "name": "xx",
         * "given_name": "xx",
         * "family_name": "xx",
         * "link": "xx",
         * "picture": "xx",
         * "gender": "xx",
         * "locale": "xx"
         */
        registerLogin(NetTypeEnum.GOOGLE, map.get("id"), credential.getAccessToken(), credential.getExpirationTimeMilliseconds(), map.get("name"));

    }

    /**
     * ================ TWITTER FLOW
     */

    public void twitterFlow(String token, String verifier) throws TwitterException, IOException {
        AccessToken accessToken = null;

        try {
            if (token.length() > 0 && twitterRequestToken != null) {
                accessToken = twitter.getOAuthAccessToken(twitterRequestToken, token);
            } else {
                accessToken = twitter.getOAuthAccessToken();
            }

            registerLogin(NetTypeEnum.TWITTER, twitter.getId(), accessToken.getToken(), 0L, twitter.getId());

        } catch (TwitterException te) {
            if (401 == te.getStatusCode()) {
                System.out.println("Unable to get the access token.");
            } else {
                te.printStackTrace();
            }
        }
    }

    //=============== HELP METHODS

    private void registerLogin(NetTypeEnum netType, final String userId, final String token, final Long exp, final String name) throws ServletException {
        User user = null;
        NetUser netUser;
        user = userService.findUserByEmail(userId);
        //account already exists
        if (user != null) {
            netUser = user.getNetUser();
            //users exists with a valid access token
            if (netUser != null && netUser.getToken().equals(token)) {
                getRequest().login(user.getEmail(), "netUser");
                redirectPretty("home");
            } else {
                //TODO renew token
                switch(netType){
                    case FACEBOOK:
                        renewFacebookToken();
                        break;
                    case GOOGLE:
                        renewGoogleToken();
                        break;
                    case TWITTER:
                        renewTwitterToken();
                        break;
                }
            }
        } else {
            user = new User();
            netUser = new NetUser();
            user.setNetUser(netUser);
            netUser.setNetTypeEnum(netType);
            netUser.setTokenExp(exp);
            netUser.setToken(token);
            user.setEmail(userId);
            user.setName(name);
            user.setConfirmedBl(true);
            user.setPassword("netUser");

            userService.register(user);
            getRequest().login(user.getEmail(), user.getPassword());
            redirectPretty("home");
        }
    }
}
