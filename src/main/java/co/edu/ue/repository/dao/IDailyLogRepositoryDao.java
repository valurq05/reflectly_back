package co.edu.ue.repository.dao;

import java.time.LocalDate;
import java.util.List;

import co.edu.ue.dto.EntryDetailsDTO;
import co.edu.ue.entity.DailyLog;

public interface IDailyLogRepositoryDao {

	DailyLog insertDailyLog(DailyLog DailyLog);
	DailyLog updateDailyLog(DailyLog DailyLog);
	DailyLog findIdDailyLog(int id);
	List<DailyLog> listDailyLogs();
	List<EntryDetailsDTO> listDailyLogsByDateOrAndCategory(int userId,LocalDate dayLogDate, Integer categoryid);
	List<Object[]>listfindCategoriesForEntries(int userId);
	List<Object[]>listfindImagesForEntries(int userId);
}
