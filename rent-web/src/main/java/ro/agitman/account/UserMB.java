package ro.agitman.account;

import ro.agitman.AbstractMB;
import ro.agitman.facade.UserService;
import ro.agitman.model.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;

/**
 * Created by edi on 2/11/2015.
 */
@ManagedBean
@SessionScoped
public class UserMB extends AbstractMB {

    private User user;
    private String username;
    private String password;

    @EJB
    private UserService userService;

    public void login() {
        try {
            //Login via the Servlet Context
            getRequest().login(username, password);

            redirect("user/home");

        } catch (ServletException e) {
            error("Email sau parola gresita");
            e.printStackTrace();
        }
    }

    public void update() {

//        userService.update();
    }

    public User getUser() {
        if (user == null) {
            String userEmail = getExternalContext().getUserPrincipal().getName();
            user = userService.findUserByEmail(userEmail);
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
        return "/pages/index?faces-redirect=true";
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
}
