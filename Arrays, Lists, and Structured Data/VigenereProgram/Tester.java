
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Tester {
    public void testCaesarCipher(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(3);
        System.out.println(cc.encrypt(message));
        System.out.println(cc.decrypt(cc.encrypt(message)));
    }
}
