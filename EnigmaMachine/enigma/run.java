package enigma;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class run extends Frame {

    private TextField enigmaRotorInput;
    private TextField enigmaRotorStartInput;
    private TextField enigmaRingsInput;
    private TextField enigmaReflectorInput;
    private TextField enigmaPlugboardInput;
    private TextField enigmaMessageInput;
    private TextArea enigmaOutput;
    public int n = 0;
    public String rotorInput = "I II III";
    public String rotorStartInput = "";
    public String ringsInput = " ";
    public String reflectorInput = "";
    public String plugboardInput = "";
    public String message = "";
    public static Scanner sc = new Scanner(System.in);

    public run(){

        setLayout(new FlowLayout()); // "super" frame sets to FlowLayout

        add(new Label("\nRotors:",SwingConstants.CENTER));
        enigmaRotorInput = new TextField(30);
        add(enigmaRotorInput);
        add(new Label("\nRotor Start:",SwingConstants.CENTER));
        enigmaRotorStartInput = new TextField(30);
        add(enigmaRotorStartInput);
        add(new Label("\nRings:",SwingConstants.CENTER));
        enigmaRingsInput = new TextField(30);
        add(enigmaRingsInput);
        add(new Label("\nReflector:",SwingConstants.CENTER));
        enigmaReflectorInput = new TextField(30);
        add(enigmaReflectorInput);
        add(new Label("\nPlugboard:",SwingConstants.CENTER));
        enigmaPlugboardInput = new TextField(30);
        add(enigmaPlugboardInput);
        add(new Label("\nEnter Text:", SwingConstants.CENTER));
        enigmaMessageInput = new TextField(30);
        add(enigmaMessageInput);
        enigmaOutput = new TextArea(5, 40); // 5 rows, 40 columns
        add(enigmaOutput);
  
        enigmaRotorInput.addKeyListener(new MyKeyListener());
        enigmaRotorStartInput.addKeyListener(new MyKeyListener());
        enigmaRingsInput.addKeyListener(new MyKeyListener());
        enigmaReflectorInput.addKeyListener(new MyKeyListener());
        enigmaPlugboardInput.addKeyListener(new MyKeyListener());
        enigmaMessageInput.addKeyListener(new MyKeyListener());

        setTitle("Enigma Simulator"); // "super" Frame sets title
        setSize(400, 500);         // "super" Frame sets initial size
        setVisible(true);          // "super" Frame shows
        addWindowListener(new WindowAdapter() {
  
           @Override
           public void windowClosing(WindowEvent e) {
               System.exit(0);
           }
       });

    }
    public static void main(String[] args){
        //System.out.println(encryptMessage("","","","","",takeInput()));
        new run(); 
        sc.close();
    }

   // Define an inner class to handle KeyEvent
   private class MyKeyListener implements KeyListener {
      // Called back when a key has been typed (pressed and released)
      @Override
      public void keyTyped(KeyEvent evt) {
        if(evt.getSource()==enigmaRotorInput){
            rotorInput=enigmaRotorInput.getText()+(evt.getKeyChar()!='\u0008'?evt.getKeyChar():"");
            System.out.println("Rotors - "+rotorInput);
            enigmaOutput.setText(encryptMessage(rotorInput,rotorStartInput, ringsInput, reflectorInput, plugboardInput, message));
        }
        if(evt.getSource()==enigmaRotorStartInput){
            rotorStartInput=enigmaRotorStartInput.getText()+(evt.getKeyChar()!='\u0008'?evt.getKeyChar():"");
            System.out.println("Rotor Start - "+rotorStartInput);
            enigmaOutput.setText(encryptMessage(rotorInput,rotorStartInput, ringsInput, reflectorInput, plugboardInput, message));
        }
        if(evt.getSource()==enigmaRingsInput){
            ringsInput = enigmaRingsInput.getText()+(evt.getKeyChar()!='\u0008'?evt.getKeyChar():"");
            System.out.println("Rings - "+ringsInput);
            enigmaOutput.setText(encryptMessage(rotorInput,rotorStartInput, ringsInput, reflectorInput, plugboardInput, message));
        }
        if(evt.getSource()==enigmaReflectorInput){
            reflectorInput = enigmaReflectorInput.getText()+(evt.getKeyChar()!='\u0008'?evt.getKeyChar():"");
            System.out.println("Reflector - "+reflectorInput);
            enigmaOutput.setText(encryptMessage(rotorInput,rotorStartInput, ringsInput, reflectorInput, plugboardInput, message));
        }
        if(evt.getSource()==enigmaPlugboardInput){
            plugboardInput = enigmaPlugboardInput.getText()+(evt.getKeyChar()!='\u0008'?evt.getKeyChar():"");
            System.out.println("Plugboard - "+plugboardInput);
            enigmaOutput.setText(encryptMessage(rotorInput,rotorStartInput, ringsInput, reflectorInput, plugboardInput, message));
        }
        if(evt.getSource()==enigmaMessageInput){
            message=enigmaMessageInput.getText()+(evt.getKeyChar()!='\u0008'?evt.getKeyChar():"");
            System.out.println("Message - "+message);
            enigmaOutput.setText(encryptMessage(rotorInput,rotorStartInput, ringsInput, reflectorInput, plugboardInput, message));
        }
      }

      // Not Used, but need to provide an empty body for compilation
      @Override public void keyPressed(KeyEvent evt) { }
      @Override public void keyReleased(KeyEvent evt) { }
    }
    public static String takeInput(){
        System.out.println("Enter message to encrypt:");
        String input = sc.nextLine();
        return input;
    }
    public static String encryptMessage(String rotorInput, String rotorStartKey, String ringsInput, String reflectorInput,String plugboardInput, String message){

        //historical enigma rotors and reflectors
        Rotor I = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ","Q");
        Rotor II = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE","E");
        Rotor III = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO","V");
        Rotor IV = new Rotor("ESOVPZJAYQUIRHXLNFTGKDCMWB","J");
        Rotor V = new Rotor("VZBRGITYUPSDNHLXAWMJQOFECK","Z");
        Reflector A = new Reflector("EJMZALYXVBWFCRQUONTSPIKHGD");
        Reflector B = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        Reflector C = new Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");

        // keyboard and plugboard
        Keyboard kb = new Keyboard();
        Plugboard pb = new Plugboard(plugboardInput!=null?plugboardInput.split(" "): new String[]{"AA"});

        Map<String, Rotor> rotorNames = new HashMap<String, Rotor>();
        rotorNames.put("I", I);
        rotorNames.put("II", II);
        rotorNames.put("III", III);
        rotorNames.put("IV", IV);
        rotorNames.put("V", V);

        Map<String, Reflector> reflectorNames = new HashMap<String, Reflector>();
        reflectorNames.put("A", A);
        reflectorNames.put("B", B);
        reflectorNames.put("C", C);

        String[] rotors = rotorInput.split(" ");

        // define an enigma machine
        //EnigmaMachine enigma = new EnigmaMachine(B, rotorNames.get(rotors[0]), rotorNames.get(rotors[1]), rotorNames.get(rotors[2]), pb, kb);
        EnigmaMachine enigma = new EnigmaMachine(reflectorNames.get(reflectorInput), rotorNames.get(rotors[0]), rotorNames.get(rotors[rotors.length>1?rotors.length-2:rotors.length>0?rotors.length-1:rotors.length]), rotorNames.get(rotors[rotors.length>0?rotors.length-1:rotors.length]), pb, kb);
        //EnigmaMachine enigma = new EnigmaMachine(B, I, IV, II, pb, kb);

        // set rings
        if(ringsInput.equals("")==false)
            enigma.setRings(new int[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ringsInput.charAt(0))+1,"ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ringsInput.charAt(ringsInput.length()>1?ringsInput.length()-2:ringsInput.length()>0?ringsInput.length()-1:ringsInput.length()))+1,"ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ringsInput.charAt(ringsInput.length()>0?ringsInput.length()-1:ringsInput.length()))+1});
        else
            enigma.setRings(new int[]{1,1,1});


        //set message key
        if(rotorStartKey.length()==3)
            enigma.setKey(rotorStartKey);
        else 
            enigma.setKey("ABC");

        //encipher a message
        String cipherText = "";
        for(int i = 0; i<message.length(); i++){
            if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(Character.toString(Character.toUpperCase(message.charAt(i))))==-1)
                cipherText+= Character.toString(Character.toUpperCase(message.charAt(i)));
            else
                cipherText += enigma.encipher(Character.toString(Character.toUpperCase(message.charAt(i))));
        }   
        return cipherText;
    }
}