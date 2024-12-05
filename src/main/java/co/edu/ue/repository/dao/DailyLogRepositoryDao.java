package co.edu.ue.repository.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.dto.EntryDetailsDTO;
import co.edu.ue.entity.DailyLog;
import co.edu.ue.repository.jpa.IDailyLog;

@Repository
public class DailyLogRepositoryDao implements IDailyLogRepositoryDao{

	@Autowired
	IDailyLog dailyLogJPA;
	
	@Override
	public List<DailyLog> insertDailyLog(DailyLog DailyLog) {
	
		dailyLogJPA.save(DailyLog);
		return listDailyLogs();
	}

	@Override
	public DailyLog updateDailyLog(DailyLog DailyLog) {
		return dailyLogJPA.save(DailyLog);
	}

	@Override
	public DailyLog findIdDailyLog(int id) {
		return dailyLogJPA.findById(id).orElseThrow( () -> new RuntimeException("No se encontro el registro diario"));
	}

	@Override
	public List<DailyLog> listDailyLogs() {
		return dailyLogJPA.findAll();
	}

	@Override
	public List<EntryDetailsDTO> listDailyLogsByDateOrAndCategory(int userId, LocalDate dayLogDate, Integer categoryid) {
		return dailyLogJPA.findEntryDetailsByUserAndOptionalDateAndCategory(userId, dayLogDate, categoryid);
	}

	@Override
	public List<Object[]> listfindCategoriesForEntries(int userId) {
		return dailyLogJPA.findCategoriesForEntries(userId);
	}



}
