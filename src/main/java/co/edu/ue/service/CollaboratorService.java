package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.Collaborator;
import co.edu.ue.repository.dao.ICollaboratorRepositoryDao;

@Service
public class CollaboratorService implements ICollaboratorService {

	@Autowired
	ICollaboratorRepositoryDao collaboratorDAO;
	@Override
	public List<Collaborator> addCollaborator(Collaborator collaborator) {
		// TODO Auto-generated method stub
		return collaboratorDAO.insertCollaborator(collaborator);
	}

	@Override
	public Collaborator upCollaborator(Collaborator collaborator) {
		// TODO Auto-generated method stub
		return collaboratorDAO.updateCollaborator(collaborator);
	}

	@Override
	public Collaborator findByIdCollaborator(int id) {
		// TODO Auto-generated method stub
		return collaboratorDAO.findIdCollaborator(id);
	}

	@Override
	public List<Collaborator> listAllCollaborators() {
		// TODO Auto-generated method stub
		return collaboratorDAO.listCollaborators();
	}

}
