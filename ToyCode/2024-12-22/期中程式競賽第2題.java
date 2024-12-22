import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNumber = 1;

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            scanner.nextLine();

            Stack<String> stack = new Stack<>();
            Queue<String> queue = new LinkedList<>();
            PriorityQueue<String> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

            boolean isStack = true, isQueue = true, isPriorityQueue = true;
            StringBuilder operations = new StringBuilder();

            for (int i = 0; i < n; i++) {
                String command = scanner.next();
                String fruit = null;
                if ("take".equals(command)) {
                    fruit = scanner.next();
                } else if ("throw".equals(command)) {
                    fruit = scanner.next();
                }

                operations.append(String.format("(%d) cmd=%s", i + 1, command));
                if (fruit != null) {
                    operations.append(String.format(", fruit=%s", fruit));
                }
                operations.append("\n");

                if ("throw".equals(command)) {
                    if (fruit != null) {
                        if (isStack) stack.push(fruit);
                        if (isQueue) queue.offer(fruit);
                        if (isPriorityQueue) priorityQueue.offer(fruit);
                    }
                } else if ("take".equals(command)) {
                    if (isStack) {
                        if (stack.isEmpty() || !stack.pop().equals(fruit)) {
                            isStack = false;
                        }
                    }
                    if (isQueue) {
                        if (queue.isEmpty() || !queue.poll().equals(fruit)) {
                            isQueue = false;
                        }
                    }
                    if (isPriorityQueue) {
                        if (priorityQueue.isEmpty() || !priorityQueue.poll().equals(fruit)) {
                            isPriorityQueue = false;
                        }
                    }
                }
            }

            System.out.printf("Case #%d: there are %d operations.%n", caseNumber++, n);
            System.out.print(operations);
         
            StringBuilder result = new StringBuilder();
            if (!isStack) result.append("It is NOT a Stack.\n");
            else result.append("It is a Stack.\n");

            if (!isQueue) result.append("It is NOT a Queue.\n");
            else result.append("It is a Queue.\n");

            if (!isPriorityQueue) result.append("It is NOT a Max Priority Queue.\n");
            else result.append("It is a Max Priority Queue.\n");

            if (!isStack && !isQueue && !isPriorityQueue) {
                result.append("impossible");
            } else if (isStack && isQueue && isPriorityQueue) {
                result.append("not sure: stack, queue or priority queue");
            } else if (isStack && isQueue) {
                result.append("not sure: stack or queue");
            } else if (isStack && isPriorityQueue) {
                result.append("not sure: stack or priority queue");
            } else if (isQueue && isPriorityQueue) {
                result.append("not sure: queue or priority queue");
            } else if (isStack) {
                result.append("stack");
            } else if (isQueue) {
                result.append("queue");
            } else if (isPriorityQueue) {
                result.append("priority queue");
            }

            System.out.printf(result.toString());

            if (scanner.hasNextInt()) {
                System.out.println("\n");
            } else {
                System.out.println();
            }
        }

        scanner.close();
    }
}
