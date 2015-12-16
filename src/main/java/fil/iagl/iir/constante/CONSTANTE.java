package fil.iagl.iir.constante;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class CONSTANTE {

  private CONSTANTE() {
  }

  public static final Integer NB_IMAGE_PAR_OFFRE_NON_PREMIUM = 1;
  public static final Integer NB_HEURE_POUR_CHANGER_OFFRE = 48;

  public static final List<String> IMAGE_EXTENSION_VALIDE = Collections.unmodifiableList(Arrays.asList(".jpg", ".jpeg", ".bmp", ".svg", ".gif", ".png"));
  public static final String STATIC_RESSOURCE_LOCATION = Arrays.asList("src", "main", "resources", "public").stream().collect(Collectors.joining("" + File.separatorChar));
  public static final String UPLOADED_IMAGE_LOCATION = Arrays.asList("resources", "img", "upload").stream().collect(Collectors.joining("" + File.separatorChar));

}
