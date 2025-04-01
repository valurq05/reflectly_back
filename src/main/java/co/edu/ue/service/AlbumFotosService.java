package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import co.edu.ue.entity.AlbumFoto;
import co.edu.ue.repository.dao.IAlbumFotosRepositoryDao;

@Service
public class AlbumFotosService implements IAlbumFotosService {

    @Autowired
    private IAlbumFotosRepositoryDao albumFotosDao;

    @Override
    public List<AlbumFoto> getAll() {
        return albumFotosDao.findAll();
    }

    @Override
    public AlbumFoto getById(int id) {
        return albumFotosDao.findById(id);
    }

    @Override
    public AlbumFoto save(AlbumFoto albumFoto) {
        return albumFotosDao.save(albumFoto);
    }

    @Override
    public void delete(int id) {
        albumFotosDao.deleteById(id);
    }

    @Override
    public List<AlbumFoto> getByUserId(int userId) {
        return albumFotosDao.findByUserUseId(userId);
    }
}