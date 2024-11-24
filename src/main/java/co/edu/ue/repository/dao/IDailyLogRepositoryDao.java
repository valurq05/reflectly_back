package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.DailyLog;

public interface IDailyLogRepositoryDao {

	List<DailyLog> insertDailyLog(DailyLog DailyLog);
	DailyLog updateDailyLog(DailyLog DailyLog);
	DailyLog findIdDailyLog(int id);
	List<DailyLog> listDailyLogs();
}
