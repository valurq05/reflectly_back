package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.Image;
import co.edu.ue.repository.jpa.IImages;
@Repository
public class ImagesRepositoryDao implements IImageRepostioryDao{

	@Autowired
	IImages imageJPA;
	@Override
	public List<Image> insertImage(Image Image) {
		imageJPA.save(Image);
		return listImages();
	}

	@Override
	public Image updateImage(Image Image) {
		return imageJPA.save(Image);
	}

	@Override
	public Image findIdImage(int id) {
		return imageJPA.findById(id).orElseThrow( () -> new RuntimeException("No se encontro la imagen"));
	}

	@Override
	public List<Image> listImages() {
		return imageJPA.findAll();
	}

	@Override
	public Boolean existsImgId(int imgId) {
		return imageJPA.existsById(imgId);
	}

}
