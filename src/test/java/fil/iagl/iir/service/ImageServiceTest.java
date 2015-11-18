package fil.iagl.iir.service;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import fil.iagl.iir.entite.Image;
import fil.iagl.iir.outils.FeedMeException;

public class ImageServiceTest extends AbstractServiceTest {

  @Mock
  MultipartFile mockMultipartFile;

  @Test
  public void sauvegarderTestSucces() throws Exception {
    File originalFile = new ClassPathResource("img" + File.separatorChar + "icon.gif").getFile();
    String path = "img" + File.separatorChar + "1.gif";
    Image image = new Image();
    image.setPath(path);
    Mockito.when(mockMultipartFile.getOriginalFilename()).thenReturn("img/troll.gif");
    Mockito.when(mockMultipartFile.getBytes()).thenReturn(IOUtils.toByteArray(new FileInputStream(originalFile)));

    Image savedImage = this.imageService.sauvegarder(mockMultipartFile);

    Mockito.verify(imageDao, Mockito.times(1)).sauvegarder(Mockito.any());
  }

  @Test(expected = FeedMeException.class)
  public void sauvegarderTestEchec_ImageNull() throws Exception {
    // Etant donnée une image null
    // On verifie que le service renvoi bien une exception
    this.imageService.sauvegarder(null);
  }

  @Test(expected = FeedMeException.class)
  public void sauvegarderTestEchec_PasImage() throws Exception {
    // Etant donnée un fichier qui n'est pas une image
    Mockito.when(mockMultipartFile.getOriginalFilename()).thenReturn("C:/azeiaizuehaiuzeh/euiazbeaze.mp3");

    // On verifie que le service renvoi une exception
    this.imageService.sauvegarder(mockMultipartFile);
  }

}
