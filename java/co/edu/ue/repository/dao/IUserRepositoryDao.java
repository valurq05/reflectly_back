package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.User;

public interface IUserRepositoryDao {

	List<User> insertUser(User user);
	User updateUser(User User);
	User findIdUser(int id);
	List<User> listUser();
	User findMailUser(String mail);
	Boolean existMailUser(String mail);
	Boolean existIdUser(int id);
}
