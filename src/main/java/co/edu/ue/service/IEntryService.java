package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.Entry;

public interface IEntryService {
	List<Entry> addEntry(Entry entry);
	Entry upEntry(Entry entry);
	Entry findByIdEntry(int id);
	List<Entry> listAllEntry();
	void toggleStatByUseEntryId(Integer entryId);
	Boolean existsByentId(int entId);

}
