package fil.iagl.iir.predicat;

import java.util.function.Predicate;

import fil.iagl.iir.constante.CONSTANTE;

public final class Predicats {

  public static Predicate<Integer> estNoteValide = note -> note != null && note >= CONSTANTE.NOTE_MINIMALE && note <= CONSTANTE.NOTE_MAXIMALE;

}
