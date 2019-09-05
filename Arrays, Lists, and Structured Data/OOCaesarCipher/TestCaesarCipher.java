
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipher {
    private int[] countLetters(String encrypted){
        int[] letters = new int[26];
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int k=0; k<encrypted.length(); k++){
            int index = alphabet.indexOf(Character.toUpperCase(encrypted.charAt(k)));
            if (index != -1){
                letters[index] += 1;
            }
        }
        return letters;
    }
    private int maxIndex(int[] values){
        int indexMax = 0;
        for (int k=0; k<values.length; k++){
            if (values[k] >= values[indexMax]){
                indexMax = k;
            }
        }
        return indexMax;
    }
    public void breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        System.out.println("The key is " + dkey);
        CaesarCipher cc = new CaesarCipher(dkey);
        String decrypted = cc.decrypt(input);
        System.out.println("The decrypted string is \n");
        System.out.println(decrypted);
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("The message is \n");
        CaesarCipher cc = new CaesarCipher(18);
        System.out.println(message);
        String encrypted = cc.encrypt(message);
        System.out.println("The encrypted string is \n");        
        System.out.println(encrypted);
        String decrypted = cc.decrypt(message);
        System.out.println("The decrypted string is \n");
        System.out.println(decrypted);
        System.out.println("Automatic decryption: \n");
        breakCaesarCipher(encrypted);
    }
}
