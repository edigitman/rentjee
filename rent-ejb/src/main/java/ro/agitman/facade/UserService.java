package ro.agitman.facade;

import ro.agitman.model.User;

import javax.ejb.Local;


@Local
public interface UserService {

	User findUserByEmail(String email);

	User register(User u);

	User create(User u);

	boolean confirm(String registerConfirmToken);

	void update(User user);

	boolean recover(String email);

	boolean recoverConfirm(String password, String recoverConfirmToken);
}