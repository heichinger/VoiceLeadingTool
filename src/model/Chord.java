package model;

public interface Chord {
  /**
   * Returns the chord.
   * @return an integer array representing the placement of notes in the chord relative to the octave.
   */
  int [] getChord();

  /**
   * Transposes the chord by a given amount.
   * @param by the integer amount to transpose by
   */
  void transpose(int by);

  /**
   * Inverts a chord.
   * @param a the leftmost bound of the axis of reflection
   * @param b the rightmost bound of the axis of reflection
   */
  void invert(int a, int b);

  String chordString(int [] chord);

  String noteString(int note);
}
