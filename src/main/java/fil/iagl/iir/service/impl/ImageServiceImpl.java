package fil.iagl.iir.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import fil.iagl.iir.dao.image.ImageDao;
import fil.iagl.iir.entite.Image;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.service.ImageService;

public class ImageServiceImpl implements ImageService {

  @Autowired
  private ImageDao imageDao;

  @Override
  public Image sauvegarder(MultipartFile image) {
    if (image == null) {
      throw new FeedMeException("Parametre null");
    }

    if (extensions.stream().noneMatch(ext -> image.getOriginalFilename().endsWith(ext))) {
      throw new FeedMeException("N'est pas une image");
    }

    if (image.isEmpty()) {
      throw new FeedMeException("Le fichier est vide");
    }

    String orgName = image.getOriginalFilename();
    try {
      BufferedImage src = ImageIO.read(new ByteArrayInputStream(image.getBytes()));

      String filePath = orgName;
      File dest = new File(filePath);

      ImageIO.write(src, "png", dest);
    } catch (IllegalStateException e) {
      e.printStackTrace();
      System.out.println("File uploaded failed:" + orgName);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("File uploaded failed:" + orgName);
    }
    System.out.println("File uploaded:" + orgName);
    return null;
  }

  private String instrumentFilename(MultipartFile image) {
    String originalFilename = image.getOriginalFilename();
    return originalFilename.substring(originalFilename.lastIndexOf(File.separatorChar));
  }

}
