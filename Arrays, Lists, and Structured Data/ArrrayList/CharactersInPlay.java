
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> nameCharacters;
    private ArrayList<Integer> nameCount;
    public CharactersInPlay(){
        nameCharacters = new ArrayList<String>();
        nameCount = new ArrayList<Integer>();
    }
    public void update(String person){
        int index = nameCharacters.indexOf(person);
        if (index == -1){
            nameCharacters.add(person);
            nameCount.add(1);
        }
        else{
            int count = nameCount.get(index);
            nameCount.set(index, count+1);
        }
    }
    public void findAllCharacters(){
        nameCharacters.clear();
        nameCount.clear();
        FileResource fr = new FileResource();
        for (String line: fr.lines()){
            for (int k=0; k<line.length(); k++){
                if (line.charAt(k) == '.'){
                    update(line.substring(0,k));
                }
            }
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        for (int k=0; k<nameCount.size(); k++){
            if ((nameCount.get(k) >= num1) && (nameCount.get(k) <= num2)){
                System.out.println(nameCharacters.get(k) + "\t" + nameCount.get(k));
            }
        }
    }
    public int findIndexOfMax(){
        int maxIndex = 0;
        for (int k=0; k<nameCount.size(); k++){
            if (nameCount.get(k)>nameCount.get(maxIndex)){
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    public void tester(){
        findAllCharacters();
        /*for (int k=0; k<nameCharacters.size(); k++){
            if (nameCount.get(k) > 1){
                System.out.println(nameCharacters.get(k) + "\t" + nameCount.get(k));
            }
            System.out.println(nameCharacters.get(k) + "\t" + nameCount.get(k));
        }*/
        System.out.println("\nCharacters with the main parts are:");
        charactersWithNumParts(10, 15);
        System.out.println("\nCharacter with the most speaking part is:");
        int maxIndex = findIndexOfMax();
        System.out.println(nameCharacters.get(maxIndex) + "\t" + nameCount.get(maxIndex));
    }
}
