package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.DailyLog;
import co.edu.ue.repository.dao.IDailyLogRepositoryDao;

@Service
public class DailyLogService implements IDailyLogService{

	@Autowired
	IDailyLogRepositoryDao dailyLogDAO;
	
	@Override
	public List<DailyLog> addDailyLog(DailyLog DailyLog) {
		// TODO Auto-generated method stub
		return dailyLogDAO.insertDailyLog(DailyLog);
	}

	@Override
	public DailyLog upDailyLog(DailyLog DailyLog) {
		// TODO Auto-generated method stub
		return dailyLogDAO.updateDailyLog(DailyLog);
	}

	@Override
	public DailyLog findByIdDailyLog(int id) {
		// TODO Auto-generated method stub
		return dailyLogDAO.findIdDailyLog(id);
	}

	@Override
	public List<DailyLog> listAllDailyLogs() {
		// TODO Auto-generated method stub
		return dailyLogDAO.listDailyLogs();
	}

}
