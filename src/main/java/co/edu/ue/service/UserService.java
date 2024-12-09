package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.Person;
import co.edu.ue.entity.User;
import co.edu.ue.entity.UserRole;
import co.edu.ue.repository.dao.IUserRepositoryDao;

@Service
public class UserService implements IUserService{

	@Autowired
	IUserRepositoryDao userRepository;
    @Autowired
    IPersonService personService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    IUserRoleService userRoleService;
	@Override
	public User addUser(User user) {
		Person person = user.getPerson();
        personService.addPerson(person);
        String defaultImagePath = "/images/GdXyg8gWgAAQmW1.jpg";
        person.setPerPhoto(defaultImagePath);
        Person lastPerson = personService.findByIdPerson(person.getPerId());
        user.setPerson(lastPerson);

        String encryptedPassword = passwordEncoder.encode(user.getUsePassword());
        user.setUsePassword(encryptedPassword);
        userRepository.insertUser(user);
        User lastUser = userRepository.findIdUser(user.getUseId());
        System.out.println(lastUser);
        UserRole newUserRole = new UserRole();
        newUserRole.setUseId(lastUser.getUseId());
        newUserRole.setRolId(2);
        userRoleService.addUserRole(newUserRole);
		
		return lastUser;
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

	@Override
	public Boolean existByIdUser(int id) {
		// TODO Auto-generated method stub
		return userRepository.existIdUser(id);
	}

}
