package fil.iagl.iir.predicat;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class PredicatsTest {

  @Test
  public void estVoteValideTestSucces() throws Exception {
    Assertions.assertThat(Predicats.estNoteValide.test(1)).isTrue();
    Assertions.assertThat(Predicats.estNoteValide.test(5)).isTrue();
  }

  @Test
  public void estVoteValideTestEchec() throws Exception {
    Assertions.assertThat(Predicats.estNoteValide.test(0)).isFalse();
    Assertions.assertThat(Predicats.estNoteValide.test(6)).isFalse();
    Assertions.assertThat(Predicats.estNoteValide.test(null)).isFalse();
  }
}
