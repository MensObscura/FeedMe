package fil.iagl.iir.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fil.iagl.iir.entite.Image;

public interface ImageService {

  public static final List<String> extensions = Arrays.asList(".jpg", ".bmp", ".svg", ".gif");

  /**
   * Sauvegarde l'image en base de donnée
   * 
   * @param image l'image à sauvegarder
   */
  Image sauvegarder(MultipartFile image);

}
