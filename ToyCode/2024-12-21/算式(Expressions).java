import java.util.*;

class TreeNode {
    char value;
    TreeNode left, right;

    public TreeNode(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class Main {

    private static boolean isLower(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    private static boolean isUpper(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    private static TreeNode buildTree(String test) {
        Stack<TreeNode> stack = new Stack<>();

        for (char ch : test.toCharArray()) {
            if (isLower(ch)) {
                stack.push(new TreeNode(ch));
            } else if (isUpper(ch)) {
                TreeNode right = stack.pop();
                TreeNode left = stack.pop();
                TreeNode node = new TreeNode(ch);
                node.left = left;
                node.right = right;
                stack.push(node);
            }
        }

        return stack.pop();
    }

    private static String bfs(TreeNode root) {
        if (root == null) return "";

        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder post = new StringBuilder();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            post.append(node.value);

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return post.reverse().toString();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 1; i <= T; i++) {
            String test = scanner.nextLine();

            TreeNode expression = buildTree(test);

            String queueExpression = bfs(expression);

            System.out.println(queueExpression);
        }

        scanner.close();
    }
}
