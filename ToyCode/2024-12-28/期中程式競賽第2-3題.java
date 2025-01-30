import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNo = 1;
        boolean firstCase = true;

        while (true) {
            if (!sc.hasNextInt()) break;
            int n = sc.nextInt();
            if (n == 0) break;

            if (!firstCase) {
                System.out.println();
            }
            firstCase = false;

            List<String> cmds = new ArrayList<>();
            List<String> fruits = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String cmd = sc.next();
                String fruit = sc.next();
                cmds.add(cmd);
                fruits.add(fruit);
            }

            Deque<String> stack = new ArrayDeque<>();
            Deque<String> queue = new ArrayDeque<>();
            PriorityQueue<String> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            boolean s = true, q = true, p = true;
            for (int i = 0; i < n; i++) {
                String c = cmds.get(i);
                String f = fruits.get(i);
                if (c.equals("throw")) {
                    if (s) stack.push(f);
                    if (q) queue.offer(f);
                    if (p) maxHeap.offer(f);
                } else {
                    if (s) {
                        if (stack.isEmpty() || !stack.pop().equals(f)) s = false;
                    }
                    if (q) {
                        if (queue.isEmpty() || !queue.poll().equals(f)) q = false;
                    }
                    if (p) {
                        if (maxHeap.isEmpty() || !maxHeap.poll().equals(f)) p = false;
                    }
                }
            }

            System.out.println("Case #" + caseNo + ": there are " + n + " operations.");
            for (int i = 0; i < n; i++) {
                System.out.println("(" + (i + 1) + ") cmd=" + cmds.get(i) + ", fruit=" + fruits.get(i));
            }
            System.out.println(s ? "It is a Stack." : "It is NOT a Stack.");
            System.out.println(q ? "It is a Queue." : "It is NOT a Queue.");
            System.out.println(p ? "It is a Max Priority Queue." : "It is NOT a Max Priority Queue.");
            System.out.println(result(s, q, p));

            caseNo++;
        }
        sc.close();
    }

    private static String result(boolean s, boolean q, boolean p) {
        int c = 0;
        if (s) c++;
        if (q) c++;
        if (p) c++;
        if (c == 0) return "impossible";
        else if (c == 1) {
            if (s) return "stack";
            if (q) return "queue";
            return "priority queue";
        } else if (c == 2) {
            if (s && q) return "not sure: stack or queue";
            if (s && p) return "not sure: stack or priority queue";
            return "not sure: queue or priority queue";
        } else {
            return "not sure: stack, queue or priority queue";
        }
    }
}
