package co.edu.ue.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import co.edu.ue.entity.Collaborator;
import co.edu.ue.entity.Entry;
import co.edu.ue.entity.User;

public interface ICollaborator extends JpaRepository<Collaborator, Integer>{

    Boolean existsBycolId(int colId);
    Boolean existsByUserAndEntry(User useId, Entry entId);

    @Query("SELECT c FROM Collaborator c JOIN c.user u JOIN u.person p WHERE c.entry.entId = :entId")
    List<Collaborator> findAllByEntry(Integer entId);
}
