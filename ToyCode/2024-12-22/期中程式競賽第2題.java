import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNumber = 1;

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();

            if (n == 0) {
                break;
            }

            if (caseNumber > 1) {
                System.out.println();
            }

            Stack<String> stack = new Stack<>();
            Queue<String> queue = new LinkedList<>();
            PriorityQueue<String> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

            boolean isStack = true, isQueue = true, isPriorityQueue = true;
            StringBuilder operations = new StringBuilder();

            for (int i = 0; i < n; i++) {
                String command = scanner.next();
                String fruit = scanner.next();

                operations.append(String.format("(%d) cmd=%s, fruit=%s%n", i + 1, command, fruit));

                if ("throw".equals(command)) {
                    if (isStack) stack.push(fruit);
                    if (isQueue) queue.offer(fruit);
                    if (isPriorityQueue) priorityQueue.offer(fruit);
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

            StringBuilder resultDetails = new StringBuilder();
            if (!isStack) resultDetails.append("It is NOT a Stack.\n");
            else resultDetails.append("It is a Stack.\n");

            if (!isQueue) resultDetails.append("It is NOT a Queue.\n");
            else resultDetails.append("It is a Queue.\n");

            if (!isPriorityQueue) resultDetails.append("It is NOT a Max Priority Queue.\n");
            else resultDetails.append("It is a Max Priority Queue.\n");

            System.out.print(resultDetails);

            String finalResult;
            int trueCount = (isStack ? 1 : 0) + (isQueue ? 1 : 0) + (isPriorityQueue ? 1 : 0);

            if (trueCount == 0) {
                finalResult = "impossible";
            } else if (trueCount == 1) {
                if (isStack) finalResult = "stack";
                else if (isQueue) finalResult = "queue";
                else finalResult = "priority queue";
            } else if (trueCount == 2) {
                if (isStack && isQueue) finalResult = "not sure: stack or queue";
                else if (isStack && isPriorityQueue) finalResult = "not sure: stack or priority queue";
                else finalResult = "not sure: queue or priority queue";
            } else {
                finalResult = "not sure: stack, queue or priority queue";
            }

            System.out.println(finalResult);
        }

        scanner.close();
    }
}
