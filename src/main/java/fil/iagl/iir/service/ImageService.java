package fil.iagl.iir.service;

import org.springframework.web.multipart.MultipartFile;

import fil.iagl.iir.entite.Image;

public interface ImageService {

  /**
   * Sauvegarde l'image en base de donnée
   * 
   * @param image l'image à sauvegarder
   */
  Image sauvegarder(MultipartFile image);

  /**
   * Recupere une image par son id
   * 
   * @param idImage l'id de l'image à recuperer
   * @return l'image contenant le path
   */
  Image getById(Integer idImage);

}
