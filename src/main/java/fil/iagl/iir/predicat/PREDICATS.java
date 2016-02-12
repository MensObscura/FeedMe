package fil.iagl.iir.predicat;

import java.util.function.Predicate;

import fil.iagl.iir.constante.CONSTANTES;

public final class PREDICATS {

  public static final Predicate<Integer> estNoteValide = note -> note != null && note >= CONSTANTES.NOTE_MINIMALE && note <= CONSTANTES.NOTE_MAXIMALE;

  private PREDICATS() {
  }

}