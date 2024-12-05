package co.edu.ue.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.EmotionalLog;

public interface IEmotionalLog extends JpaRepository<EmotionalLog, Integer>{

    Boolean existsByemoLogId(int emoLogId);
}
