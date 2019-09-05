
/**
 * Write a description of GladLibHashMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class GladLibHashMap {
    private HashMap<String, ArrayList<String>> myMap; 
    private Random myRandom;
    private ArrayList<String> seenWords;
    private ArrayList<String> consideredCategories;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    public GladLibHashMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        seenWords = new ArrayList<String>();
        consideredCategories = new ArrayList<String>();
    }
    public GladLibHashMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        seenWords = new ArrayList<String>();
        consideredCategories = new ArrayList<String>();
    }
    private void initializeFromSource(String source) {
        myMap.clear();
        String[] categories = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for (String category: categories){
            ArrayList<String> categoryList = readIt(source+"/"+category+".txt");
            myMap.put(category, categoryList);
        }
    }
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        if (consideredCategories.indexOf(w.substring(first+1,last)) == -1){
            consideredCategories.add(w.substring(first+1,last));
        }
        if (seenWords.indexOf(sub) == -1){
            seenWords.add(sub);
        }
        else {
            while (seenWords.indexOf(sub) != -1){
                sub = getSubstitute(w.substring(first+1,last));
            }
            seenWords.add(sub);
        }
        return prefix+sub+suffix;
    }
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    private int totalWordsInMap(){
        int totalWords = 0;
        for (String key: myMap.keySet()){
            totalWords += myMap.get(key).size();
        }
        return totalWords;
    }
    private int totalWordsConsidered(){
        int wordsConsidered = 0;
        for(String category: consideredCategories){
            if (myMap.containsKey(category)){
                wordsConsidered += myMap.get(category).size();
            }
        }
        return wordsConsidered;
    }
    public void makeStory(){
        System.out.println("\n");
        seenWords.clear();
        consideredCategories.clear();
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nThe total number of words that were replaced were: " + seenWords.size());
        //System.out.println("\nThe new words are: " + seenWords);
        System.out.println("The total words in the map are: " + totalWordsInMap());
        System.out.println("The categories are: " + consideredCategories);
        System.out.println("The total words considered are: " + totalWordsConsidered());
    }
}