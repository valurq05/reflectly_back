package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.AlbumFoto;
import co.edu.ue.repository.jpa.IAlbumFotos;

@Repository
public class AlbumFotosRepositoryDao implements IAlbumFotosRepositoryDao {

    @Autowired
    private IAlbumFotos albumFotosRepository;

    @Override
    public List<AlbumFoto> findAll() {
        return albumFotosRepository.findAll();
    }

    @Override
    public AlbumFoto findById(int id) {
        return albumFotosRepository.findById(id).orElse(null);
    }

    @Override
    public AlbumFoto save(AlbumFoto albumFoto) {
        return albumFotosRepository.save(albumFoto);
    }

    @Override
    public void deleteById(int id) {
        albumFotosRepository.deleteById(id);
    }

    @Override
    public List<AlbumFoto> findByUserUseId(int useId) {
        return albumFotosRepository.findByUserUseId(useId);
    }
}