package co.edu.ue.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.Image;

public interface IImages extends JpaRepository<Image, Integer>{
	
}
