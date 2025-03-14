package co.edu.ue.service;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.dto.EntryDetailsDTO;
import co.edu.ue.entity.DailyLog;
import co.edu.ue.repository.dao.IDailyLogRepositoryDao;

@Service
public class DailyLogService implements IDailyLogService {

	@Autowired
	IDailyLogRepositoryDao dailyLogDAO;

	@Override
	public DailyLog addDailyLog(DailyLog DailyLog) {
		return dailyLogDAO.insertDailyLog(DailyLog);
	}

	@Override
	public DailyLog upDailyLog(DailyLog DailyLog) {
		return dailyLogDAO.updateDailyLog(DailyLog);
	}

	@Override
	public DailyLog findByIdDailyLog(int id) {
		return dailyLogDAO.findIdDailyLog(id);
	}

	@Override
	public List<DailyLog> listAllDailyLogs() {
		return dailyLogDAO.listDailyLogs();
	}

	@Override
	public List<EntryDetailsDTO> listAllDailyLogsByDateOrAndCategory(int userId, LocalDate dayLogDate,
			Integer categoryid) {
		List<EntryDetailsDTO> entryDetails = dailyLogDAO.listDailyLogsByDateOrAndCategory(userId, dayLogDate,
				categoryid);

		List<Object[]> categories = dailyLogDAO.listfindCategoriesForEntries(userId);
		List<Object[]> images = dailyLogDAO.listfindImagesForEntries(userId);
		System.out.println(entryDetails);
		System.out.println(categories);
		Map<Integer, EntryDetailsDTO> entryMap = new HashMap<Integer, EntryDetailsDTO>();
		for (EntryDetailsDTO entryDetail : entryDetails) {
			entryMap.put(entryDetail.getEntId(), entryDetail);
		}

		for (Object[] imageData : images) {
			int entId = (int) imageData[1];
			String imageUrl = (String) imageData[0];

			if (entryMap.containsKey(entId)) {
				entryMap.get(entId).add(imageUrl);
			}
		}

		for (Object[] categoryData : categories) {
			int entId = (int) categoryData[2];
			String category = (String) categoryData[1];

			System.out.println(entId);
			if (entryMap.containsKey(entId)) {
				entryMap.get(entId).addCategory(category);
			}
		}

		return new ArrayList<>(entryMap.values());
	}

	@Override
	public String allUserEntries(int userId) {
		List<EntryDetailsDTO> entryDetails = dailyLogDAO.listDailyLogsByDateOrAndCategory(userId, null, null);

		return entryDetails.stream()
				.map(entry -> entry.getEntDate() + ": " + stripHtmlTags(entry.getEntText()))
				.collect(Collectors.joining(" "));

	}

	private String stripHtmlTags(String html) {
		return html.replaceAll("<[^>]*>", "");
	}

}
