
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
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
    public void testPrintAllGenes (){
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        printAllGenes(dna.toUpperCase());
    }
    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        //System.out.println("The genes in the string " + dna + " are:");
        int currIndex = 0;
        while (true){
            if (currIndex >= dna.length()){
                break;
            }
            String gene = findGene(dna.substring(currIndex, dna.length()));
            if (gene == ""){
                break;
            }
            //System.out.printf("\n%s", gene);
            sr.add(gene);
            int startIndex = dna.indexOf(gene, currIndex);
            int stopIndex = startIndex + gene.length();
            currIndex = stopIndex + 1;
        }
        return sr;
    }
    public void testGetAllGenes(){
        String dna = "ATGATAGCCTAACCATGTAGCC";
        StorageResource sr = getAllGenes(dna);
        for (String s: sr.data()){
            System.out.println(s);
        }
    }
    public int substringCount(String sub, String str){
        int index = 0;
        int count = 0;
        while (true){
            index = str.indexOf(sub, index);
            if (index == -1){
                break;
            }
            index += sub.length();
            count += 1;
        }
        return count;
    }
    public float cgRatio(String dna){
        int cCount = substringCount("C", dna);
        int gCount = substringCount("G", dna);
        float cgRatio_ = (float)(cCount + gCount)/dna.length();
        return(cgRatio_);
    }
    public int countCTG(String dna){
        return(substringCount("CTG", dna));
    }    
    public void processGenes(StorageResource sr){
        for(String str: sr.data()){
            int countLength = 0;
            int countCGRatio = 0;
            int maxLength = 0;
            str = str.toUpperCase();
            //System.out.println("The DNA string is " + str + " and the genes are:");
            StorageResource srGenes = getAllGenes(str);
            System.out.println("The number of genes is " + srGenes.size());
            System.out.println("The genes are:");
            for (String gene: srGenes.data()){ 
                /*if (gene.length() > 9){
                    System.out.println("\n" + gene);
                    countLength += 1;
                }*/
                if (gene.length() > maxLength){
                    maxLength = gene.length();
                }
                if (gene.length() > 60){
                    System.out.println("\n" + gene);
                    countLength += 1;
                }
                if (cgRatio(gene) > 0.35){
                    System.out.println("\n" + gene);
                    countCGRatio += 1;
                }
            }
            System.out.println("\n");
            System.out.println("Count of genes that have a length greater than 60 - " + countLength);
            System.out.println("Count of genes that have a cgRatio greater than 0.35 - " + countCGRatio);
            System.out.println("The length of the longest gene is - " + maxLength);
        }
    }
    public void testProcessGenes(){
        StorageResource sr = new StorageResource();
        //FileResource fr = new FileResource("brca1line.fa");
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        sr.add(dna);
        int countCTG_ = countCTG(dna);
        System.out.println("countCTG is " + countCTG_);
        //sr.add("CCATGCCCBBBTAGCCATGTAG");
        //sr.add("CCATGTAGCCATGTAA");
        //sr.add("ATGTAACCATGCCCTAG");
        //sr.add("CCATGTAG");
        processGenes(sr);
    }
}