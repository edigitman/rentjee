package ro.agitman.pub;

import ro.agitman.AbstractMB;
import ro.agitman.facade.UserService;
import ro.agitman.model.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by edi on 2/25/2015.
 */
@ManagedBean
@ViewScoped
public class RegisterMB extends AbstractMB {

    @EJB
    private UserService userService;

    private User user = new User();
    private String confirm;

    public String register() {
        userService.register(user);
        info("Cont create cu succes");
        return "index?faces-redirect=true";
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
}
