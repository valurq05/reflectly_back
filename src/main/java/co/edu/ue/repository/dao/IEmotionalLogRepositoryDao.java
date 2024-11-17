package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.EmotionalLog;

public interface IEmotionalLogRepositoryDao {

	List<EmotionalLog> insertEmotionalLog(EmotionalLog emotionalLog);
	EmotionalLog updateEmotionalLog(EmotionalLog emotionalLog);
	EmotionalLog findIdEmotionalLog(int id);
	List<EmotionalLog> listEmotionalLogs();
}
