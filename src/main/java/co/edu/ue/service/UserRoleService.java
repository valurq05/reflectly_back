package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.UserRole;
import co.edu.ue.repository.dao.IUserRoleRepositoryDao;

@Service
public class UserRoleService implements IUserRoleService{

	@Autowired
	IUserRoleRepositoryDao userRoleDAO;
	@Override
	public List<UserRole> addUserRole(UserRole UserRole) {
		return userRoleDAO.insertUserRole(UserRole);
	}

	@Override
	public UserRole upUserRole(UserRole UserRole) {
		return userRoleDAO.updateUserRole(UserRole);
	}

	@Override
	public UserRole findByIdUserRole(int id) {
		return userRoleDAO.findIdUserRole(id);
	}

	@Override
	public List<UserRole> listAllUserRoles() {
		return userRoleDAO.listUserRoles();
	}

	@Override
	public Boolean existsByuseRolId(int useRolId) {
		return userRoleDAO.existsUseRolId(useRolId);
	}

}
