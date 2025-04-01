package co.edu.ue.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.AlbumFoto;

public interface IAlbumFotos extends JpaRepository<AlbumFoto, Integer> {

    List<AlbumFoto> findByUserUseId(int useId);
}
