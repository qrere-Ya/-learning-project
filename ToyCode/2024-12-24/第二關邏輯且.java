import java.util.*;

public class BooleanExpression1 {

    private static int caseNumber = 1; 
    private static int evaluationStep = 1;
    private static final int MAX_EXPR_LENGTH = 48; 
    private static final int PREFIX_LENGTH = 40; 
    private static final int SUFFIX_LENGTH = 6; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> results = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String expression = scanner.nextLine().trim();
            if (expression.isEmpty()) break;

            evaluationStep = 1;
            if (!results.isEmpty()) {
                results.add(""); 
            }
            results.add("Case #" + caseNumber + ":");
            results.add(parseAndEvaluate(expression));
            caseNumber++;
        }

        scanner.close();

        for (String result : results) {
            System.out.println(result);
        }
    }

    private static String parseAndEvaluate(String expr) {
        if (expr.equals("t")) return "The bool expression: \"t\" is true.";
        if (expr.equals("f")) return "The bool expression: \"f\" is false.";

        StringBuilder output = new StringBuilder();
        boolean result = evaluate(expr, output);
        output.append("The bool expression is ").append(result ? "true." : "false.");
        return output.toString();
    }

    private static boolean evaluate(String expr, StringBuilder output) {
        expr = expr.trim();
        if (expr.equals("t")) return true;
        if (expr.equals("f")) return false;

        if (expr.startsWith("!")) {
            String subExpr = extractInnerExpression(expr, 1);
            boolean result = !evaluate(subExpr, output);
            logEvaluation("Not", expr, result, subExpr, output);
            return result;
        }

        throw new IllegalArgumentException("Invalid Boolean Expression: " + expr);
    }

    private static void logEvaluation(String operator, String expr, boolean result, String skippedExpr, StringBuilder output) {
        String displayedExpr = truncateExpression(expr);
        output.append("(").append(evaluationStep++).append(") parse").append(operator).append("Expr: ")
              .append("\"").append(displayedExpr).append("\" => ").append(result ? "t" : "f");
        output.append("\n");
    }

    private static String truncateExpression(String expr) {
        if (expr.length() <= MAX_EXPR_LENGTH) {
            return expr;
        }
        return expr.substring(0, PREFIX_LENGTH) + "..." + expr.substring(expr.length() - SUFFIX_LENGTH);
    }

    private static String extractInnerExpression(String expr, int startIndex) {
        int openParentheses = 0;
        for (int i = startIndex; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') openParentheses++;
            if (expr.charAt(i) == ')') openParentheses--;
            if (openParentheses == 0) return expr.substring(startIndex + 1, i).trim();
        }
        throw new IllegalArgumentException("Unmatched parentheses in: " + expr);
    }
}
