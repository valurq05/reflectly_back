package co.edu.ue.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.CategoriesEntry;
import co.edu.ue.entity.Category;
import co.edu.ue.entity.Entry;

public interface ICategoriesEntry extends JpaRepository<CategoriesEntry, Integer>{

    Boolean existsBycatEntId(int catEntId);
    Boolean existsBycategoryAndEntry(Category catId, Entry entId);
}
