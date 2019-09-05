
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    public int actualLength(String word){
        int length = word.length();
        if (Character.isLetter(word.charAt(length - 1)) == false){
            length -= 1;
        }
        if (Character.isLetter(word.charAt(0)) == false){
            length -= 1;
        }
        if (length < 0){
            return 0;
        }
        return length;
    }
    public int[] countWordLengths(FileResource resource, int[] counts){
        for (String word:resource.words()){
            int length = actualLength(word); 
            if (length >= counts.length){
                counts[counts.length - 1] += 1;
            }
            else{
                counts[length] += 1;
            }
        }
        return counts;
    }
    public int indexOfMax(int[] values){
        int indexMax = 0;
        for (int k=0; k<values.length; k++){
            if (values[k] >= values[indexMax]){
                indexMax = k;
            }
        }
        return indexMax;
    }
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        counts = countWordLengths(fr, counts);
        for (int k=1; k<counts.length; k++){
            System.out.println("The number of words of length " + k + " is " + counts[k]);
        }
        int indexMax = indexOfMax(counts);
        System.out.println("The element with the largest value is " + indexMax);
    }
}
