package co.edu.ue.service;

import java.util.List;


import co.edu.ue.entity.Image;

public interface IImageService {
	
	List<Image> addImage(Image image);
	Image upImage(Image Image);
	Image findByIdImage(int id);
	List<Image> listAllImages();
}
