
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder("");
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key,alphabet.length()) + alphabet.substring(0,key);
        for (int i=0; i<input.length(); i++){
            String rep = Character.toString(input.charAt(i));
            if (alphabet.contains(rep) || alphabet.toLowerCase().contains(rep)){
                //lowercase
                if (rep.equals(rep.toLowerCase())){
                    int index = alphabet.toLowerCase().indexOf(rep);
                    char repCh = shiftedAlphabet.toLowerCase().charAt(index);
                    rep = Character.toString(repCh);
                }
                //uppercase
                else{
                    int index = alphabet.indexOf(rep);
                    char repCh = shiftedAlphabet.charAt(index);
                    rep = Character.toString(repCh);
                }
            }
            encrypted.append(rep);
        }
        return encrypted.toString();
    }
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encryptedTwo = new StringBuilder("");
        for (int i=0; i<input.length(); i++){
            String rep = Character.toString(input.charAt(i));
            if (i%2==0){
                encryptedTwo.append(encrypt(rep, key1));
            }
            else {
                encryptedTwo.append(encrypt(rep, key2));
            }
        }
        return encryptedTwo.toString();
    }
    public void testCaesar(){
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        //String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String encrypted = encrypt(message, 15);
        System.out.println("encrypted message: " + encrypted);
        String encryptedTwo = encryptTwoKeys(message, 21, 8);
        System.out.println("encrypted message: " + encryptedTwo);
        String decrypted = encryptTwoKeys(message, 12, 2);
        System.out.println("decrypted message: " + decrypted);
    }
    public String toString(){
        return "This is a pretty cool concept";
    }
}
