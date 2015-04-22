package ro.agitman.pub;

import com.google.gson.Gson;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import ro.agitman.AbstractMB;
import ro.agitman.dto.CaptchaResponse;
import ro.agitman.facade.UserService;
import ro.agitman.model.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by edi on 2/25/2015.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "register", pattern = "/register", viewId = "/pages/register.jsf"),
        @URLMapping(id = "recover", pattern = "/recover", viewId = "/pages/recover.jsf"),
        @URLMapping(id = "recoverConfirm", pattern = "/recoverConfirm/" + "#{registerMB.recoverConfirmToken}", viewId = "/pages/recoverConfirm.jsf")
})
public class RegisterMB extends AbstractMB {

    private final String secret = "6Lddv_4SAAAAAAWRv2cP2rJ8IAuIMgxL5_3pnOAp";
    private final String root = "https://www.google.com/recaptcha/api/siteverify?";
    @EJB
    private UserService userService;

    private User user = new User();
    private String confirm;
    private String recoverConfirmToken;


    public String register() {
        //TODO call captcha validate
        userService.register(user);
        info("Cont create cu succes");
        return "index?faces-redirect=true";
    }

    public String recover() {
        //TODO call captcha validate
        // userService.recover(user.getEmail());
        return "index?faces-redirect=true";
    }

    public String recoverConfirm() {
        //userService.recoverConfirm(user.getEmail());
        return "/pages/login?faces-redirect=true";
    }

    private boolean validateCapthca() {
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
        url.append("&&remoteip=").append(ip);

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getRecoverConfirmToken() {
        return recoverConfirmToken;
    }

    public void setRecoverConfirmToken(String recoverConfirmToken) {
        this.recoverConfirmToken = recoverConfirmToken;
    }
}
