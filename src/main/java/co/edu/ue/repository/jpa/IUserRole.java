package co.edu.ue.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.UserRole;

public interface IUserRole extends JpaRepository<UserRole, Integer> {

}
