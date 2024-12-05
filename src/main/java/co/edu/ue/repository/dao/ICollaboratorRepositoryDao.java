package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.Collaborator;

public interface ICollaboratorRepositoryDao {

	List<Collaborator> insertCollaborator(Collaborator collaborator);
	Collaborator updateCollaborator(Collaborator collaborator);
	Collaborator findIdCollaborator(int id);
	List<Collaborator> listCollaborators();
	Boolean existsBycolId(int colId);
}
