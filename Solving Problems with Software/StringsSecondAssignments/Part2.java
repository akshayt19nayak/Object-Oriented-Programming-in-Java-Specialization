
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int currIndex = 0;
        int count = 0;
        while (true){
            currIndex = stringb.indexOf(stringa, currIndex);
            if (currIndex == -1){
                break;
            }
            currIndex += stringa.length();
            count += 1;
        }
        return count;
    }
    public void testHowMany(){
        System.out.println("The count of substring AA" + " in ATGAAAATG is " + howMany("AA", "ATGAAAATG")); 
    }
}
