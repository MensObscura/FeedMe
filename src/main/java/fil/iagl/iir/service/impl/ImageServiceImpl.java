package fil.iagl.iir.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fil.iagl.iir.constante.CONSTANTES;
import fil.iagl.iir.dao.image.ImageDao;
import fil.iagl.iir.entite.Image;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

  @Autowired
  private ImageDao imageDao;

  @Override
  public Image sauvegarder(MultipartFile image) {
    if (image == null) {
      throw new FeedMeException("Parametre null");
    }
    if (image.isEmpty()) {
      throw new FeedMeException("Le fichier est vide");
    }

    String imageExt = CONSTANTES.IMAGE_EXTENSION_VALIDE.stream().filter(ext -> image.getOriginalFilename().toLowerCase().endsWith(ext)).findAny()
      .orElseThrow(() -> new FeedMeException("Ne correspond à aucun format connu"));

    try {
      BufferedImage src = ImageIO.read(new ByteArrayInputStream(image.getBytes()));

      String fileName = generateNewName();
      String physiquePath = CONSTANTES.STATIC_RESSOURCE_LOCATION + File.separatorChar + CONSTANTES.UPLOADED_IMAGE_LOCATION + File.separatorChar + fileName + imageExt;
      String logiquePath = CONSTANTES.UPLOADED_IMAGE_LOCATION + File.separatorChar + fileName + imageExt;

      File dest = new File(physiquePath);
      dest.createNewFile();

      FileOutputStream output = new FileOutputStream(dest);
      ImageIO.write(src, imageExt.substring(1), output); // substring pour enlever le "." dans imageExt

      output.close();

      Image imageSauvegardee = new Image();
      imageSauvegardee.setPath(logiquePath);
      imageDao.sauvegarder(imageSauvegardee);
      return imageSauvegardee;
    } catch (IllegalStateException | IOException e) {
      throw new FeedMeException("Le fichier n'a pas pu s'enregistrer" + image.getOriginalFilename(), e);
    }
  }

  @Override
  public Image getById(Integer idImage) {
    if (idImage == null) {
      throw new FeedMeException("L'id de l'image est null");
    }
    return imageDao.getById(idImage);
  }

  /*
   * Permet de générer un nom de fichier unique ( basé sur la date + timestamp + string random )
   * 
   * Format : randomAlphanumeric(16)_date_timestamp
   * 
   * Ca devrait etre assez robuste /o/
   * 
   * TODO: Utiliser date java8
   */
  private String generateNewName() {
    String filename = "";
    long millis = System.currentTimeMillis();
    String datetime = DateFormat.getInstance().format(new Date());
    datetime = datetime.replace(" ", "");
    datetime = datetime.replace("/", "");
    datetime = datetime.replace(":", "");
    String rndchars = RandomStringUtils.randomAlphanumeric(16);
    filename = rndchars + "_" + datetime + "_" + millis;
    return filename;
  }

}
