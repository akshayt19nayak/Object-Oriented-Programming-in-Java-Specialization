
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        String strCh = Character.toString(ch);
        String alphabet = "AEIOU";
        String lowerAlphabet = alphabet.toLowerCase();
        if (alphabet.contains(strCh) || lowerAlphabet.contains(strCh)){
            return true;
        }
        else {
            return false;
        }
    }
    public String replaceVowels(String phrase, char ch){
        StringBuilder ret = new StringBuilder("");
        for (int i=0; i < phrase.length(); i++){
            char repCh = phrase.charAt(i);
            if (isVowel(repCh)){
                repCh = ch;
            }
            ret.append(repCh);
        }
        return ret.toString();
    }
    public String emphasize(String phrase, char ch){
        StringBuilder ret = new StringBuilder("");
        for (int i=0; i < phrase.length(); i++){
            char repCh = phrase.charAt(i);
            if ((repCh == ch) || (Character.toLowerCase(repCh) == ch)){
                int loc = phrase.indexOf(Character.toString(ch),i)+1;
                if (loc%2==0){
                    repCh = '+';
                }
                else {
                    repCh = '*';
                }
            }
            ret.append(repCh);
        }
        return ret.toString();
    }
    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
