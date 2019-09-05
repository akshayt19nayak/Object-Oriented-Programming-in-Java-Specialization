
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CodonCount {    
    private HashMap<String,Integer> countDNA;
    public CodonCount(){
        countDNA = new HashMap<String,Integer>();
    }
    public void buildCodonMap(int start, String dna){
        countDNA.clear();
        while ((start + 3) < dna.length()){
            int end = start + 3;
            String sub = dna.substring(start, end);
            start = end;
            if (countDNA.containsKey(sub)){
                countDNA.put(sub, countDNA.get(sub)+1);
            }
            else{
                countDNA.put(sub, 1);
            }
        }
    }
    public String getMostCommonCodon(){
        String mostCommonCodon = null;
        for (String key: countDNA.keySet()){
            if (mostCommonCodon == null){
                mostCommonCodon = key;
            }
            else if (countDNA.get(key) > countDNA.get(mostCommonCodon)){
                mostCommonCodon = key;
            }
        }
        return mostCommonCodon;
    }
    public void printCodonCounts(int start, int end){
        for (String key: countDNA.keySet()){
            if ((countDNA.get(key)>=start) && (countDNA.get(key)<=end)){
                System.out.println(key + "\t" + countDNA.get(key));
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        int[] readingFrames = {0,1,2};
        for (int start: readingFrames){
            buildCodonMap(start, dna);
            System.out.println("\nThe number of unique codons in reading frame " + start + " is " + countDNA.size());
            System.out.println("The most common codon is " + getMostCommonCodon() + " with a count of " + countDNA.get(getMostCommonCodon()));
            System.out.println("The codons whose counts are between the specified limits are:");
            printCodonCounts(6,7);
        }
    }
}
