package enigma;
public class Plugboard {
    
    String left;
    String right;

    public Plugboard(String[] pairs){
        left = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        right = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i<pairs.length; i++){
            if(pairs[i].equals("")==false){
                String A = Character.toString(pairs[i].charAt(0));
                String B = Character.toString(pairs[i].charAt(pairs[i].length()>1?1:0));
                int pos_A = left.indexOf(A);
                int pos_B = left.indexOf(B);
                if(pos_A!=-1&&pos_B!=-1){
                    left = left.substring(0,pos_A) + B + left.substring(pos_A+1);
                    left = left.substring(0,pos_B) + A + left.substring(pos_B+1);
                }
            }
        }
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

    // public static void main(String[] args){
    //     Plugboard p = new Plugboard(new String[]{"AB","EH"});
    //     System.out.println(p.forward(4));
    // }

}
