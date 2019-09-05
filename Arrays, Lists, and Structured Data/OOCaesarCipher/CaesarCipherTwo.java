
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1,alphabet.length()) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2,alphabet.length()) + alphabet.substring(0,key2);
        int mainKey1 = key1;
        int mainKey2 = key2;
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder("");
        for (int i=0; i<input.length(); i++){
            String shiftedAlphabet = null;
            if (i%2==0){
                shiftedAlphabet = shiftedAlphabet1;
            }
            else {
                shiftedAlphabet = shiftedAlphabet2;
            }
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
    public String decrypt(String input){
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String decrypted = cc2.encrypt(input);
        return decrypted;
    }
}