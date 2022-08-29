package enigma;
public class Rotor{
    
    String left;
    String right;
    String notch;

    public Rotor(String wiring, String notch){
        left = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        right = wiring==null?"EKMFLGDQVZNTOWYHXUSPAIBRCJ":wiring;
        this.notch = notch;
    }

    public int forward(int signal){
        String letter = Character.toString(right.charAt(signal));
        signal = left.indexOf(letter);
        return signal;
    }
    
    public int backward(int signal){
        String letter = Character.toString(left.charAt(signal));
        signal = right.indexOf(letter);
        return signal;
    }

    public void rotate(){
        left = left.substring(1) + left.charAt(0);
        right = right.substring(1) + right.charAt(0);
    }

    public void rotate(int nRotations){
        for(int i = 0; i<nRotations; i++){
            left = left.substring(1) + left.charAt(0);
            right = right.substring(1) + right.charAt(0);
        }
    }

    public void rotate(int nRotations, boolean forward){
        if(forward==false){
            for(int i = 0; i<nRotations; i++){
                left = left.substring(25) + left.substring(0,25);
                right = right.substring(25) + right.substring(0,25);
            }
        }
    }

    public void show(){
        System.out.println(left);
        System.out.println(right);
        System.out.println("");
    }

    public void rotateToLetter(String letter){
        int nRotations = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(letter);
        this.rotate(nRotations);
    }

    public void setRing(int pos){

        // rotate the rotor backwards
        this.rotate(pos-1, false);

        // adjust the turnover notch in relationship to the wiring
        int n_notch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(this.notch);
        this.notch = Character.toString("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((n_notch-pos<0)?(n_notch-pos+26):(n_notch-pos)));
    }

    // public static void main(String[] args){
    //     Rotor I = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ","Q");
    //     Rotor II = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE","E");
    //     Rotor III = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO","V");
    //     Rotor IV = new Rotor("ESOVPZJAYQUIRHXLNFTGKDCMWB","J");
    //     Rotor V = new Rotor("VZBRGITYUPSDNHLXAWMJQOFECK","Z");
    //     I.show();
    //     I.rotateToLetter("G");
    //     I.show();
    // }
}
