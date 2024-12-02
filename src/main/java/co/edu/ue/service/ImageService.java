package co.edu.ue.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import co.edu.ue.entity.Image;
import co.edu.ue.repository.dao.IImageRepostioryDao;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepostioryDao imageDAO;



    @Override
    public List<Image> addImage(Image image) {  
        return imageDAO.insertImage(image);
    }

    @Override
    public Image upImage(Image image) {
        return imageDAO.updateImage(image);
    }

    @Override
    public Image findByIdImage(int id) {
        return imageDAO.findIdImage(id);
    }

    @Override
    public List<Image> listAllImages() {
        return imageDAO.listImages();
    }
}
