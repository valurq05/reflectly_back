package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.UserRole;

public interface IUserRoleService {

	List<UserRole> addUserRole(UserRole UserRole);
	UserRole upUserRole(UserRole UserRole);
	UserRole findByIdUserRole(int id);
	List<UserRole> listAllUserRoles();
	Boolean existsByuseRolId(int useRolId);
	List<UserRole> findByUseId(int useId);
}
