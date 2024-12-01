package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.EmotionalState;
import co.edu.ue.repository.dao.IEmotionalStateRepositoryDao;

@Service
public class EmotionalStateService implements IEmotionalStateService{

	@Autowired
	IEmotionalStateRepositoryDao EmoStateDao;
	@Override
	public List<EmotionalState> addEmotionalState(EmotionalState emotionalState) {
		// TODO Auto-generated method stub
		return EmoStateDao.insertEmotionalState(emotionalState);
	}

	@Override
	public EmotionalState upEmotionalState(EmotionalState emotionalState) {
		// TODO Auto-generated method stub
		return EmoStateDao.updateEmotionalState(emotionalState);
	}

	@Override
	public EmotionalState findByIdEmotionalState(int id) {
		// TODO Auto-generated method stub
		return EmoStateDao.findIdEmotionalState(id);
	}

	@Override
	public List<EmotionalState> listAllEmotionalState() {
		// TODO Auto-generated method stub
		return EmoStateDao.listEmotionalState();
	}

	@Override
	public Boolean existsByEmoStaId(int useId) {
		// TODO Auto-generated method stub
		return EmoStateDao.existsEmoStaId(useId);
	}

}
