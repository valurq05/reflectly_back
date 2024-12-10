package co.edu.ue.service;

import java.time.LocalDate;

import java.util.List;

import co.edu.ue.dto.EntryDetailsDTO;
import co.edu.ue.entity.DailyLog;

public interface IDailyLogService {

	DailyLog addDailyLog(DailyLog DailyLog);
	DailyLog upDailyLog(DailyLog DailyLog);
	DailyLog findByIdDailyLog(int id);
	List<DailyLog> listAllDailyLogs();
	List<EntryDetailsDTO> listAllDailyLogsByDateOrAndCategory(int userId,LocalDate dayLogDate, Integer categoryid);

	


}
