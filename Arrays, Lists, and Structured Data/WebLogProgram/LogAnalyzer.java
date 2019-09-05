
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer{
    private ArrayList<LogEntry> records;
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();    
    }   
    public void readFile(String filename) {
        FileResource fr =  new FileResource(filename);
        for (String line: fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records){
            String IP = le.getIpAddress();
            if (! uniqueIPs.contains(IP)){
                uniqueIPs.add(IP);
            }
        }
        return uniqueIPs.size();
    }
    public void printAllHigherThanNum(int num){
        for (LogEntry le: records){
            int statusCode = le.getStatusCode();
            if (statusCode > num){
                System.out.println(le);
            }
        }
    }
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPVisitsOnDay = new ArrayList<String>();
        for (LogEntry le: records){
            String date = le.getAccessTime().toString();
            if (date.contains(someday)){
                String IP = le.getIpAddress();
                if (! uniqueIPVisitsOnDay.contains(IP)){
                    uniqueIPVisitsOnDay.add(IP);
                }
            }
        }
        return uniqueIPVisitsOnDay;
    }
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
        for (LogEntry le: records){
            int statusCode = le.getStatusCode();
            if ((statusCode >= low) && (statusCode <= high)){
                String IP = le.getIpAddress();
                if (! uniqueIPsInRange.contains(IP)){
                    uniqueIPsInRange.add(IP);
                }
            }
        }
        return uniqueIPsInRange.size();
    }
    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> countVisits = new HashMap<String, Integer>();
        for (LogEntry le: records){
            String IP = le.getIpAddress();
            if (countVisits.containsKey(IP)){
                int count = countVisits.get(IP);
                countVisits.put(IP,count+1);
            }
            else{
                countVisits.put(IP,1);
            }
        }
        return countVisits;
    }
    public int mostNumberVisitsByIP(HashMap<String, Integer> countVisits){
        String maxKey = null;
        for (String key: countVisits.keySet()){
            if (maxKey == null){
                maxKey = key;
            }
            else if(countVisits.get(key) > countVisits.get(maxKey)){
                maxKey = key;
            }
        }
        return countVisits.get(maxKey);
    }
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> countVisits){
        ArrayList<String> mostVisits = new ArrayList<String>();
        int maxCountVisits = mostNumberVisitsByIP(countVisits);
        for (String key: countVisits.keySet()){
            if (countVisits.get(key)==maxCountVisits){
                mostVisits.add(key);
            }
        }
        return mostVisits;
    }
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> dayIPs = new HashMap<String, ArrayList<String>>();
        for (LogEntry le: records){
            String date = le.getAccessTime().toString().substring(4,10);
            String IP = le.getIpAddress();
            if (dayIPs.containsKey(date)){
                ArrayList<String> IPs = dayIPs.get(date);
                IPs.add(IP);
                dayIPs.put(date, IPs);
            }
            else{
                ArrayList<String> IPs = new ArrayList<String>();
                IPs.add(IP);
                dayIPs.put(date, IPs);
            }
        }
        return dayIPs;
    }
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayIPs){
        String maxKey = null;
        for (String key: dayIPs.keySet()){
            if (maxKey == null){
                maxKey = key;
            }
            else if(dayIPs.get(key).size() > dayIPs.get(maxKey).size()){
                maxKey = key;
            }
        }
        return maxKey;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayIPs, String date){
        ArrayList<String> IPs =  dayIPs.get(date);
        HashMap<String, Integer> countVisits = new HashMap<String, Integer>();
        for (String IP: IPs){
            if (countVisits.containsKey(IP)){
                int count = countVisits.get(IP);
                countVisits.put(IP,count+1);
            }
            else{
                countVisits.put(IP,1);
            }
        }
        return iPsMostVisits(countVisits);
    }
}