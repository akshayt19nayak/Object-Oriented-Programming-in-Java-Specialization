
/**
 * Write a description of Class1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class Class1 {
    public void totalBirths (FileResource fr) {
        int totalNames = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("M")) {
                totalBoys += 1;
            }
            else {
                totalGirls += 1;
            }
            totalNames += 1;
        }
        System.out.println("total births = " + totalNames);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }
    public void testTotalBirths (){
	//FileResource fr = new FileResource();
	FileResource fr = new FileResource("data/yob1905.csv");
	totalBirths(fr);
    }
    public int getRank (int year, String name, String gender){
        int rank = 0;
        int found = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rec.get(0).equals(name)) {
                    found += 1;
                    break;
                }
            }
        }
        if (found == 1){
            return rank;
        }
        else{
            return -1;
        }
    }
    public void testGetRank (){
        System.out.println("The rank of the name is " + getRank(2012, "Mason", "F"));
    }
    public String getName (int year, int rank,  String gender){
        int count = 0;
        String name = null;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                count += 1;
                if (count == rank) {
                    name = rec.get(0);
                    break;
                }
            }
        }
        if (! name.equals(null)){
            return name;
        }
        else{
            return "NO NAME";
        }
    }
    public void testGetName (){
        System.out.println("The name at the rank is " + getName(2012, 2300, "F"));
    }
    public void whatIsNameInYear (String name, int year, int  newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    public void testWhatIsNameInYear (){
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }
    public int yearOfHighestRank (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int minRank = 0;
        int minRankYear = -1;
        for (File f: dr.selectedFiles()){
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int rank = getRank(year, name, gender);
            if (rank != -1){ 
                if (minRankYear == -1){
                    minRank = rank;
                    minRankYear = year;
                }
                else if ((rank < minRank)){
                    minRank = rank;
                    minRankYear = year;
                }
            }
        }
        return minRankYear;
    }
    public void testYearOfHighestRank (){
        System.out.println("The year with the highest rank is " + yearOfHighestRank("Mason", "M"));
    }
    public double getAverageRank (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int count = 0;
        int sumRank = 0;
        for (File f: dr.selectedFiles()){
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int rank = getRank(year, name, gender);
            if (rank != -1){ 
                count += 1;
                sumRank += rank;
            }
        }
        if (count == 0){
            return -1;
        }
        return ((double) sumRank)/count;
    }
    public void testGetAverageRank(){
        System.out.println("The average rank is " + getAverageRank("Jacob", "M"));
    }
    public int getTotalBirthsRankedHigher (int year, String name, String gender){
        int rank = getRank(year, name, gender);
        int count = 0;
        int totalBirths = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                count += 1;
                if (count == rank){
                    break;
                }
                totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        return totalBirths;
    }
    public void testGetTotalBirthsRankedHigher (){
        System.out.println("The total number of births ranked higher is " + getTotalBirthsRankedHigher(2012, "Ethan", "M"));
    }
}
