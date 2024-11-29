package co.edu.ue.repository.dao;

import java.util.List;


import co.edu.ue.entity.Entry;

public interface IEntryRepositoryDao {

	List<Entry> insertEntry(Entry entry);
	Entry updateEntry(Entry entry);
	Entry findIdEntry(int id);
	List<Entry> listEntries();

	
}
