package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.Entry;
import co.edu.ue.repository.jpa.IEntry;

@Repository
public class EntryRepositoryDao implements IEntryRepositoryDao{

	@Autowired
	IEntry entryJPA;
	@Override
	public List<Entry> insertEntry(Entry entry) {
		entryJPA.save(entry);
		return listEntries();
	}

	@Override
	public Entry updateEntry(Entry entry) {
		// TODO Auto-generated method stub
		return entryJPA.save(entry);
	}

	@Override
	public Entry findIdEntry(int id) {
		// TODO Auto-generated method stub
		
		return entryJPA.findById(id).orElse(null);
	}

	@Override
	public List<Entry> listEntries() {
		// TODO Auto-generated method stub
		return entryJPA.findAll();
	}

	@Override
	public void toggleStatuseEntryId(Integer entryId) {
			entryJPA.toggleStatusById(entryId);
		
	}



}
