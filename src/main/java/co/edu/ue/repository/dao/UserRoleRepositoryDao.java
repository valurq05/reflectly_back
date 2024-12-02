package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.UserRole;
import co.edu.ue.repository.jpa.IUserRole;

@Repository
public class UserRoleRepositoryDao implements IUserRoleRepositoryDao{

	@Autowired
	IUserRole userRoleJPA;
	@Override
	public List<UserRole> insertUserRole(UserRole UserRole) {
		userRoleJPA.save(UserRole);
		return listUserRoles();
	}

	@Override
	public UserRole updateUserRole(UserRole UserRole) {
		
		return userRoleJPA.save(UserRole);
	}

	@Override
	public UserRole findIdUserRole(int id) {
		// TODO Auto-generated method stub
		return userRoleJPA.findById(id).orElse(null);
	}

	@Override
	public List<UserRole> listUserRoles() {
		// TODO Auto-generated method stub
		return userRoleJPA.findAll();
	}

}
