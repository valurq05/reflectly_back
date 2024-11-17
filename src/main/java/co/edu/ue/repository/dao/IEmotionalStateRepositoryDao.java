package co.edu.ue.repository.dao;

import java.util.List;


import co.edu.ue.entity.EmotionalState;

public interface IEmotionalStateRepositoryDao{
	
	List<EmotionalState> insertEmotionalState(EmotionalState emotionalState);
	EmotionalState updateEmotionalState(EmotionalState emotionalState);
	EmotionalState findIdEmotionalState(int id);
	List<EmotionalState> listEmotionalState();

}
