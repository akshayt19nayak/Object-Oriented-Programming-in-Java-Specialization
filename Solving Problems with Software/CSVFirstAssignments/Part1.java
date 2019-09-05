
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
    public String countryInfo (CSVParser parser, String country){
        String stringReturn = "NOT FOUND";
        for(CSVRecord record : parser){
            if (record.get("Country").equals(country)){
                stringReturn = record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return stringReturn;
            }
        }
        return stringReturn;
    }
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters (CSVParser parser, String exportItem){
        int countExport = 0;
        for(CSVRecord record: parser){
            if (record.get("Exports").contains(exportItem)){
                countExport += 1;
            }
        }
        return countExport;
    }
    public void bigExporters (CSVParser parser, String amount){
        for(CSVRecord record: parser){
            if (record.get("Value (dollars)").length() > amount.length()){
                System.out.print(record.get("Country") + " ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }
    public void tester (){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "cotton", "flowers");
        System.out.println(numberOfExporters(parser, "cocoa"));
        //bigExporters(parser, "$999,999,999,999");
    }
}
