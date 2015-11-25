package fil.iagl.iir.controller.image;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fil.iagl.iir.controller.AbstractControllerTest;
import fil.iagl.iir.entite.Image;

public class ImageControllerTest extends AbstractControllerTest {

  @Override
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.imageController)
      .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
  }

  @Test
  public void uploadTestSucces() throws Exception {
    // Etant donné une image
    Integer idImage = 1;
    String path = "MonPathVersUneRessourcePublic";
    Image image = new Image();
    image.setId(idImage);
    image.setPath(path);

    // Etant donné un mock de fichié envoyé
    MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "some image".getBytes());

    // Etant donné que le service nous renvoi cette image pour mock du fichier
    Mockito.when(imageService.sauvegarder(mockMultipartFile)).thenReturn(image);

    // On verifie que le controlleur nous renvois bien cette image lorsqu'on l'appel avec le mock du fichier
    mockMvc.perform(MockMvcRequestBuilders.fileUpload("/image").file(mockMultipartFile))
      // On verifie que le status est bien 200
      .andExpect(status().isOk())
      // On verifie que le json à bien un champs id qui correspond à l'image retourné
      .andExpect(jsonPath("$.id").value(image.getId()))
      // On verifie que le json à bien un champs path qui correspond à l'image retourné
      .andExpect(jsonPath("$.path").value(image.getPath()));

    // On verifie que le service à bien été appelé dans le controlleur
    Mockito.verify(imageService, Mockito.times(1)).sauvegarder(mockMultipartFile);
  }

  @Test
  public void getByIdTestSucces() throws Exception {
    // Etant donné une image
    Integer idImage = 1;
    String path = "MonPathVersUneRessourcePublic";
    Image image = new Image();
    image.setId(idImage);
    image.setPath(path);
    // Etant donné que le service nous renvoi cette image pour l'id 1
    Mockito.when(imageService.getById(idImage)).thenReturn(image);

    // On verifie que le controlleur nous renvois bien cette image lorsqu'on l'appel avec l'id 1
    mockMvc.perform(get("/image/" + idImage))
      // On verifie que le status est bien 200
      .andExpect(status().isOk())
      // On verifie que le json à bien un champs id qui correspond à l'image retourné
      .andExpect(jsonPath("$.id").value(image.getId()))
      // On verifie que le json à bien un champs path qui correspond à l'image retourné
      .andExpect(jsonPath("$.path").value(image.getPath()));

    // On verifie que le service à bien été appelé dans le controlleur
    Mockito.verify(imageService, Mockito.times(1)).getById(idImage);
  }

}
