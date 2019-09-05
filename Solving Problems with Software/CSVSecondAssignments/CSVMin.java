
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class CSVMin {
    public CSVRecord smallestOfTwo(CSVRecord currentRecord, CSVRecord lowestRecord, String field){
        if (lowestRecord == null){
            lowestRecord = currentRecord;
            }
        else {
            double currentValue = Double.parseDouble(currentRecord.get(field));
            double lowestValue = Double.parseDouble(lowestRecord.get(field));
            if (currentValue < lowestValue){
                lowestRecord = currentRecord;
            }
        }
        return lowestRecord;
    }
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestRecord = null;
        for(CSVRecord currentRecord: parser){
            if (! currentRecord.get("TemperatureF").equals("-9999")){
                coldestRecord = smallestOfTwo(currentRecord, coldestRecord, "TemperatureF");
            }
        }
        return coldestRecord;
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        System.out.println("The coldest temperature was " + coldestRecord.get("TemperatureF") + " at " + coldestRecord.get("DateUTC"));
    }
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestRecord = null;
        String coldestFile = null;
        double firstTemp = 9999;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentMinRecord = coldestHourInFile(parser);
            coldestRecord = smallestOfTwo(currentMinRecord, coldestRecord, "TemperatureF");
            double currentMinTemp = Double.parseDouble(currentMinRecord.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
            if (coldestFile == null){
                coldestFile = f.getName();
            }
            else {
                if ((currentMinTemp == coldestTemp) && (currentMinTemp != firstTemp)){
                    firstTemp = coldestTemp;
                    coldestFile = f.getName();
                }
            }
        }
        return coldestFile;
    }
    public void testFileWithColdestTemperature(){
        String coldestFile = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldestFile);
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + coldestRecord.get("TemperatureF"));
        parser = fr.getCSVParser();
        for (CSVRecord current: parser){
            System.out.println(current.get("DateUTC") + " " + current.get("TemperatureF"));
        }
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord humidestRecord = null;
        for(CSVRecord currentRecord: parser){
            if (! currentRecord.get("Humidity").equals("N/A")){
                humidestRecord = smallestOfTwo(currentRecord, humidestRecord, "Humidity");
            }
        }
        return humidestRecord;
    }
    public void testlowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord humidestRecord = lowestHumidityInFile(parser);
        System.out.println("The lowest humidity was " + humidestRecord.get("Humidity") + " at " + humidestRecord.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord humidestRecord = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentMinRecord = lowestHumidityInFile(parser);
            humidestRecord = smallestOfTwo(currentMinRecord, humidestRecord, "Humidity");
        }
        return humidestRecord;
    }
    public void testlowestHumidityInManyFiles(){
        CSVRecord humidestRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + humidestRecord.get("Humidity") + " at " + humidestRecord.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser parser){
        double sumTemperature = 0;
        int count = 0;
        for(CSVRecord currentRecord: parser){
            if (! currentRecord.get("TemperatureF").equals("-9999")){
                sumTemperature += Double.parseDouble(currentRecord.get("TemperatureF"));
                count += 1;
            }
        }
        return (sumTemperature/count);
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sumTemperature = 0;
        int count = 0;
        for(CSVRecord currentRecord: parser){
            double humidity = Double.parseDouble(currentRecord.get("Humidity"));
            if ((! currentRecord.get("TemperatureF").equals("-9999")) && (humidity >= value)){
                sumTemperature += Double.parseDouble(currentRecord.get("TemperatureF"));
                count += 1;
            }
        }
        if (count==0){
            return 9999;
        }
        return (sumTemperature/count);
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemperature = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (averageTemperature == 9999){
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average Temp when high Humidity is " + averageTemperature);
        }
    }
}