
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part4 {
    public void url_finder(String url){
    URLResource ur = new URLResource(url);
    for (String word : ur.words()){
        if (word.toLowerCase().contains("youtube.com")){
            int opening_quotes_index = word.indexOf("\"");
            int closing_quotes_index = word.indexOf("\"", opening_quotes_index+1);
            //System.out.println(word + ", opening quote index - " + opening_quotes_index  + ", closing quote index - " + closing_quotes_index);
            System.out.println(word.substring(opening_quotes_index+1, closing_quotes_index));
        }
    }
    }
    public void testing(){
        url_finder("http://www.dukelearntoprogram.com/course2/data/manylinks.html");    
    }
}
