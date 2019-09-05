import java.util.*;
import edu.duke.*;
import java.io.File;
public class VigenereBreaker {
    private String sliceString(String message, int whichSlice, int totalSlices){
        StringBuilder sb = new StringBuilder();
        for (int i=whichSlice; i<message.length(); i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    private int[] tryKeyLength(String encrypted, int klength, char mostCommon){
        int[] dkey = new int[klength];
        for (int k=0; k<klength; k++){
            String encryptedSliced = sliceString(encrypted, k, klength);
            CaesarCracker cracker = new CaesarCracker(mostCommon);
            dkey[k] = cracker.getKey(encryptedSliced);
        }
        return dkey;
    }
    private HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for(String line: fr.lines()){
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    private int countWords(String message, HashSet<String> dictionary){
        int legit = 0;
        for (String word: message.split("\\W+")){
            if (dictionary.contains(word.toLowerCase())){
                legit += 1;
            }
        }
        return legit;
    }
    private String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommonChar, boolean print){
        int tries = 100;
        int maxLegit = 0;
        int maxKLength = 0;
        for (int kLength=1; kLength<=tries; kLength++){
            int[] dkey = tryKeyLength(encrypted, kLength, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(dkey);
            int legit = countWords(vc.decrypt(encrypted), dictionary);
            if (legit > maxLegit){
                maxLegit = legit;
                maxKLength = kLength;
            }
        }
        System.out.println("The total number of valid words is " + maxLegit);
        int[] dkey = tryKeyLength(encrypted, maxKLength, mostCommonChar);
        if (print){
            System.out.println("The key is " + Arrays.toString(dkey));
            System.out.println("The key length is " + dkey.length);
        }
        VigenereCipher vc = new VigenereCipher(dkey);
        return vc.decrypt(encrypted);
    }
    private char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
        for(String word: dictionary){
            for(int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                if (charCount.containsKey(c)){
                    charCount.put(c, charCount.get(c)+1);
                }
                else{
                    charCount.put(c, 1);
                }
            }
        }
        int maxOcc = 0;
        char mostCommonChar = 0;
        for (Character c: charCount.keySet()){
            if (charCount.get(c) > maxOcc){
                maxOcc = charCount.get(c);
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
    }
    private void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxLegit = 0;
        String maxLanguage = null;
        for (String language: languages.keySet()){
            System.out.println("The language is - " + language);
            char mostCommonCharLang = mostCommonCharIn(languages.get(language));
            String decrypted = breakForLanguage(encrypted, languages.get(language), mostCommonCharLang, false);
            int legit = countWords(decrypted, languages.get(language));
            if (legit > maxLegit){
                maxLegit = legit;
                maxLanguage = language;
            }
        }
        System.out.println("\nThe correct language is " + maxLanguage);
        char mostCommonCharLang = mostCommonCharIn(languages.get(maxLanguage));
        String decrypted = breakForLanguage(encrypted, languages.get(maxLanguage), mostCommonCharLang, true);
        System.out.println("\nThe decrypted message is:");
        System.out.println(decrypted.substring(0,200));
    }
    public void breakVigenere(){
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            languages.put(f.getName(), readDictionary(fr));
            System.out.println("The " + f.getName() + " dictionary has been read!");
        }
        FileResource fr = new FileResource();
        String message = fr.asString();
        breakForAllLangs(message, languages);
        //FileResource dictionaryFile = new FileResource();
        //HashSet<String> dictionary = readDictionary(dictionaryFile);
        //System.out.println(breakForLanguage(message, dictionary).substring(0,200));
        //int[] dkey = tryKeyLength(message, 38, 'e');
        //VigenereCipher vc = new VigenereCipher(dkey);
        //int legit = countWords(vc.decrypt(message), dictionary);
        //System.out.println("The total number of valid words is " + legit);
        //int[] dkey = tryKeyLength(message, 4, 'e');
        //VigenereCipher vc = new VigenereCipher(dkey);
        //System.out.println(vc.decrypt(message).substring(0,200));
        //System.out.println("The key is " + Arrays.toString(dkey));
    }
}
