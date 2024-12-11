package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.Collaborator;
import co.edu.ue.entity.Entry;
import co.edu.ue.entity.User;
import co.edu.ue.repository.dao.ICollaboratorRepositoryDao;

@Service
public class CollaboratorService implements ICollaboratorService {

	@Autowired
	ICollaboratorRepositoryDao collaboratorDAO;

	@Override
	public List<Collaborator> addCollaborator(Collaborator collaborator) {
		return collaboratorDAO.insertCollaborator(collaborator);
	}

	@Override
	public Collaborator upCollaborator(Collaborator collaborator) {
		return collaboratorDAO.updateCollaborator(collaborator);
	}

	@Override
	public Collaborator findByIdCollaborator(int id) {
		return collaboratorDAO.findIdCollaborator(id);
	}

	@Override
	public List<Collaborator> listAllCollaborators() {
		return collaboratorDAO.listCollaborators();
	}

	@Override
	public Boolean existsBycolId(int colId) {
		return collaboratorDAO.existsBycolId(colId);
	}

	@Override
	public Boolean existsByUserAndEntry(User useId, Entry entId) {

		return collaboratorDAO.existsByUserAndEntry(useId, entId);
	}

	@Override
	public List<Collaborator> findAllByEntry(Integer colId) {
		
		return collaboratorDAO.findAllByEntry(colId);
	}

}
