package fil.iagl.iir.service;

import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import fil.iagl.iir.constante.CONSTANTES;
import fil.iagl.iir.entite.Image;
import fil.iagl.iir.outils.FeedMeException;

public class ImageServiceTest extends AbstractServiceTest {

  @Mock
  MultipartFile mockMultipartFile;

  @Mock
  Image image;

  @Test
  public void sauvegarderTestSucces() throws Exception {
    // Etant donnée une certaine image ayant un format accepté
    File originalFile = new ClassPathResource("img" + File.separatorChar + "icon.gif").getFile();
    Mockito.when(mockMultipartFile.getOriginalFilename()).thenReturn("chemin/de/l'image/chez/l'upoader.GIF");
    byte[] binaryArray = IOUtils.toByteArray(new FileInputStream(originalFile));
    Mockito.when(mockMultipartFile.getBytes()).thenReturn(binaryArray);

    // Lorsque on demande au service de la sauvegarder
    Image savedImage = this.imageService.sauvegarder(mockMultipartFile);

    // On verifie que la DAO à bien été appelé
    Mockito.verify(imageDao, Mockito.times(1)).sauvegarder(Mockito.any());
    // On verifie que l'image retourner n'est pas nul
    Assertions.assertThat(savedImage).isNotNull();
    // On verifie que le path de l'image existe bien et a bien été sauvegarder sur le disque
    File result = new File(CONSTANTES.STATIC_RESSOURCE_LOCATION + File.separatorChar + savedImage.getPath());
    Assertions.assertThat(result).exists();
    // On verifie que le contenu est le meme que l'image uploadé ( pixel )
    byte[] originalPixels = ((DataBufferByte) ImageIO.read(originalFile).getRaster().getDataBuffer()).getData();
    byte[] resultPixels = ((DataBufferByte) ImageIO.read(result).getRaster().getDataBuffer()).getData();
    Assertions.assertThat(resultPixels).isEqualTo(originalPixels);

    // On supprime le fichier copié ( uniquement pour le test )
    FileUtils.forceDelete(result);
    Assertions.assertThat(result).doesNotExist();
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

  @Test(expected = FeedMeException.class)
  public void sauvegarderTestEchec_FichierVide() throws Exception {
    // Etant donnée un fichier vide
    Mockito.when(mockMultipartFile.isEmpty()).thenReturn(Boolean.TRUE);

    // On verifie que le service renvoi une exception
    this.imageService.sauvegarder(mockMultipartFile);
  }

  @Test(expected = FeedMeException.class)
  public void sauvegarderTestEchec_IOException() throws Exception {
    // Etant donnée un fichier vide
    Mockito.when(mockMultipartFile.getOriginalFilename()).thenReturn("chemin/de/l'image/chez/l'upoader.GIF");
    Mockito.when(mockMultipartFile.getBytes()).thenThrow(new IOException());

    // On verifie que le service renvoi une exception
    this.imageService.sauvegarder(mockMultipartFile);
  }

  @Test(expected = FeedMeException.class)
  public void getByIdTestEchec_IdNull() throws Exception {
    // Etant donnée un id d'image null
    Integer id = null;

    // On verifie que le service renvoi une exception lorsqu'on demande une image d'id null
    this.imageService.getById(id);
  }

  @Test
  public void getByIdTestSucces() throws Exception {
    // Etant donnée un retour connu de la DAO pour un id particulier
    Integer id = 1;
    Mockito.when(imageDao.getById(id)).thenReturn(image);

    // On verifie que le service nous renvoi bien le retour de la DAO
    Assertions.assertThat(this.imageService.getById(id)).isEqualTo(image);
  }

}
