package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.EmotionalState;

public interface IEmotionalStateService {

	List<EmotionalState> addEmotionalState(EmotionalState emotionalState);
	EmotionalState upEmotionalState(EmotionalState emotionalState);
	EmotionalState findByIdEmotionalState(int id);
	List<EmotionalState> listAllEmotionalState();
	Boolean existsByEmoStaId(int useId);

}
