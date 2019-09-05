
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key,alphabet.length()) + alphabet.substring(0,key);
        mainKey = key;
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder("");
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
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(encrypted);
    }
}