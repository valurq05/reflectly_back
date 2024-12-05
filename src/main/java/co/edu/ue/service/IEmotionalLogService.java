package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.EmotionalLog;

public interface IEmotionalLogService{

	List<EmotionalLog> addEmotionalLog(EmotionalLog emotionalLog);
	EmotionalLog upEmotionalLog(EmotionalLog emotionalLog);
	EmotionalLog findByIdEmotionalLog(int id);
	List<EmotionalLog> listAllEmotionalLogs();
	Boolean existsByemoLogId(int emoLogId);
}
