
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences(String stringa, String stringb){
        boolean result = false;
        int first_index = stringb.indexOf(stringa);
        int second_index = stringb.indexOf(stringa, first_index + stringa.length());
        if ((first_index!=-1) && (second_index!=-1)){
            result = true;
        }
        return result;
    }
    public String lastPart(String stringa, String stringb){
        String result = stringb;
        int first_index = stringb.indexOf(stringa);
        if (first_index!=-1){
            result = stringb.substring(first_index + stringa.length(), stringb.length());
        }
        return result;
    }
    public void testing (){
        String[] stringa = {"an","foo","bar"};
        String[] stringb = {"banana","forest","barabar"};
        for(String stra : stringa){
            for(String strb: stringb){
                System.out.println("Is "+ stra + " contained in " + strb + "? - " + twoOccurences(stra, strb));
                System.out.println("The part of the string after "+ stra + " in " + strb + " is " + lastPart(stra, strb));
            }
        }
    }
}