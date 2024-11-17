package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.Collaborator;
import co.edu.ue.repository.jpa.ICollaborator;

@Repository
public class CollaboratorRepositoryDao implements ICollaboratorRepositoryDao{

	@Autowired
	ICollaborator collaboratorJPA;
	
	@Override
	public List<Collaborator> insertCollaborator(Collaborator collaborator) {
		collaboratorJPA.save(collaborator);
		return listCollaborators();
	}

	@Override
	public Collaborator updateCollaborator(Collaborator collaborator) {
		// TODO Auto-generated method stub
		return collaboratorJPA.save(collaborator);
	}

	@Override
	public Collaborator findIdCollaborator(int id) {
		// TODO Auto-generated method stub
		return collaboratorJPA.findById(id).orElse(null);
	}

	@Override
	public List<Collaborator> listCollaborators() {
		// TODO Auto-generated method stub
		return collaboratorJPA.findAll();
	}

}
