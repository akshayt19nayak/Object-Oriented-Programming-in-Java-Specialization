
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for (int k=start; k<message.length(); k=k+2){
            sb.append(message.charAt(k));
        }
        return sb.toString();
    }
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
    private int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    public void breakCaesarCipher(String encrypted){
        String oneHalf = halfOfString(encrypted, 0);
        String otherHalf = halfOfString(encrypted, 1);
        int dkey1 = getKey(oneHalf);
        int dkey2 = getKey(otherHalf);
        System.out.println("The two keys are " + dkey1 + " and " + dkey2);
        CaesarCipherTwo cc2 = new CaesarCipherTwo(dkey1, dkey2);
        String decrypted = cc2.decrypt(encrypted);
        System.out.println("The decrypted string is \n");
        System.out.println(decrypted);
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("The message is \n");
        CaesarCipherTwo cc2 = new CaesarCipherTwo(17, 3);
        System.out.println(message);
        String encrypted = cc2.encrypt(message);
        System.out.println("The encrypted string is \n");        
        System.out.println(encrypted);
        String decrypted = cc2.decrypt(message);
        System.out.println("The decrypted string is \n");
        System.out.println(decrypted);
        System.out.println("Automatic decryption: \n");
        breakCaesarCipher(encrypted);
    }
}
