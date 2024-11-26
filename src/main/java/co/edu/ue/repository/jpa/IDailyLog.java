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

	@Query("SELECT new co.edu.ue.dto.EntryDetailsDTO(dll.dayLogDate, ell.emoLogId, ell.emoLogDate, ess.emoStaState, " +
		       "enn.entDate, enn.entTitle, enn.entText, pe.perName, us.useMail) " +
		       "FROM DailyLog dll " +
		       "JOIN dll.emotionalLog ell " +
		       "JOIN ell.emotionalState ess " +
		       "JOIN dll.entry enn " +
		       "JOIN Collaborator co ON co.entry = enn " +
		       "JOIN co.user us " +
		       "JOIN us.person pe " +
		       "WHERE us.useId = :userId")
		List<EntryDetailsDTO> findDailyLogsByUserId(@Param("userId") int userId);

	@Query("SELECT new co.edu.ue.dto.EntryDetailsDTO(dl.dayLogDate, el.emoLogId, el.emoLogDate, es.emoStaState, " +
		       "en.entDate, en.entTitle, en.entText, p.perName, u.useMail) " +
		       "FROM DailyLog dl " +
		       "JOIN dl.emotionalLog el " +
		       "JOIN el.emotionalState es " +
		       "JOIN dl.entry en " +
		       "JOIN Collaborator c ON c.entry = en " +
		       "JOIN c.user u " +
		       "JOIN u.person p " +
		       "WHERE dl.dayLogDate = :dayLogDate " +
		       "AND u.useId = :userId")
		List<EntryDetailsDTO> findDailyLogsByDateAndUserId(@Param("dayLogDate") LocalDate dayLogDate, 
		                                                   @Param("userId") int userId);

	 
}
