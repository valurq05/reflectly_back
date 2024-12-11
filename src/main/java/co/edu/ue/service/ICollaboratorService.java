package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.Collaborator;
import co.edu.ue.entity.Entry;
import co.edu.ue.entity.User;

public interface ICollaboratorService {

	List<Collaborator> addCollaborator(Collaborator collaborator);

	Collaborator upCollaborator(Collaborator collaborator);

	Collaborator findByIdCollaborator(int id);

	List<Collaborator> listAllCollaborators();

	Boolean existsBycolId(int colId);

	Boolean existsByUserAndEntry(User useId, Entry entId);

	List<Collaborator> findAllByEntry(Integer colId);

}
