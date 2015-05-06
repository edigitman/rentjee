package ro.agitman.account;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.agitman.AbstractMB;
import ro.agitman.facade.UserService;
import ro.agitman.model.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;
import java.security.Principal;

/**
 * Created by edi on 2/11/2015.
 */
@ManagedBean
@SessionScoped
@URLMappings(mappings = {
        @URLMapping(id = "settings", pattern = "/u/settings", viewId = "/pages/user/setari.xhtml?faces-redirect=true")
})
public class UserMB extends AbstractMB {

    private static Logger log = LoggerFactory.getLogger(UserMB.class);

    private User user;
    private String username;
    private String password;
    private String newPassword;

    @EJB
    private UserService userService;

    public void login() {
        try {
            //Login via the Servlet Context
            getRequest().login(username, password);

            redirectPretty("home");

        } catch (ServletException e) {
            error("Email sau parola gresita");
            log.error("Error on login for user [{}] and pwd [{}], error [{}]", new Object[]{username, password, e.getMessage()});
            e.printStackTrace();
        }
    }

    public void update() {
        if (password != null && !password.isEmpty()) {
            if (newPassword != null) {
                user.setRegToken(newPassword);
            }
            boolean ok = userService.update(user, password, newPassword != null);

            if(ok){
                info("Informatii modificate cu succes");
            }else {
                error("Parola incorecta");
            }
        } else {
            error("Parola veche obligatorie pentru a salva modificarile");
        }
        user = userService.findUserByEmail(user.getEmail());
    }

    public User getUser() {

        if (user == null) {
            Principal principal = getExternalContext().getUserPrincipal();
            if (principal != null) {
                String userEmail = principal.getName();
                user = userService.findUserByEmail(userEmail);
            }
        }
        return user;
    }

    public boolean isUserLogged() {
        return getRequest().getUserPrincipal() != null;
    }

    public boolean isUserAdmin() {
        return getRequest().isUserInRole("ADMIN");
    }

    public String logOut() {
        getRequest().getSession(false).invalidate();
        return "pretty:index";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
