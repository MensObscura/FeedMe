package fil.iagl.iir.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import fil.iagl.iir.entite.Image;

public interface ImageService {

  public static final List<String> extensions = Arrays.asList(".jpg", ".bmp", ".svg", ".gif");

  public static final String BASE_PREFIX = Arrays.asList("src", "main", "resources", "public").stream().collect(Collectors.joining("" + File.separatorChar));

  public static final String PUBLIC_LOCATION = Arrays.asList("resources", "img", "upload").stream().collect(Collectors.joining("" + File.separatorChar));

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
