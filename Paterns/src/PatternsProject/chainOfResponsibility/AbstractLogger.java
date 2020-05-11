package PatternsProject.chainOfResponsibility;

abstract public class AbstractLogger implements Logger {
    int level;
    Logger next;

    abstract public void info(String message);

    public void inform(String message, int level) {
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }
    }
    public void setNext(Logger next) {
        this.next = next;
    }
}
