package ro.agitman.account;

import ro.agitman.facade.UserService;
import ro.agitman.model.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by edi on 2/11/2015.
 */
@ManagedBean
@SessionScoped
public class UserMB implements Serializable {

    private User user;

    @EJB
    private UserService userFacade;

    public User getUser(){
        if(user == null){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String userEmail = context.getUserPrincipal().getName();

            user = userFacade.findUserByEmail(userEmail);
        }

        return user;
    }

    public boolean isUserLogged(){
        return getRequest().getUserPrincipal() != null;
    }

    public boolean isUserAdmin(){
        return getRequest().isUserInRole("ADMIN");
    }

    public String logOut(){
        getRequest().getSession().invalidate();
        return "logout";
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
