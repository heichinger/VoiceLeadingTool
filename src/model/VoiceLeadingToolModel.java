package model;

public interface VoiceLeadingToolModel {
  /**
   * Determines if a voice leading is a transposition.
   * @return true if the voice leading is a transposition
   */
  boolean isTransposition();

  /**
   * Determines if a voice leading is an inversion.
   * @return true if the voice leading is an inversion
   */
  boolean isInversion();
}
