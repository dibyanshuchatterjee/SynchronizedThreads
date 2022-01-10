import java.util.ArrayList;

public class ThreadTestForSolution_2 {
    public static void main(String[] args) {
        NumberSolution_2 thread1 = new NumberSolution_2(new ArrayList<Integer>());
        NumberSolution_2 thread2 = new NumberSolution_2(new ArrayList<Integer>());
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
