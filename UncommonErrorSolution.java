public class UncommonErrorSolution {

    public static void main(String[] args) {
        try {
            int result = 10 / 0; // ArithmeticException
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.err.println("ArithmeticException caught: Division by zero.  Result set to 0.");
            //Handle the exception gracefully. For example, set a default value
             //int result = 0;
        } finally {
            System.out.println("Finally block executed");
        }

        String str = null;
        try {
            int length = (str != null) ? str.length(): 0; // NullPointerException prevention
            System.out.println("String length: " + length);
        } catch (NullPointerException e) {
            System.err.println("NullPointerException caught: String was null. Length set to 0.");
        }

        // Deadlock Solution: restructure code to avoid circular dependencies
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock1");
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
            synchronized (lock2) {
                System.out.println("Thread 1: Holding lock2");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock2");
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            synchronized (lock1) {
                System.out.println("Thread 2: Holding lock1");
            }
        });

        thread1.start();
        thread2.start();
    }
}