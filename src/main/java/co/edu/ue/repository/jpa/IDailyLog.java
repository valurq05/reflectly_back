package co.edu.ue.repository.jpa;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.ue.dto.EntryDetailsDTO;
import co.edu.ue.entity.DailyLog;

public interface IDailyLog extends JpaRepository<DailyLog, Integer>{

	@Query("SELECT new co.edu.ue.dto.EntryDetailsDTO(en.entId, dl.dayLogDate, el.emoLogId, el.emoLogDate, es.emoStaState, " +
            "en.entDate, en.entTitle, en.entText, p.perName, u.useMail) " +
            "FROM DailyLog dl " +
            "JOIN dl.emotionalLog el " +
            "JOIN el.emotionalState es " +
            "JOIN dl.entry en " +
            "JOIN Collaborator c ON c.entry = en " +
            "JOIN c.user u " +
            "JOIN u.person p " +
            "LEFT JOIN CategoriesEntry ce ON ce.entry = en " + 
            "LEFT JOIN ce.category ca " + 
            "WHERE u.useId = :userId " +
            "AND (:dayLogDate IS NULL OR dl.dayLogDate = :dayLogDate) " +
            "AND (:categoryId IS NULL OR ce.category.catId = :categoryId) " +
            "AND en.entStatus = true "+
            "GROUP BY en.entId, dl.dayLogDate, el.emoLogId, el.emoLogDate, es.emoStaState, " +
            "en.entDate, en.entTitle, en.entText, p.perName, u.useMail")
List<EntryDetailsDTO> findEntryDetailsByUserAndOptionalDateAndCategory(
    @Param("userId") int userId, 
    @Param("dayLogDate") LocalDate dayLogDate, 
    @Param("categoryId") Integer categoryId);


	
	@Query("SELECT ce.catEntId, ca.catCategorie, ce.entry.entId " +
		       "FROM CategoriesEntry ce " +
		       "JOIN ce.category ca " +
		       "WHERE ce.entry.entId IN (SELECT dl.entry.entId " +
		                                "FROM DailyLog dl " +
		                                "JOIN dl.entry en " +
		                                "JOIN Collaborator c ON c.entry = en " +
		                                "JOIN c.user u " +
		                                "WHERE u.useId = :userId)")
		List<Object[]> findCategoriesForEntries(
		    @Param("userId") int userId);
	 
}
