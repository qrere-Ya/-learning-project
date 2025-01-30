import java.util.*;

public class Main{
    private static int caseNumber = 1; 
    private static int evaluationStep = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        while (scanner.hasNextLine()) {
            String expression = scanner.nextLine().trim(); 
            if (expression.isEmpty()) break; 
            evaluationStep = 1; 
            System.out.println("Case #" + caseNumber + ":"); 
            try {
                String result = evaluateExpression(expression);
                System.out.println(result); 
            } catch (IllegalArgumentException e) {
                System.out.println("The bool expression is invalid.");
            }
            caseNumber++; 
            if (scanner.hasNextLine()) {
                System.out.println(); 
            }
        }
        scanner.close(); 
    }

    private static String evaluateExpression(String expr) {
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
            if (subExpr.isEmpty()) throw new IllegalArgumentException("Invalid expression: " + expr);
            boolean result = !evaluate(subExpr, output);
            logEvaluation("Not", truncateExpression(expr, 48), result, null, output);
            return result;
        } else if (expr.startsWith("&")) { 
            return evaluateLogical(expr, output, true, "And", "&");
        } else if (expr.startsWith("|")) { 
            return evaluateLogical(expr, output, false, "Or", "|");
        }
        throw new IllegalArgumentException("Invalid Boolean Expression: " + expr); 
    }

    private static boolean evaluateLogical(String expr, StringBuilder output, boolean initialValue, String operator, String symbol) {
        List<String> subExprs = extractMultipleInnerExpressions(expr);
        if (subExprs.isEmpty()) throw new IllegalArgumentException("Invalid expression: " + expr);
        boolean result = initialValue; 
        for (int i = 0; i < subExprs.size(); i++) {
            String subExpr = subExprs.get(i);
            boolean subResult = evaluate(subExpr, output); 
            if ((symbol.equals("&") && !subResult) || (symbol.equals("|") && subResult)) { 
                String skipExpr = getSkippedExpressions(subExprs, i + 1);
                logEvaluation(operator, truncateExpression(expr, 48), subResult, skipExpr.isEmpty() ? null : truncateSkipExpr(skipExpr, 32), output);
                return subResult;
            }
            result = symbol.equals("&") ? result & subResult : result | subResult; 
        }
        logEvaluation(operator, truncateExpression(expr, 48), result, null, output);
        return result;
    }

    private static void logEvaluation(String operator, String expr, boolean result, String skipExpr, StringBuilder output) {
        output.append("(").append(evaluationStep++).append(") parse").append(operator).append("Expr: \"")
              .append(expr).append("\" => ").append(result ? "t" : "f");
        if (skipExpr != null && !skipExpr.isEmpty()) {
            output.append(" with skipExpr: \"").append(skipExpr).append("\"");
        }
        output.append(System.lineSeparator());
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

    private static List<String> extractMultipleInnerExpressions(String expr) {
        List<String> subExprs = new ArrayList<>();
        int openParentheses = 0, start = 2;
        for (int i = 2; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') openParentheses++;
            if (expr.charAt(i) == ')') openParentheses--;
            if ((expr.charAt(i) == ',' && openParentheses == 0) || (expr.charAt(i) == ')' && openParentheses == -1)) {
                subExprs.add(expr.substring(start, i).trim());
                start = i + 1;
            }
        }
        return subExprs;
    }

    private static String getSkippedExpressions(List<String> subExprs, int startIndex) {
        StringBuilder skipExpr = new StringBuilder();
        for (int i = startIndex; i < subExprs.size(); i++) {
            if (i > startIndex) skipExpr.append(",");
            skipExpr.append(subExprs.get(i));
        }
        return skipExpr.toString();
    }

    private static String truncateExpression(String expr, int maxLength) {
        return expr.length() <= maxLength ? expr : expr.substring(0, 40) + "..." + expr.substring(expr.length() - 6);
    }

    private static String truncateSkipExpr(String skipExpr, int maxLength) {
        return skipExpr.length() <= maxLength ? skipExpr : skipExpr.substring(0, 24) + "..." + skipExpr.substring(skipExpr.length() - 6);
    }
}
