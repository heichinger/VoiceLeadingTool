import model.VoiceLeadingTool12ST;
import model.VoiceLeadingToolModel;

/**
 * No view or controller for now, the model just prints relevant information to the console.
 */
public class Main {
  public static void main(String [] args) {
    String [] chord1 = {"D", "F", "Ab", "C"};
    String [] chord2 = {"A", "C#", "E", "D"};
    VoiceLeadingToolModel vl = new VoiceLeadingTool12ST(chord1, chord2);
    System.out.println(vl.isTransposition());
    System.out.println(vl.isInversion());
  }
}
