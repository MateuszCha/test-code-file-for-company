package ThreadState;

public class LoggingStateThread extends Thread {

    private Thread thread;
    public LoggingStateThread(Thread thread){
        System.out.println(thread.getState());
        this.thread = thread;
    }
    @Override
    public void run() {
        State lastState = thread.getState();

        while (true) {
            State state = thread.getState();
            if (!state.equals(lastState)) {
                lastState = thread.getState();
                System.out.println(state);
            }
            if (state.equals(State.TERMINATED)) {
                //System.out.println(state);
                break;
            }
        }
    }

    /*
    public void run(){
        do {
            switch (this.thread.getState()) {
                case NEW:
                    while (this.thread.getState() == State.NEW) {
                    }
                    break;
                case RUNNABLE:
                    System.out.println(this.thread.getState());
                    while (this.thread.getState() == State.RUNNABLE) {
                    }
                    break;
                case BLOCKED:
                    System.out.println(this.thread.getState());
                    while (this.thread.getState() == State.BLOCKED) {
                    }
                    break;
                case WAITING:
                    System.out.println(this.thread.getState());
                    while (this.thread.getState() == State.WAITING) {
                    }
                    break;
                case TIMED_WAITING:
                    System.out.println(this.thread.getState());
                    while (this.thread.getState() == State.TIMED_WAITING) {
                    }
                    break;
            }
            }while (this.thread.getState() != State.TERMINATED);
            System.out.println(this.thread.getState());
    }

     */
}
