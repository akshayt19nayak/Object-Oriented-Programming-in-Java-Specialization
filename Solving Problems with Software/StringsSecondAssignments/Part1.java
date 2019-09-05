
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1{
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        int stopIndex;
        int dist;
        int temp = startIndex;
        while (true){
            stopIndex = dna.indexOf(stopCodon, temp);
            dist = stopIndex - startIndex;
            if (stopIndex == -1){
                return dna.length();
            }
            else{
                if (dist%3 == 0){
                    return stopIndex;
                }
                else{
                    temp = stopIndex + 1;
                }
            }
        }
    }
    public String findGene (String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int taaStopIndex = findStopCodon(dna, startIndex, "TAA");
        int tagStopIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaStopIndex = findStopCodon(dna, startIndex, "TGA");
        int[] stopIndexes = {taaStopIndex, tagStopIndex, tgaStopIndex};
        int minIndex = taaStopIndex;
        for (int stopIndex: stopIndexes){
            if (stopIndex < minIndex){
                minIndex = stopIndex;
            }
        }
        if (minIndex == dna.length()){
            return "";
        }
        else {
            return dna.substring(startIndex, minIndex+3);
        }
    }
    public void testFindStopCodon(){
        String[] dnaArray = {"ATGTAA", "ATGCBTAA", "ATGTAGBCGTAA", "ABATG"};
        int startIndex = 0;
        String stopCodon = "TAA";
        for(String dna: dnaArray){
            System.out.println("The stop codon for the string " + dna + " of length " + dna.length() + " is at index - " + findStopCodon(dna, startIndex, stopCodon));
        }    
    }
    public void testFindGene(){
        String dna = "TAA";
        System.out.println("The gene in the string " + dna + " is - " + findGene(dna));  
        dna = "ATGTAA";
        System.out.println("The gene in the string " + dna + " is - " + findGene(dna));
        dna = "ATGTAGTAATGA";
        System.out.println("The gene in the string " + dna + " is - " + findGene(dna));  
        dna = "ATGBCC";
        System.out.println("The gene in the string " + dna + " is - " + findGene(dna));  
        dna = "ATGABCDEFTAA";
        System.out.println("The gene in the string " + dna + " is - " + findGene(dna));  
    }
    public void printAllGenes(String dna){
        System.out.println("The genes in the string " + dna + " are:");
        int currIndex = 0;
        while (true){
            if (currIndex >= dna.length()){
                break;
            }
            String gene = findGene(dna.substring(currIndex, dna.length()));
            if (gene == ""){
                break;
            }
            System.out.printf("\n%s", gene);
            int startIndex = dna.indexOf(gene, currIndex);
            int stopIndex = startIndex + gene.length();
            currIndex = stopIndex + 1;
        }
    }
}
