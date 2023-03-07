package queue;

import java.util.Scanner;

public class QueueClient {

    /**
     * A test client.
     */
    public static void main(String[] args) {
        Queue<String> q = new Queue<String>();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String item = scanner.next();
            if (!item.equals("-")) {
                q.enqueue(item);
            } else if (!q.isEmpty()) {
                System.out.println(q.dequeue() + " ");
            }
        }
        System.out.println(q.size());
    }
}
