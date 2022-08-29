package enigma;

public class EnigmaMachine {
    
    Reflector reflector;
    Rotor rotor1;
    Rotor rotor2;
    Rotor rotor3;
    Plugboard pb;
    Keyboard kb;
    Rotor I = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ","Q");
    Reflector B = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");

    public EnigmaMachine(Reflector reflector, Rotor rotor1, Rotor rotor2, Rotor rotor3, Plugboard plugboard, Keyboard keyboard){
        this.reflector = reflector!=null?reflector:B;
        this.rotor1 = rotor1!=null?rotor1:I;
        this.rotor2 = rotor2!=null?rotor2:I;
        this.rotor3 = rotor3!=null?rotor3:I;
        pb = plugboard;
        kb = keyboard;
    }

    public void setRings(int[] rings){
        rotor1.setRing(rings[0]!=-1?rings[0]:1);
        rotor2.setRing(rings[1]!=-1?rings[1]:1);
        rotor3.setRing(rings[2]!=-1?rings[2]:1);
    }

    public void setKey(String key){
        rotor1.rotateToLetter(Character.toString(key.charAt(0)));
        rotor2.rotateToLetter(Character.toString(key.charAt(1)));
        rotor3.rotateToLetter(Character.toString(key.charAt(2)));
    }

    public String encipher(String letter){

        // rotate the rotors
        if(((Character.toString(rotor2.left.charAt(0)).equals(rotor2.notch))) && ((Character.toString(rotor3.left.charAt(0))).equals(rotor3.notch))){
            rotor1.rotate();
            rotor2.rotate();
            rotor3.rotate();
        }
        else if(Character.toString(rotor2.left.charAt(0)).equals(rotor2.notch)){
            rotor1.rotate();
            rotor2.rotate();
            rotor3.rotate();
        }
        else if(Character.toString(rotor3.left.charAt(0)).equals(rotor3.notch)){
            rotor2.rotate();
            rotor3.rotate();
        }
        else{
            rotor3.rotate();
        }

        // pass signal through the machine
        int signal = kb.forward(letter);
        //System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        signal = pb.forward(signal);
        //System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        signal = rotor3.forward(signal);
        //System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        signal = rotor2.forward(signal);
        //System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        signal = rotor1.forward(signal);
        //System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        signal = reflector.reflect(signal);
        //System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        signal = rotor1.backward(signal);
        //System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        signal = rotor2.backward(signal);
        //System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        signal = rotor3.backward(signal);
        //System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        signal = pb.backward(signal);
        //System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(signal));
        letter = kb.backward(signal);
        return letter;
    }

}
