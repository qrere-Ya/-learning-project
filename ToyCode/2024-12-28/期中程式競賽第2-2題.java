import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNo = 1;
        boolean firstCase = true; 

        while (sc.hasNextInt()) { 
            int n = sc.nextInt();
            if (n == 0) break;
            sc.nextLine();

            List<String> cmds = new ArrayList<>();
            List<String> fruits = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String line = sc.nextLine().trim();
                String[] parts = line.split("\\s+");
                cmds.add(parts[0]); 
                if (parts[0].equals("throw")) {
                    fruits.add(parts[1]); 
                } else if (parts[0].equals("take")) {
                    fruits.add(parts[1]);
                }
            }

            Deque<String> stack = new ArrayDeque<>();
            Deque<String> queue = new ArrayDeque<>();
            PriorityQueue<String> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

            boolean isStack = true, isQueue = true, isPriorityQueue = true;

            for (int i = 0; i < n; i++) {
                String cmd = cmds.get(i);
                String fruit = fruits.get(i);

                if (cmd.equals("throw")) {
                    if (isStack) stack.push(fruit);
                    if (isQueue) queue.offer(fruit);
                    if (isPriorityQueue) maxHeap.offer(fruit);
                } else if (cmd.equals("take")) {
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
                        if (maxHeap.isEmpty() || !maxHeap.poll().equals(fruit)) {
                            isPriorityQueue = false;
                        }
                    }
                }
            }

            if (!firstCase) {
                System.out.println();
            }
            firstCase = false;

            System.out.println("Case #" + caseNo + ": there are " + n + " operations.");
            for (int i = 0; i < n; i++) {
                System.out.println("(" + (i + 1) + ") cmd=" + cmds.get(i) + ", fruit=" + fruits.get(i));
            }
            System.out.println(isStack ? "It is a Stack." : "It is NOT a Stack.");
            System.out.println(isQueue ? "It is a Queue." : "It is NOT a Queue.");
            System.out.println(isPriorityQueue ? "It is a Max Priority Queue." : "It is NOT a Max Priority Queue.");
            System.out.println(determineResult(isStack, isQueue, isPriorityQueue));

            caseNo++;
        }

        sc.close();
    }

    private static String determineResult(boolean isStack, boolean isQueue, boolean isPriorityQueue) {
        int possibleCount = 0;
        if (isStack) possibleCount++;
        if (isQueue) possibleCount++;
        if (isPriorityQueue) possibleCount++;

        if (possibleCount == 0) return "impossible";
        if (possibleCount == 1) {
            if (isStack) return "stack";
            if (isQueue) return "queue";
            return "priority queue";
        }
        if (possibleCount == 2) {
            if (isStack && isQueue) return "not sure: stack or queue";
            if (isStack && isPriorityQueue) return "not sure: stack or priority queue";
            return "not sure: queue or priority queue";
        }
        return "not sure: stack, queue or priority queue";
    }
}
