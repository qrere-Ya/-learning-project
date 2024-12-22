import java.util.*;
// 匯入Java工具包，包含用於數據結構（例如Queue、List）的類。

class TreeNode {
    int val; 
    // 節點的值，用來存儲數據，例如在樹中每個節點對應的數值。
    int nodeId; 
    // 節點的唯一編號，用於識別節點。
    int level; 
    // 節點的層級，用來標記節點在二元樹中的深度（1代表根節點）。
    TreeNode left; 
    // 左子節點的引用，指向二元樹中節點的左子節點。
    TreeNode right; 
    // 右子節點的引用，指向二元樹中節點的右子節點。

    TreeNode(int val) { 
        this.val = val; 
        // 建構子：用於創建節點，並初始化節點的值。
    }
}

public class BinaryTreeTraversal { 
    // 主類，負責處理二元樹的建立、路徑查找等功能。

    private static int parseInput(String input, int[] output) {
        // 解析輸入字串，將其轉為整數陣列並存入output。
        // 返回整數數組中有效元素的數量。

        int index = 0; 
        // 用於記錄解析到第幾個值，初始為0。

        int startIdx = input.indexOf("="); 
        // 找到等號的位置，等號後的部分是有效的樹結構數據。

        if (startIdx == -1) {
            return 0; 
            // 如果找不到等號，表示輸入格式錯誤，直接返回0。
        }

        String treeContent = input.substring(startIdx + 1).trim(); 
        // 提取等號後的字串，並移除多餘空白。

        if (treeContent.startsWith("[")) {
            treeContent = treeContent.substring(1); 
            // 如果字串以"["開頭，移除它，方便解析。
        }

        if (treeContent.endsWith("]")) {
            treeContent = treeContent.substring(0, treeContent.length() - 1); 
            // 如果字串以"]"結尾，移除它。
        }

        String[] tokens = treeContent.split(","); 
        // 使用逗號分隔字串，將節點的值分成一個個字串。

        for (String token : tokens) {
            token = token.trim(); 
            // 去掉每個字串前後的空白。

            if (token.equals("null")) {
                output[index++] = -1; 
                // "null" 表示對應節點不存在，存為-1。
            } else if (!token.isEmpty()) {
                output[index++] = Integer.parseInt(token); 
                // 將非空字串轉為整數並存入陣列。
            }
        }

        return index; 
        // 返回有效節點的數量。
    }

    private static TreeNode constructBinaryTree(int[] arr, int n, int[] totalNodes, int[] totalLevels) {
        // 根據數組arr中的數據建立二元樹，返回樹的根節點。
        // 同時記錄樹的總節點數和最大層數到totalNodes和totalLevels中。

        if (n == 0) return null; 
        // 如果數組為空，返回null，表示沒有樹。

        Queue<TreeNode> queue = new LinkedList<>();
        // 使用佇列（Queue）來輔助層次遍歷構建樹。

        int nodeCount = 0; 
        // 節點計數器，初始為0。
        int maxLevel = 0;  
        // 樹的最大層級。

        TreeNode root = new TreeNode(arr[0]); 
        // 用數組中的第一個元素創建根節點。
        root.nodeId = ++nodeCount; 
        // 設定根節點的唯一ID。
        root.level = 1; 
        // 根節點在第一層。
        queue.add(root); 
        // 將根節點加入佇列。

        int i = 1; 
        // 記錄當前處理數組的索引。

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); 
            // 從佇列中取出一個節點進行處理。

            maxLevel = current.level; 
            // 更新當前最大層數。

            System.out.print("Build node#" + current.nodeId + ": (");
            // 打印當前節點的建構過程。

            if (i < n && arr[i] != -1) {
                current.left = new TreeNode(arr[i]); 
                // 創建左子節點。
                current.left.nodeId = ++nodeCount; 
                // 為左子節點分配ID。
                current.left.level = current.level + 1; 
                // 設定左子節點的層級。
                queue.add(current.left); 
                // 將左子節點加入佇列。
                System.out.print("node#" + current.left.nodeId);
            } else {
                System.out.print("null"); 
                // 如果左子節點為null。
            }
            i++;
            System.out.print(") " + current.val + " (");

