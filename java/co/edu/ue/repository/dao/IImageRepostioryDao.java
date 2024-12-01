package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.Image;

public interface IImageRepostioryDao{
	
	List<Image> insertImage(Image Image);
	Image updateImage(Image Image);
	Image findIdImage(int id);
	List<Image> listImages();

}
