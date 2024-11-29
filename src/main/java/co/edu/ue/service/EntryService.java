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
		// TODO Auto-generated method stub
		return entryDAO.insertEntry(entry);
	}

	@Override
	public Entry upEntry(Entry entry) {
		// TODO Auto-generated method stub
		return entryDAO.updateEntry(entry);
	}

	@Override
	public Entry findByIdEntry(int id) {
		// TODO Auto-generated method stub
		return entryDAO.findIdEntry(id);
	}

	@Override
	public List<Entry> listAllEntry() {
		// TODO Auto-generated method stub
		return entryDAO.listEntries();
	}


}
