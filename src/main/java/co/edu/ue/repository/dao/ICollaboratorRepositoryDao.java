package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.Collaborator;
import co.edu.ue.entity.Entry;
import co.edu.ue.entity.User;

public interface ICollaboratorRepositoryDao {

	List<Collaborator> insertCollaborator(Collaborator collaborator);
	Collaborator updateCollaborator(Collaborator collaborator);
	Collaborator findIdCollaborator(int id);
	List<Collaborator> listCollaborators();
	Boolean existsBycolId(int colId);
	Boolean existsByUserAndEntry(User useId, Entry entId);
	List<Collaborator> findAllByEntry(Integer colId);
}
