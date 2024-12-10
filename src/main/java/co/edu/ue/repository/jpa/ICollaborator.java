package co.edu.ue.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.ue.entity.Collaborator;
import co.edu.ue.entity.Entry;
import co.edu.ue.entity.User;

public interface ICollaborator extends JpaRepository<Collaborator, Integer>{

    Boolean existsBycolId(int colId);
    Boolean existsByUserAndEntry(User useId, Entry entId);
}
