
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
    public String findSimpleGene(String dna){
        int start_index = dna.indexOf("ATG");
        int stop_index = dna.indexOf("TAA", start_index+3);
        String result = "";
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
        return result;
    }
    
    public void testSimpleGene(){
        String[] strArrayDNA = {"ATGTAA","ATGBCC","BCGTAA","ATGBCCNHTAA","ATGNDCTAA"}; 
        for(String dna: strArrayDNA){
            System.out.println("The DNA string in " + dna + " is: " + findSimpleGene(dna));
        }
    }
}
