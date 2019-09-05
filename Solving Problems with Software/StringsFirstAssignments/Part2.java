
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String result = "";
        if ((dna == dna.toUpperCase()) || (dna == dna.toLowerCase())){
           if (dna == dna.toLowerCase()){
               startCodon = startCodon.toLowerCase();
               stopCodon = stopCodon.toLowerCase();
           }
           int start_index = dna.indexOf(startCodon);
           int stop_index = dna.indexOf(stopCodon, start_index+3);
           if ((start_index == -1) || (stop_index == -1)){
               result = "";
           }
           else{
                if (dna.substring(start_index+3,stop_index).length() %3 == 0){
                    result = dna.substring(start_index,stop_index+3);
                }
                else{
                    result = "";
                }
           } 
        }
        return result;
    }
    
    public void testSimpleGene(){
        String startCodon = "ATG";
        String stopCodon = "TAA";
        String[] strArrayDNA = {"ATGTAA","ATGBCC","BCGTAA","ATGBCCNHTAA","ATGNDCTAA","atgbcctaa"}; 
        for(String dna: strArrayDNA){
            System.out.println("The DNA string in " + dna + " is: " + findSimpleGene(dna, startCodon, stopCodon));
        }
    }
}
