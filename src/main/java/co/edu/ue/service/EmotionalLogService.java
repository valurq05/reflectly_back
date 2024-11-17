package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.EmotionalLog;

import co.edu.ue.repository.dao.IEmotionalLogRepositoryDao;

@Service
public class EmotionalLogService implements IEmotionalLogService{

	@Autowired
	IEmotionalLogRepositoryDao EmoLogDAO;
	
	@Override
	public List<EmotionalLog> addEmotionalLog(EmotionalLog emotionalLog) {
		// TODO Auto-generated method stub
		return EmoLogDAO.insertEmotionalLog(emotionalLog);
	}

	@Override
	public EmotionalLog upEmotionalLog(EmotionalLog emotionalLog) {
		// TODO Auto-generated method stub
		return EmoLogDAO.updateEmotionalLog(emotionalLog);
	}

	@Override
	public EmotionalLog findByIdEmotionalLog(int id) {
		// TODO Auto-generated method stub
		return EmoLogDAO.findIdEmotionalLog(id);
	}

	@Override
	public List<EmotionalLog> listAllEmotionalLogs() {
		// TODO Auto-generated method stub
		return EmoLogDAO.listEmotionalLogs();
	}


}
