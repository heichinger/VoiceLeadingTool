package model;

public class VoiceLeadingTool12ST implements VoiceLeadingToolModel {
  Chord from;
  Chord to;

  public VoiceLeadingTool12ST(String[] from, String[] to) {
    this.from = new Chord12ST(from);
    this.to = new Chord12ST(to);
  }

  @Override
  public boolean isTransposition() {
    for (int i = 1; i < 12; i++) {
      Chord fromTranspose = new Chord12ST(from.getChord());
      fromTranspose.transpose(i);
      if (to.equals(fromTranspose)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isInversion() {
    for (int i = 0; i < 6; i++) {
      Chord fromInvertII = new Chord12ST(from.getChord());
      Chord fromInvertIJ = new Chord12ST(from.getChord());
      fromInvertII.invert(i, i);
      fromInvertIJ.invert(i, i + 1);
      if (to.equals(fromInvertII) || to.equals(fromInvertIJ)) {
        return true;
      }
    }
    return false;
  }
}
