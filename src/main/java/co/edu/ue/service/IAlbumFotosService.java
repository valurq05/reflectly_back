package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.AlbumFoto;

public interface IAlbumFotosService {

    List<AlbumFoto> getAll();

    AlbumFoto getById(int id);

    AlbumFoto save(AlbumFoto albumFoto);

    void delete(int id);

    List<AlbumFoto> getByUserId(int userId);
}