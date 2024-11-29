package co.edu.ue.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.ue.entity.Entry;

public interface IEntry extends JpaRepository<Entry, Integer>{


}
