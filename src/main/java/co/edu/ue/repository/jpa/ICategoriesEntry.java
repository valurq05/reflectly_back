package co.edu.ue.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.CategoriesEntry;

public interface ICategoriesEntry extends JpaRepository<CategoriesEntry, Integer>{

    Boolean existsBycatEntId(int catEntId);
}
