package co.edu.ue.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ue.entity.Entry;

public interface IEntry extends JpaRepository<Entry, Integer>{

	@Transactional
    @Modifying
    @Query("UPDATE Entry e SET e.entStatus = NOT e.entStatus WHERE e.entId = :entryId")
    void toggleStatusById(Integer entryId);

}
