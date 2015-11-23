package fil.iagl.iir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fil.iagl.iir.entite.Image;
import fil.iagl.iir.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {

  @Autowired
  private ImageService imageService;

  @RequestMapping(method = RequestMethod.POST)
  public Image upload(@RequestParam(value = "img") MultipartFile multipartFile) {
    return imageService.sauvegarder(multipartFile);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Image getById(@PathVariable("id") Integer id) {
    return imageService.getById(id);
  }
}
