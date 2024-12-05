package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.EmotionalLog;
import co.edu.ue.repository.jpa.IEmotionalLog;

@Repository
public class EmotionalLogRepositoryDao implements IEmotionalLogRepositoryDao{

	@Autowired
	IEmotionalLog emotionalLogJPA;
	@Override
	public List<EmotionalLog> insertEmotionalLog(EmotionalLog emotionalLog) {
		emotionalLogJPA.save(emotionalLog);
		return listEmotionalLogs();
	}

	@Override
	public EmotionalLog updateEmotionalLog(EmotionalLog emotionalLog) {
		return emotionalLogJPA.save(emotionalLog);
	}

	@Override
	public EmotionalLog findIdEmotionalLog(int id) {
		return emotionalLogJPA.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el registro emocional"));
	}

	@Override
	public List<EmotionalLog> listEmotionalLogs() {
		return emotionalLogJPA.findAll();
	}

	@Override
	public Boolean existsByemoLogId(int emoLogId) {
		return emotionalLogJPA.existsByemoLogId(emoLogId);
	}

}
