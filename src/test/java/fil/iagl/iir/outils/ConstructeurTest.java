package fil.iagl.iir.outils;

import java.lang.reflect.Constructor;

import org.apache.ibatis.javassist.Modifier;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.core.Condition;
import org.junit.Test;

import fil.iagl.iir.constante.CONSTANTES;
import fil.iagl.iir.predicat.PREDICATS;

public class ConstructeurTest {

  @Test
  public void ConstanteConstructeurPriveTest() {
    nePossedeQueDesConstructeursPrives(CONSTANTES.class);
    nePossedeQueDesConstructeursPrives(PREDICATS.class);
  }

  private void nePossedeQueDesConstructeursPrives(Class<?> clazz) {
    Constructor<?>[] constructeurs = clazz.getDeclaredConstructors();
    Assertions.assertThat(constructeurs).are(new Condition<Constructor<?>>() {
      @Override
      public boolean matches(Constructor<?> constructor) {
        return Modifier.isPrivate(constructor.getModifiers());
      }
    });
  }

}
