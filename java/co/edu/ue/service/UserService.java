package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.User;
import co.edu.ue.repository.dao.IUserRepositoryDao;

@Service
public class UserService implements IUserService{

	@Autowired
	IUserRepositoryDao userRepository;
	@Override
	public List<User> addUser(User user) {
		return userRepository.insertUser(user);
	}

	@Override
	public User upeUser(User User) {
		// TODO Auto-generated method stub
		return userRepository.updateUser(User);
	}

	@Override
	public User findByIdUser(int id) {
		// TODO Auto-generated method stub
		return userRepository.findIdUser(id);
	}

	@Override
	public List<User> listAllUser() {
		// TODO Auto-generated method stub
		return userRepository.listUser();
	}

	@Override
	public User findByMailUser(String mail) {
		// TODO Auto-generated method stub
		return userRepository.findMailUser(mail);
	}

	@Override
	public Boolean existByMailUser(String mail) {
		// TODO Auto-generated method stub
		return userRepository.existMailUser(mail);
	}

}
