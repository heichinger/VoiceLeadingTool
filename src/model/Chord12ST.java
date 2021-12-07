package model;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Represents a chord in a 12ST per octave tuning system.
 * NOTE: this can probably be abstracted with semitones being a global field. The constructor for
 * notes that takes in Strings would have to be made abstract.
 * <p>
 * Add in functionality for String representations that decides whether to use sharp or flat?
 *
 * Make flexible to where you can set 0. (Right now, C is set to 0.)
 */
public class Chord12ST implements Chord {
  private int[] chord;
  private final int ST = 12;
  //  define the octave
  // if D is in melody, D needs to be in chord
  // HOW TO MAKE A CHORD PROGRESSION? here is the melody. here are the notes in the melody we want
  // to play chords to.

  public Chord12ST(int[] notes) throws IllegalArgumentException {
    this.chord = new int[notes.length];
    for (int i = 0; i < chord.length; i++) {
      if (notes[i] >= ST || notes[i] < 0) {
        throw new IllegalArgumentException("illegal note");
      }
      chord[i] = notes[i];
    }
  }

  public Chord12ST(String[] notes) throws IllegalArgumentException {
    this.chord = new int[notes.length];
    for (int i = 0; i < chord.length; i++) {
      switch (notes[i].toLowerCase(Locale.ROOT)) {
        case "c":
          chord[i] = 0;
          break;
        case "c#":
        case "db":
          chord[i] = 1;
          break;
        case "d":
          chord[i] = 2;
          break;
        case "d#":
        case "eb":
          chord[i] = 3;
          break;
        case "e":
          chord[i] = 4;
          break;
        case "f":
          chord[i] = 5;
          break;
        case "f#":
        case "gb":
          chord[i] = 6;
          break;
        case "g":
          chord[i] = 7;
          break;
        case "g#":
        case "ab":
          chord[i] = 8;
          break;
        case "a":
          chord[i] = 9;
          break;
        case "a#":
        case "bb":
          chord[i] = 10;
          break;
        case "b":
          chord[i] = 11;
          break;
        default:
          throw new IllegalArgumentException("illegal note");
      }
    }
  }

  @Override
  public int[] getChord() {
    int[] chord = new int[this.chord.length];
    for (int i = 0; i < chord.length; i++) {
      chord[i] = this.chord[i];
    }
    return chord;
  }

  @Override
  public void transpose(int by) {
    int[] oldChord = getChord();
    for (int i = 0; i < chord.length; i++) {
      chord[i] += by;
      chord[i] %= ST;
    }
    System.out.println(chordString(oldChord) + " T" + by + " " + chordString(chord));
  }

  @Override
  public void invert(int a, int b) {
    int[] oldChord = getChord();
    double midpoint = (a + b) / 2.0;
    Queue<Double> distances = new ConcurrentLinkedQueue<>();

    for (int note : chord) {
      double doubleNote = (double) (note);
      double dist = 0;
      while (doubleNote != midpoint) {
        doubleNote += .5;
        dist += .5;
        if (doubleNote > 11.5) {
          doubleNote = 0;
        }
      }
      distances.add(dist);
    }

    for (int i = 0; i < chord.length; i++) {
      chord[i] = (int) Math.round(midpoint + distances.remove()) % 12;
    }
    System.out.println(chordString(oldChord)
            + " I" + noteString(a)
            + noteString(b) + " "
            + chordString(chord));
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Chord12ST)) {
      return false;
    }
    Chord12ST that = (Chord12ST) (other);

    boolean seen = false;
    for (int thisNote : this.chord) {
      for (int thatNote : that.chord) {
        if (thisNote == thatNote) {
          seen = true;
        }
      }
      if (!seen) {
        return false;
      }
      seen = false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    StringBuilder allInts = new StringBuilder();
    int [] sortedChord = getChord();
    Arrays.sort(sortedChord);
    for (int note : sortedChord) {
      allInts.append(note);
    }
    return Objects.hash(allInts.toString());
  }

  @Override
  public String chordString(int[] chord) {
    StringBuilder chordString = new StringBuilder("(");
    for (int i = 0; i < chord.length; i++) {
      chordString.append(noteString(chord[i])).append(" ");
    }
    return chordString.toString().trim() + ")";
  }

  @Override
  public String noteString(int note) {
    switch (note) {
      case 0:
        return "C";
      case 1:
        return "C#";
      case 2:
        return "D";
      case 3:
        return "D#";
      case 4:
        return "E";
      case 5:
        return "F";
      case 6:
        return "F#";
      case 7:
        return "G";
      case 8:
        return "G#";
      case 9:
        return "A";
      case 10:
        return "A#";
      case 11:
        return "B";
      default:
        throw new IllegalArgumentException("illegal note");
    }
  }
}
