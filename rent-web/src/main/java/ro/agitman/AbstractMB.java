package ro.agitman;

import com.google.gson.Gson;
import ro.agitman.dto.CaptchaResponse;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by edi on 2/25/2015.
 */
public abstract class AbstractMB implements Serializable {

    private final String secret = "6Lddv_4SAAAAAAWRv2cP2rJ8IAuIMgxL5_3pnOAp";
    private final String root = "https://www.google.com/recaptcha/api/siteverify?";

    private ResourceBundle bundle;

    public void info(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public void error(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

    public void errorPersistRedirect(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public ExternalContext getExternalContext(){
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public void redirect(String path) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, path + "?faces-redirect=true");
    }

    public  void redirectPretty(String viewId){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "pretty:" + viewId);
    }

    public ResourceBundle getBundle() {
        if (bundle == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            bundle = context.getApplication().getResourceBundle(context, "msgs");
        }
        return bundle;
    }

    public String getValue(String key) {

        String result = null;
        try {
            result = getBundle().getString(key);
        } catch (MissingResourceException e) {
            result = "???" + key + "??? not found";
        }
        return result;
    }

    protected boolean validateCapthca() {
        HttpServletRequest req = getRequest();
        String captchaResponse = req.getParameter("g-recaptcha-response");
        //is client behind something?
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }

        try {
            return sendGet(captchaResponse, ipAddress);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // HTTP GET request
    private boolean sendGet(String response, String ip) throws Exception {
        CaptchaResponse objParsed = null;
        StringBuilder url = new StringBuilder(root);
        url.append("secret=").append(secret);
        url.append("&response=").append(response);
        url.append("&remoteip=").append(ip);

        URL obj = new URL(url.toString());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) {
            return false;
        }

        try (InputStreamReader isr = new InputStreamReader(con.getInputStream())) {
            final Gson gson = new Gson();
            final BufferedReader reader = new BufferedReader(isr);
            objParsed = gson.fromJson(reader, CaptchaResponse.class);
        }

        return objParsed != null && Boolean.valueOf(objParsed.getSuccess());
    }

}
