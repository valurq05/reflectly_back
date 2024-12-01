package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.User;

public interface IUserService {

	List<User> addUser(User user);
	User upeUser(User User);
	User findByIdUser(int id);
	List<User> listAllUser();
	User findByMailUser(String mail);
	Boolean existByMailUser(String mail);
}
