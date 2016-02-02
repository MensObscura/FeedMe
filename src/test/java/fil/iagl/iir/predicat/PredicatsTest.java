package fil.iagl.iir.predicat;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class PredicatsTest {

  @Test
  public void estVoteValideTestSucces() {
    Assertions.assertThat(PREDICATS.estNoteValide.test(1)).isTrue();
    Assertions.assertThat(PREDICATS.estNoteValide.test(5)).isTrue();
  }

  @Test
  public void estVoteValideTestEchec() {
    Assertions.assertThat(PREDICATS.estNoteValide.test(0)).isFalse();
    Assertions.assertThat(PREDICATS.estNoteValide.test(6)).isFalse();
    Assertions.assertThat(PREDICATS.estNoteValide.test(null)).isFalse();
  }
}
