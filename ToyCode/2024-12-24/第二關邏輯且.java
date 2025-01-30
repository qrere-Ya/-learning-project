import java.util.*;

public class BooleanExpression2 {

    private static int caseNumber = 1;
    private static int evaluationStep = 1;
    private static final int MAX_EXPR_LENGTH = 48;
    private static final int SKIP_EXPR_MAX_LENGTH = 32;
    private static final int PREFIX_LENGTH = 24;
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

        if (expr.startsWith("&")) {
            String innerExpr = extractInnerExpression(expr, 1);
            String[] terms = splitTerms(innerExpr);
            boolean result = true;
            StringBuilder skippedExpr = new StringBuilder();

            for (int i = 0; i < terms.length; i++) {
                boolean termResult = evaluate(terms[i].trim(), output);
                if (!termResult) {
                    result = false;
                    if (i + 1 < terms.length) {
                        skippedExpr.append(String.join(",", Arrays.copyOfRange(terms, i + 1, terms.length)));
                    }
                    break;
                }
            }

            logEvaluation("And", expr, result, skippedExpr.toString(), output);
            return result;
        }

        throw new IllegalArgumentException("Invalid Boolean Expression: " + expr);
    }

    private static void logEvaluation(String operator, String expr, boolean result, String skippedExpr, StringBuilder output) {
        String displayedExpr = truncateExpression(expr, MAX_EXPR_LENGTH);
        output.append("(").append(evaluationStep++).append(") parse").append(operator).append("Expr: ")
              .append("\"").append(displayedExpr).append("\" => ").append(result ? "t" : "f");

        if (!skippedExpr.isEmpty()) {
            String correctedSkipExpr = truncateSkipExpression(skippedExpr, SKIP_EXPR_MAX_LENGTH);
            output.append(" with skipExpr: \"").append(correctedSkipExpr).append("\"");
        }

        output.append("\n");
    }

    private static String truncateExpression(String expr, int maxLength) {
        if (expr.length() <= maxLength) {
            return expr;
        }
        return expr.substring(0, maxLength - SUFFIX_LENGTH - 3) + "..." + expr.substring(expr.length() - SUFFIX_LENGTH);
    }

    private static String truncateSkipExpression(String expr, int maxLength) {
        if (expr.length() <= maxLength) {
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

    private static String[] splitTerms(String expr) {
        List<String> terms = new ArrayList<>();
        int openParentheses = 0;
        StringBuilder currentTerm = new StringBuilder();

        for (char c : expr.toCharArray()) {
            if (c == ',' && openParentheses == 0) {
                terms.add(currentTerm.toString());
                currentTerm.setLength(0);
            } else {
                if (c == '(') openParentheses++;
                if (c == ')') openParentheses--;
                currentTerm.append(c);
            }
        }
        if (currentTerm.length() > 0) {
            terms.add(currentTerm.toString());
        }

        return terms.toArray(new String[0]);
    }
}
