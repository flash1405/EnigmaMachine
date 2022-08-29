package enigma;
public class Keyboard {

    public int forward(String letter){
        int signal = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(letter);
        return signal;
    }

    public String backward(int signal){
        String letter = Character.toString("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        return letter;
    }

    // public static void main(String[] args){
    //     Keyboard k = new Keyboard();
    //     System.out.println(k.forward("A"));
    //     System.out.println(k.backward(0));
    // }
}
