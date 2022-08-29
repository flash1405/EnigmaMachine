package enigma;
public class Reflector {

    String left;
    String right;
    String notch;

    public Reflector(String wiring){
        left = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        right = wiring==null?"YRUHQSLDPXNGOKMIEBFZCWVJAT":wiring;
    }

    public int reflect(int signal){
        String letter = Character.toString(right.charAt(signal));
        signal = left.indexOf(letter);
        return signal;
    }

}
