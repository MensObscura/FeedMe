package fil.iagl.iir.entite;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image implements Serializable {

  private static final long serialVersionUID = -4674270967921890278L;
  private Integer id;
  private String path;

  @Override
  public String toString() {
    return "Image [id=" + id + ", path=" + path + "]";
  }

}
