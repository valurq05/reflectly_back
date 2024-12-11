package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.Collaborator;
import co.edu.ue.entity.Entry;
import co.edu.ue.entity.User;
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
		
		return collaboratorJPA.save(collaborator);
	}

	@Override
	public Collaborator findIdCollaborator(int id) {
		
		return collaboratorJPA.findById(id).orElseThrow( () -> new RuntimeException("No se encontro el colaborador"));
	}

	@Override
	public List<Collaborator> listCollaborators() {
		
		return collaboratorJPA.findAll();
	}

	@Override
	public Boolean existsBycolId(int colId) {
		
		return collaboratorJPA.existsBycolId(colId);
	}

	@Override
	public Boolean existsByUserAndEntry(User useId, Entry entId) {
		
		return collaboratorJPA.existsByUserAndEntry(useId, entId);
	}

	@Override
	public List<Collaborator> findAllByEntry(Integer colId) {
		
		return collaboratorJPA.findAllByEntry(colId);
	}

}
