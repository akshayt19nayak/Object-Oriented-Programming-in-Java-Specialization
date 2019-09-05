
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarBreaker {
    public int[] countLetters(String encrypted){
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
    public int maxIndex(int[] values){
        int indexMax = 0;
        for (int k=0; k<values.length; k++){
            if (values[k] >= values[indexMax]){
                indexMax = k;
            }
        }
        return indexMax;
    }
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    public String decrypt(String encrypted, int dkey){
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(encrypted, 26-dkey);
    }
    public void testDecrypt(){
        CaesarCipher cc = new CaesarCipher();
        String toEncrypt = "FREE";
        System.out.println("The string to encrypt is " + toEncrypt);
        String encrypted = cc.encrypt(toEncrypt, 15);
        System.out.println("The encrypted string is " + encrypted);
        int dkey = getKey(encrypted);
        String decrypted = decrypt(encrypted, dkey);
        System.out.println("The decrypted string is " + decrypted);
    }
    public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for (int k=start; k<message.length(); k=k+2){
            sb.append(message.charAt(k));
        }
        return sb.toString();
    }
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String oneHalf = halfOfString(encrypted, 0);
        String otherHalf = halfOfString(encrypted, 1);
        int dkey1 = getKey(oneHalf);
        int dkey2 = getKey(otherHalf);
        System.out.println("The two keys are " + dkey1 + " and " + dkey2);
        String decrypted = cc.encryptTwoKeys(encrypted, 26-dkey1, 26-dkey2);
        return decrypted;
    }
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println(decryptTwoKeys(message));
    }
    public void simpleTest(){
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc);
    }
}
