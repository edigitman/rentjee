package ro.agitman.facade;

import ro.agitman.model.User;

import javax.ejb.Local;


@Local
public interface UserService {

	User findUserByEmail(String email);

	User register(User u);

	User registerNetUser(User u);

	User create(User u);

	boolean confirm(String registerConfirmToken);

    /**
     * Updates a user information, validates it's password
     * @param user - user to be updated
     * @param confirmPwd - old password to verify
     * @param withPassword - change also the password (it's in the users token)
     * @return true if account updated, false if passwords don't match
     */
	boolean update(User user, String confirmPwd, Boolean withPassword);

	boolean recover(String email);

	boolean recoverConfirm(String password, String recoverConfirmToken);
}