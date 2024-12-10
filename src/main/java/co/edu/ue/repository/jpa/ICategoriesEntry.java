package co.edu.ue.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ue.entity.CategoriesEntry;
import co.edu.ue.entity.Category;
import co.edu.ue.entity.Entry;

public interface ICategoriesEntry extends JpaRepository<CategoriesEntry, Integer> {


    Boolean existsByCatEntIdAndCatEntStatusTrue(int catEntId);

    Boolean existsByCategoryAndEntryAndCatEntStatusTrue(Category catId, Entry entId);

    @Query("SELECT c FROM CategoriesEntry c JOIN c.category ca WHERE c.entry.entId = :entId and c.catEntStatus = true")
    List<CategoriesEntry> findAllByEntry(Integer entId);

    @Transactional
    @Modifying
    @Query("UPDATE CategoriesEntry c SET c.catEntStatus = NOT c.catEntStatus WHERE c.catEntId = :catEntId")
    void toggleStatusById(Integer catEntId);
}
