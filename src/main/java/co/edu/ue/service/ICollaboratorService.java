package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.Collaborator;

public interface ICollaboratorService {

	List<Collaborator> addCollaborator(Collaborator collaborator);
	Collaborator upCollaborator(Collaborator collaborator);
	Collaborator findByIdCollaborator(int id);
	List<Collaborator> listAllCollaborators();
}
