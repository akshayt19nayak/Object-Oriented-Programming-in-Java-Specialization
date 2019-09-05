
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords  = new ArrayList<String>();
        myFreqs  = new ArrayList<Integer>();
    }
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word: fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq+1);
            }
        }
    }
    public int findIndexOfMax(){
        int maxIndex = 0;
        for (int k=0; k<myFreqs.size(); k++){
            if (myFreqs.get(k)>myFreqs.get(maxIndex)){
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    public void tester(){
        findUnique();
        /*for (int k=0; k<myWords.size(); k++){
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }*/
        System.out.println("The number of unique words is " + myWords.size());
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs the most is '" + myWords.get(maxIndex) + "' which occurs " + myFreqs.get(maxIndex) + " times");
    }
}
