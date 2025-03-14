package co.edu.ue.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.UserRole;

public interface IUserRole extends JpaRepository<UserRole, Integer> {

    Boolean existsByrolId(int rolId);

    List<UserRole> findByuseId(int useId);
}
