package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.DailyLog;

public interface IDailyLogService {

	List<DailyLog> addDailyLog(DailyLog DailyLog);
	DailyLog upDailyLog(DailyLog DailyLog);
	DailyLog findByIdDailyLog(int id);
	List<DailyLog> listAllDailyLogs();
}
