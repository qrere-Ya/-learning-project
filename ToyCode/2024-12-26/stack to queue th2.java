import java.util.*; // 引入工具類，包含 Stack、Queue 和 Scanner

public class Main {

    // 表達式樹的節點類別
    static class TreeNode {
        String value; // 節點的值（操作數或運算符）
        TreeNode left, right; // 左子節點和右子節點

        TreeNode(String value) {
            this.value = value; // 初始化節點的值
            this.left = null; // 預設左子節點為空
            this.right = null; // 預設右子節點為空
        }
    }

    // 從後置式算式建立表達式樹
    public static TreeNode buildTree(String stackExpression) {
        Stack<TreeNode> stack = new Stack<>(); // 建立一個堆疊來保存節點

        // 遍歷每個字元來構建表達式樹
        for (char ch : stackExpression.toCharArray()) {
            if (Character.isLowerCase(ch)) { // 如果是操作數，建立節點並推入堆疊
                stack.push(new TreeNode(String.valueOf(ch)));
            } else if (Character.isUpperCase(ch)) { // 如果是運算符，彈出兩個節點並建立子樹
                TreeNode right = stack.pop(); // 右操作數
                TreeNode left = stack.pop(); // 左操作數
                TreeNode operatorNode = new TreeNode(String.valueOf(ch)); // 建立運算符節點
                operatorNode.left = left; // 將左節點連接到左子樹
                operatorNode.right = right; // 將右節點連接到右子樹
                stack.push(operatorNode); // 將運算符節點推回堆疊
            }
        }

        return stack.pop(); // 堆疊最後剩下的節點為表達式樹的根節點
    }

    // 使用 BFS 遍歷樹並反轉結果
    private static String bfs(TreeNode root) {
        if (root == null) return ""; // 如果根節點為空，直接返回空字串

        Queue<TreeNode> queue = new LinkedList<>(); // 建立隊列用於廣度優先搜尋
        StringBuilder post = new StringBuilder(); // 用於儲存遍歷結果

        queue.offer(root); // 將根節點加入隊列

        while (!queue.isEmpty()) { // 當隊列不為空時，持續處理
            TreeNode node = queue.poll(); // 從隊列中取出節點
            post.append(node.value); // 將節點值加入結果中

            if (node.left != null) queue.offer(node.left); // 如果有左子節點，加入隊列
            if (node.right != null) queue.offer(node.right); // 如果有右子節點，加入隊列
        }

        return post.reverse().toString(); // 反轉結果並返回
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 建立掃描器以讀取輸入

        int testCaseCount = Integer.parseInt(scanner.nextLine()); // 讀取測試資料的數量

        for (int i = 0; i < testCaseCount; i++) { // 逐一處理每筆測試資料
            String stackExpression = scanner.nextLine(); // 讀取後置式算式

            // 從後置式算式建立表達式樹
            TreeNode rootFromStack = buildTree(stackExpression);

            // 使用 BFS 遍歷以取得 Queue 版本的結果
            String queueExpression = bfs(rootFromStack);

            // 輸出 Queue 表達式
            System.out.println(queueExpression);
        }
        scanner.close(); // 關閉掃描器
    }
}
