package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.UserRole;

public interface IUserRoleRepositoryDao{

	List<UserRole> insertUserRole(UserRole UserRole);
	UserRole updateUserRole(UserRole UserRole);
	UserRole findIdUserRole(int id);
	List<UserRole> listUserRoles();
	Boolean existsUseRolId(int useRolId);
}
