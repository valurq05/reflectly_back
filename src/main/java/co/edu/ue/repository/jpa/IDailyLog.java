package co.edu.ue.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.DailyLog;

public interface IDailyLog extends JpaRepository<DailyLog, Integer>{

}
