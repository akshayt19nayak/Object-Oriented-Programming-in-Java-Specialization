
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
    }
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The number of unique IPs is " + la.countUniqueIPs());
    }
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The status codes are:");
        la.printAllHigherThanNum(400);
    }
    public void testUniqueIPVisitsOnDay(){ 
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The unique IP visits on the specified day are: " + la.uniqueIPVisitsOnDay("Sep 24"));
    }
    public void testUniqueIPsInRange(){ 
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The number of unique IP visits in the specified status code range are: " + la.countUniqueIPsInRange(400,499));
    }
    public void testMostNumberVisitsByIP(){ 
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The most number of visits by any IP is: " + la.mostNumberVisitsByIP(la.countVisitsPerIP()));
    }
    public void testIPsMostVisits(){ 
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The IPs with most visits are: " + la.iPsMostVisits(la.countVisitsPerIP()));
    }
    public void testIPsForDays(){ 
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The days with the IPs that visited them are: " + la.iPsForDays());
    }
    public void testDayWithMostIPVisits(){ 
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The day with the most IP visits are: " + la.dayWithMostIPVisits(la.iPsForDays()));
    }
    public void testIPsWithMostVisitsOnDay(){ 
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The IPs with the most visits on the given day are: " + la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 29"));
    }
}
