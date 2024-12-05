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
		return emotionalStateJPA.save(emotionalState);
	}

	@Override
	public EmotionalState findIdEmotionalState(int id) {
		return emotionalStateJPA.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el estado emocional"));
	}

	@Override
	public List<EmotionalState> listEmotionalState() {
		return emotionalStateJPA.findAll();
	}

	@Override
	public Boolean existsEmoStaId(int useId) {
		return emotionalStateJPA.existsByemoStaId(useId);
	}

}
