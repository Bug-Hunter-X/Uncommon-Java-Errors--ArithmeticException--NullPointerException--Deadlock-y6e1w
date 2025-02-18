public class UncommonErrorExample {

    public static void main(String[] args) {
        try {
            int result = 10 / 0; // ArithmeticException
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.err.println("ArithmeticException caught: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed");
        }

        String str = null;
        try {
            int length = str.length(); // NullPointerException
            System.out.println("String length: " + length);
        } catch (NullPointerException e) {
            System.err.println("NullPointerException caught: " + e.getMessage());
        }

        // Deadlock Scenario (requires multiple threads)
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Thread 1: Holding lock1 and lock2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("Thread 2: Holding lock2 and lock1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}