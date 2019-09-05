
/**
 * Write a description of WordsinFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.File;
import edu.duke.*;
public class WordsinFiles {
    private HashMap<String, ArrayList<String>> wordFileNames;
    public WordsinFiles(){
        wordFileNames = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String word: fr.words()){
            if (! wordFileNames.containsKey(word)){
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(f.getName());
                wordFileNames.put(word, fileList);
            }
            else{
                ArrayList<String> fileList = wordFileNames.get(word);
                if (!fileList.contains(f.getName())){
                    fileList.add(f.getName());
                    wordFileNames.put(word, fileList);
                }
            }
        }
    }
    private void buildWordFileMap(){
        wordFileNames.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    private int maxNumber(){
        int max = 0;
        for (String key: wordFileNames.keySet()){   
            int temp = wordFileNames.get(key).size();
            if (temp > max){
                max = temp;
            }
        }
        return max;
    }
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for (String key: wordFileNames.keySet()){   
            int temp = wordFileNames.get(key).size();
            if (temp == number){
                words.add(key);
            }
        }
        return words;
    }
    private void printFilesIn(String word){
        System.out.println("\nword: " + word);
        for (String file: wordFileNames.get(word)){
            System.out.println(file);
        }
    }
    public void tester(){
        buildWordFileMap();
        System.out.println("Max number of files any word is in is " + maxNumber());
        System.out.println("The number of words that occured the specified number of times is " + wordsInNumFiles(7).size());
        printFilesIn("tree");
        /*
        for(String word: wordsInNumFiles(maxNumber())){
            printFilesIn(word);
        }*/
    }
}
