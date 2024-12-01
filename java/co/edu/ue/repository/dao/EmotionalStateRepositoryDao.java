package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.EmotionalState;
import co.edu.ue.repository.jpa.IEmotionalState;

@Repository
public class EmotionalStateRepositoryDao implements IEmotionalStateRepositoryDao{

	@Autowired
	IEmotionalState emotionalStateJPA;
	@Override
	public List<EmotionalState> insertEmotionalState(EmotionalState emotionalState) {
		emotionalStateJPA.save(emotionalState);
		return listEmotionalState();
	}

	@Override
	public EmotionalState updateEmotionalState(EmotionalState emotionalState) {
		// TODO Auto-generated method stub
		return emotionalStateJPA.save(emotionalState);
	}

	@Override
	public EmotionalState findIdEmotionalState(int id) {
		// TODO Auto-generated method stub
		return emotionalStateJPA.findById(id).orElse(null);
	}

	@Override
	public List<EmotionalState> listEmotionalState() {
		// TODO Auto-generated method stub
		return emotionalStateJPA.findAll();
	}

	@Override
	public Boolean existsEmoStaId(int useId) {
		// TODO Auto-generated method stub
		return emotionalStateJPA.existsByemoStaId(useId);
	}

}
