package PatternsProject.chainOfResponsibility;

/* 
Refactoring the Chain of Responsibility pattern
We have a program that has a logging system that works as follows:
- if the event's log level is FATAL, the manager is called, the CEO receives an SMS, a message is displayed on the console, and an entry is made in the log file;
- if the event's log level is ERROR, the CEO receives an SMS, a message is displayed on the console, and an entry is made in the log file;
- if the event's log level is WARN, a message is displayed on the console and an entry is made in the log file;
- if the event's log level is INFO, a log entry is made in the log file.

The program implements the "chain of responsibility" pattern. Study it carefully. The FileLogger, ConsoleLogger, SmsLogger, and PhoneLogger classes have a lot of duplicate code. Pull all the duplicate code up to an AbstractLogger abstract class.
In refactoring, pulling up means to move fields, methods, constructors from all child classes into one common parent class. In doing so, the code that is moved to the parent class is removed from the child classes.

The program's behavior should not change.



*/

public class Solution {
    public static void main(String[] args) {
        Logger logger3 = new PhoneLogger(Level.FATAL);
        Logger logger2 = new SmsLogger(Level.ERROR);
        Logger logger1 = new ConsoleLogger(Level.WARN);
        Logger logger0 = new FileLogger(Level.INFO);

        logger3.setNext(logger2);
        logger2.setNext(logger1);
        logger1.setNext(logger0);

        logger3.inform("Everything is OK", Level.INFO);
        logger3.inform("We found a bug", Level.WARN);
        logger3.inform("Database connection error", Level.ERROR);
        logger3.inform("System shut down", Level.FATAL);
    }
}