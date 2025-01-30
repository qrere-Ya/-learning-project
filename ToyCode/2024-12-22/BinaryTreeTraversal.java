import java.util.*;

class TreeNode {
    int val;
    int nodeId;
    int level;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Main {

    private static TreeNode constructBinaryTree(int[] arr, int n, int[] totalNodes, int[] totalLevels) {
        if (n == 0) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        int nodeCount = 0;
        int maxLevel = 0;

        TreeNode root = new TreeNode(arr[0]);
        root.nodeId = ++nodeCount;
        root.level = 1;
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            maxLevel = Math.max(maxLevel, current.level);

            System.out.print("Build node#" + current.nodeId + ": (");

            if (i < n && arr[i] != -1) {
                current.left = new TreeNode(arr[i]);
                current.left.nodeId = ++nodeCount;
                current.left.level = current.level + 1;
                queue.add(current.left);
                System.out.print("node#" + current.left.nodeId);
            } else {
                System.out.print("null");
            }
            i++;
            System.out.print(") " + current.val + " (");

            if (i < n && arr[i] != -1) {
                current.right = new TreeNode(arr[i]);
                current.right.nodeId = ++nodeCount;
                current.right.level = current.level + 1;
                queue.add(current.right);
                System.out.print("node#" + current.right.nodeId);
            } else {
                System.out.print("null");
            }
            i++;
            System.out.println(") in level " + current.level + ".");
        }

        totalNodes[0] = nodeCount;
        totalLevels[0] = maxLevel;
        return root;
    }

    private static boolean isSubPath(TreeNode node, int[] path, int index) {
        if (node == null || node.val != path[index]) return false;
        return index == path.length - 1 || 
               isSubPath(node.left, path, index + 1) || 
               isSubPath(node.right, path, index + 1);
    }

    private static String findPath(TreeNode root, int[] path) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.val == path[0] && isSubPath(current, path, 0)) {
                return "The first matching path is found at node#" + current.nodeId + " in level " + current.level + ".";
            }
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return "The path is not found.";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) inputs.add(input);
        }

        int caseNum = 1;
        for (int idx = 0; idx < inputs.size(); ) {
            String input = inputs.get(idx);

            if (input.startsWith("tree")) {
                int[] treeArray = new int[1000];
                int n = parseInput(input, treeArray);

                System.out.println("Case #" + caseNum++ + ":");
                printArray("tree", treeArray, n);

                int[] totalNodes = {0}, totalLevels = {0};
                TreeNode root = constructBinaryTree(treeArray, n, totalNodes, totalLevels);

                System.out.println("The binary tree has " + totalNodes[0] + " node(s) and " + totalLevels[0] + " level(s).");

                idx++;
                while (idx < inputs.size() && inputs.get(idx).startsWith("path")) {
                    String pathInput = inputs.get(idx);
                    int[] pathArray = new int[10];
                    int pathSize = parseInput(pathInput, pathArray);

                    printArray("path", pathArray, pathSize);
                    System.out.println(findPath(root, Arrays.copyOf(pathArray, pathSize)));
                    idx++;
                }
                if (idx < inputs.size()) System.out.println();
            } else {
                idx++;
            }
        }
    }

    private static int parseInput(String input, int[] output) {
        int index = 0, startIdx = input.indexOf("=");
        if (startIdx == -1) return 0;

        String[] tokens = input.substring(startIdx + 1)
                                .replaceAll("[\\[\\]]", "")
                                .split(",");

        for (String token : tokens) {
            output[index++] = token.trim().equals("null") ? -1 : Integer.parseInt(token.trim());
        }
        return index;
    }

    private static void printArray(String name, int[] array, int length) {
        System.out.print(name + " = [");
        for (int i = 0; i < length; i++) {
            System.out.print(array[i] == -1 ? "null" : array[i]);
            if (i < length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