            if (i < n && arr[i] != -1) {
                current.right = new TreeNode(arr[i]); 
                // 創建右子節點。
                current.right.nodeId = ++nodeCount; 
                // 為右子節點分配ID。
                current.right.level = current.level + 1; 
                // 設定右子節點的層級。
                queue.add(current.right); 
                // 將右子節點加入佇列。
                System.out.print("node#" + current.right.nodeId);
            } else {
                System.out.print("null"); 
                // 如果右子節點為null。
            }
            i++;
            System.out.println(") in level " + current.level + ".");
        }

        totalNodes[0] = nodeCount; 
        // 記錄樹的節點總數。
        totalLevels[0] = maxLevel; 
        // 記錄樹的最大層級。
        return root; 
        // 返回根節點。
    }

    private static boolean isSubPath(TreeNode node, int[] path, int index) {
        // 檢查從指定節點開始是否存在目標路徑。

        if (node == null) return false; 
        // 如果節點為空，無法匹配。

        if (node.val != path[index]) return false; 
        // 如果節點值與目標路徑不匹配，返回false。

        if (index == path.length - 1) return true; 
        // 如果匹配到路徑的最後一個值，返回true。

        return isSubPath(node.left, path, index + 1) || isSubPath(node.right, path, index + 1); 
        // 繼續檢查左右子節點是否能匹配後續路徑。
    }

    private static String findPath(TreeNode root, int[] path) {
        // 從樹的根節點開始查找目標路徑。

        Queue<TreeNode> queue = new LinkedList<>();
        // 用佇列進行層次遍歷。
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); 
            // 取出當前節點進行檢查。

            if (current.val == path[0] && isSubPath(current, path, 0)) {
                return "The first matching path is found at node#" + current.nodeId + " in level " + current.level + ".";
                // 如果找到匹配的路徑，返回信息。
            }

            if (current.left != null) queue.add(current.left); 
            // 如果有左子節點，加入佇列。

            if (current.right != null) queue.add(current.right); 
            // 如果有右子節點，加入佇列。
        }

        return "The path is not found."; 
        // 如果找遍全樹仍未找到路徑，返回未找到。
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        // 創建掃描器，從標準輸入讀取數據。

        List<String> inputs = new ArrayList<>(); 
        // 保存所有輸入。

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim(); 
            // 逐行讀取輸入，並去掉多餘空白。

            if (!input.isEmpty()) {
                inputs.add(input); 
                // 如果輸入不為空，添加到列表中。
            }
        }

        int caseNum = 1; 
        // 用於標記測試用例編號。

        for (int idx = 0; idx < inputs.size(); ) {
            String input = inputs.get(idx);

            if (input.startsWith("tree")) {
                int[] treeArray = new int[1000]; 
                // 用於存儲解析的樹數據。

                int n = parseInput(input, treeArray); 
                // 解析樹的數據。

                System.out.println("Case #" + caseNum++ + ":");
                System.out.print("tree = [");
                for (int i = 0; i < n; i++) {
                    System.out.print(treeArray[i] == -1 ? "null" : treeArray[i]);
                    if (i < n - 1) System.out.print(", ");
                }
                System.out.println("]");

                int[] totalNodes = {0};
                int[] totalLevels = {0};
                TreeNode root = constructBinaryTree(treeArray, n, totalNodes, totalLevels);

                System.out.println("The binary tree has " + totalNodes[0] + " node(s) and " + totalLevels[0] + " level(s).");

                idx++;
                while (idx < inputs.size() && inputs.get(idx).startsWith("path")) {
                    String pathInput = inputs.get(idx);
                    int[] pathArray = new int[10];
                    int pathSize = parseInput(pathInput, pathArray);

                    System.out.print("path = [");
                    for (int i = 0; i < pathSize; i++) {
                        System.out.print(pathArray[i]);
                        if (i < pathSize - 1) System.out.print(", ");
                    }
                    System.out.println("]");
                    System.out.println(findPath(root, Arrays.copyOf(pathArray, pathSize)));
                    idx++;
                }
                if (idx < inputs.size()) System.out.println();
            } else {
                idx++;
            }
        }
    }
}