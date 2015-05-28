package ro.agitman.servlet;

import facebook4j.FacebookException;
import ro.agitman.pages.pub.NetLoginMB;
import twitter4j.TwitterException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by AlexandruG on 5/28/2015.
 */
@WebServlet(name = "callback", urlPatterns = "/callback/*", loadOnStartup = 10)
public class CallbackServlet extends AbstractFacesServlet {


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]> map = request.getParameterMap();
        String pathInfo = request.getPathInfo();
        NetLoginMB netLogin = getManagedBean("netLogin", getFacesContext(request,response), NetLoginMB.class);

        String[] pathParts = pathInfo.split("/");

        try {
            if (pathParts.length > 1 && pathParts[1] != null){
                if (pathParts[1].contains("f") && map.containsKey("code") && map.containsKey("state")) {
                    netLogin.facebookFlow(map.get("code")[0], map.get("state")[0]);
                }
                if (pathParts[1].contains("g") && map.containsKey("code") && map.containsKey("state")) {
                    netLogin.googleFlow(map.get("code")[0], map.get("state")[0]);
                }
                if (pathParts[1].contains("t") && map.containsKey("oauth_token") && map.containsKey("oauth_verifier")) {
                    netLogin.twitterFlow(map.get("oauth_token")[0], map.get("oauth_verifier")[0]);
                }
            }
        } catch (FacebookException | TwitterException e) {
            e.printStackTrace();
        }
    }

    private void findCookies(HttpServletRequest request) {

        // Check for cookies
        Cookie[] cookies = request.getCookies();

        // Check to see if any cookies exists
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie aCookie = cookies[i];
                System.out.println("Name : " + aCookie.getName());
                System.out.println("Value: " + aCookie.getValue());
            }
        }
    }
}
