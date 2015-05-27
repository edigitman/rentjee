package ro.agitman.pub;

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
import com.google.gson.Gson;
import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import ro.agitman.AbstractMB;
import ro.agitman.dto.FBValidateTokenResponse;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by AlexandruG on 5/27/2015.
 */
@ManagedBean(name = "netLogin")
@SessionScoped
@URLMappings(mappings = {
        @URLMapping(id = "callbackF", pattern = "/callback/f/#{netLogin.callcback}", viewId = "/pages/loginNet.xhtml?faces-redirect=true"),
        @URLMapping(id = "callbackG", pattern = "/callback/g/#{netLogin.callcback}", viewId = "/pages/loginNet.xhtml?faces-redirect=true"),
        @URLMapping(id = "callbackT", pattern = "/callback/t/#{netLogin.callcback}", viewId = "/pages/loginNet.xhtml?faces-redirect=true")
})
public class NetLoginMB extends AbstractMB {

    private String callcback;

    //Please provide a value for the CLIENT_ID constant before proceeding, set this up at https://code.google.com/apis/console/
    private static final String CLIENT_ID = "110601991148-k98gmuuci6jrkud13rlv7hfek6av9rmg.apps.googleusercontent.com";
    //Please provide a value for the CLIENT_SECRET constant before proceeding, set this up at https://code.google.com/apis/console/
    private static final String CLIENT_SECRET = "72gGioXxD0YrF6bTkNyY5reK";

    //Callback URI that google will redirect to after successful authentication
    private static final String CALLBACK_URI = "http://lachirie.ro/callback/g/";

    private String stateToken;

    // start google authentication constants
    private static final Collection<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email".split(";"));
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    // end google authentication constants

    private GoogleAuthorizationCodeFlow flow;
    private Twitter twitter;
    private RequestToken requestToken = null;

    @PostConstruct
    public void init(){
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPE).build();
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("ao9wePfdfd5DpcDK2eNiM85dx", "h7EjOYcmDQ8jeFxphLQqXuWVnd0EddnOfdx1pvBCCmpE35nKUV");
    }

    @URLAction(onPostback = false)
    public void load() throws TwitterException, IOException {
        String viewId = PrettyContext.getCurrentInstance().getCurrentMapping().getId();
        generateStateToken();

        if ("callbackF".equals(viewId)) {
            facebookFlow();
        }

        if ("callbackG".equals(viewId)) {
            googleFlow();
        }

        if ("callbackT".equals(viewId)) {
            twitterFlow();
        }
    }

    /**
     * ================ FACEBOOK FLOW
     */
    private void facebookFlow() {
        //todo get token
        String token = splitQuery(callcback).get("token").get(0);
        //todo validate token
        FBValidateTokenResponse response = sendFacebookTokenValidation(token);
        response.getIsValid();
        response.getApplication();

        //todo get user details, email

        //todo create or login user, put token in session
        //todo create user with email and token as password

    }

    // HTTP GET request
    private FBValidateTokenResponse sendFacebookTokenValidation(String token) {
        StringBuilder url = new StringBuilder("http://graph.facebook.com/debug_token?");
        url.append("access_token=").append("1078929275454685|tqQ_MlAGX2aMEHNqTJ0nIBx-DkU");
        url.append("&input_token=").append(token);

        try {
            URL obj = new URL(url.toString());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            if (con.getResponseCode() != 200) {
                return null;
            }
            FBValidateTokenResponse objParsed;
            try (InputStreamReader isr = new InputStreamReader(con.getInputStream())) {
                final Gson gson = new Gson();
                final BufferedReader reader = new BufferedReader(isr);
                objParsed = gson.fromJson(reader, FBValidateTokenResponse.class);
            }
            return objParsed;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * ================ GOOGLE FLOW
     */

    //Generates a secure state token
    private void generateStateToken() {
        SecureRandom sr1 = new SecureRandom();
        stateToken = "google;" + sr1.nextInt();
    }

    //Builds a login URL based on client ID, secret, callback URI, and scope
    public String buildGoogleLoginUrl() {
        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        return url.setRedirectUri(CALLBACK_URI).setState(stateToken).build();
    }


    private void googleFlow() {

    }


    /**
     * Expects an Authentication Code, and makes an authenticated request for the user's profile information
     *
     * @param authCode authentication code provided by google
     * @return JSON formatted user profile information
     */
    public String getUserInfoJson(final String authCode) throws IOException {

        final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(CALLBACK_URI).execute();
        final Credential credential = flow.createAndStoreCredential(response, null);
        final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
        // Make an authenticated request
        final GenericUrl url = new GenericUrl(USER_INFO_URL);
        final HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().setContentType("application/json");
        final String jsonIdentity = request.execute().parseAsString();

        return jsonIdentity;
    }

    /**
     * ================ TWITTER FLOW
     */

    private void twitterFlow() throws TwitterException, IOException {
// The factory instance is re-useable and thread safe.
        AccessToken accessToken = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String pin = splitQuery(callcback).get("token").get(0);
        if(requestToken!=null) {
            try {
                if (pin.length() > 0) {
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                } else {
                    accessToken = twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if (401 == te.getStatusCode()) {
                    System.out.println("Unable to get the access token.");
                } else {
                    te.printStackTrace();
                }
            }
        }
        //persist to the accessToken for future reference.
        storeAccessToken(twitter.verifyCredentials().getId(), accessToken);
    }

    //Builds a login URL based on client ID, secret, callback URI, and scope
    public String buildTwitterLoginUrl() throws TwitterException {
        if (requestToken == null) {
            requestToken = twitter.getOAuthRequestToken();
        }
        return requestToken.getAuthorizationURL();
    }

    private void storeAccessToken(long useId, AccessToken accessToken) {
        //store accessToken.getToken()
        //store accessToken.getTokenSecret()
    }

    //=============== HELP METHODS
    private void findCookies() {

        // Check for cookies
        Cookie[] cookies = getRequest().getCookies();

        // Check to see if any cookies exists
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie aCookie = cookies[i];
                System.out.println("Name : " + aCookie.getName());
                System.out.println("Value: " + aCookie.getValue());
            }
        }
    }

    private Map<String, List<String>> splitQuery(String url) {
        final Map<String, List<String>> queryPairs = new LinkedHashMap<>();
        final String[] pairs = url.split("&");
        try {
            for (String pair : pairs) {
                final int idx = pair.indexOf("=");
                final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
                if (!queryPairs.containsKey(key)) {
                    queryPairs.put(key, new LinkedList<String>());
                }
                final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                queryPairs.get(key).add(value);
            }
        } catch (UnsupportedEncodingException e) {
            return queryPairs;
        }
        return queryPairs;
    }


    public String getCallcback() {
        return callcback;
    }

    public void setCallcback(String callcback) {
        this.callcback = callcback;
    }


}
