package fil.iagl.iir.entite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {

  private Integer id;
  private String path;

  public Image() {

  }

  @Override
  public String toString() {
    return "Image [id=" + id + ", path=" + path + "]";
  }

}
