package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.AlbumFoto;

public interface IAlbumFotosRepositoryDao {

    List<AlbumFoto> findAll();

    AlbumFoto findById(int id);

    AlbumFoto save(AlbumFoto albumFoto);

    void deleteById(int id);

    List<AlbumFoto> findByUserUseId(int useId);
}
