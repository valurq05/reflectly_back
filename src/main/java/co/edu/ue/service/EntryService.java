package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.Entry;
import co.edu.ue.repository.dao.IEntryRepositoryDao;

@Service
public class EntryService implements IEntryService{

	@Autowired
	IEntryRepositoryDao entryDAO;
	@Override
	public List<Entry> addEntry(Entry entry) {
		return entryDAO.insertEntry(entry);
	}

	@Override
	public Entry upEntry(Entry entry) {
		return entryDAO.updateEntry(entry);
	}

	@Override
	public Entry findByIdEntry(int id) {
		return entryDAO.findIdEntry(id);
	}

	@Override
	public List<Entry> listAllEntry() {
		return entryDAO.listEntries();
	}

	@Override
	public void toggleStatByUseEntryId(Integer entryId) {
		
		entryDAO.toggleStatuseEntryId(entryId);
		
	}

	@Override
	public Boolean existsByentId(int entId) {
		return entryDAO.existsByentId(entId);
	}


}
