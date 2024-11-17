package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.Image;
import co.edu.ue.repository.dao.IImageRepostioryDao;

@Service
public class ImageService implements IImageService{

	@Autowired
	IImageRepostioryDao imageDAO;
	@Override
	public List<Image> addImage(Image Image) {
		// TODO Auto-generated method stub
		return imageDAO.insertImage(Image);
	}

	@Override
	public Image upImage(Image Image) {
		// TODO Auto-generated method stub
		return imageDAO.updateImage(Image);
	}

	@Override
	public Image findByIdImage(int id) {
		// TODO Auto-generated method stub
		return imageDAO.findIdImage(id);
	}

	@Override
	public List<Image> listAllImages() {
		// TODO Auto-generated method stub
		return imageDAO.listImages();
	}

}
