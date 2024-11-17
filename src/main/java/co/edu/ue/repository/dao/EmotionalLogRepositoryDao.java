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
		// TODO Auto-generated method stub
		emotionalLogJPA.save(emotionalLog);
		return listEmotionalLogs();
	}

	@Override
	public EmotionalLog updateEmotionalLog(EmotionalLog emotionalLog) {
		// TODO Auto-generated method stub
		return emotionalLogJPA.save(emotionalLog);
	}

	@Override
	public EmotionalLog findIdEmotionalLog(int id) {
		// TODO Auto-generated method stub
		return emotionalLogJPA.findById(id).orElse(null);
	}

	@Override
	public List<EmotionalLog> listEmotionalLogs() {
		// TODO Auto-generated method stub
		return emotionalLogJPA.findAll();
	}

}
