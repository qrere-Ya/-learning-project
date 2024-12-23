import java.util.*;

public class Main{

    static class ValidSubstring {
        int startIndex, length;
        String substring;

        public ValidSubstring(int startIndex, int length, String substring) {
            this.startIndex = startIndex;
            this.length = length;
            this.substring = substring;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> cases = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            cases.add(input);
        }

        int caseNumber = 1;
        for (String inputCase : cases) {
            System.out.println("Case #" + caseNumber + ":");

            int colonIndex = inputCase.indexOf(":");
            if (colonIndex == -1) {
                System.out.println("Invalid input format.");
                caseNumber++;
                continue;
            }

            String caseDescription = inputCase.substring(0, colonIndex).trim();
            String parenthesesString = inputCase.substring(colonIndex + 1).trim();
            if (parenthesesString.startsWith("\"") && parenthesesString.endsWith("\"")) {
                parenthesesString = parenthesesString.substring(1, parenthesesString.length() - 1);
            }

            System.out.println("The input string of " + caseDescription + " is \"" + parenthesesString + "\".");

            List<ValidSubstring> validSubstrings = findValidParentheses(parenthesesString);

            if (validSubstrings.isEmpty()) {
                System.out.println("No valid parentheses found.");
            } else {
                int maxLen = 0;
                String longestSubstring = "";
                for (int i = 0; i < validSubstrings.size(); i++) {
                    ValidSubstring vs = validSubstrings.get(i);
                    System.out.println("(" + (i + 1) + ") index:" + vs.startIndex + ", length:" + vs.length + ", valid:\"" + vs.substring + "\"");
                    if (vs.length > maxLen) {
                        maxLen = vs.length;
                        longestSubstring = vs.substring;
                    }
                }
                System.out.println("The longest valid parentheses is \"" + longestSubstring + "\" with length " + maxLen + ".");
            }
            caseNumber++;
            if (caseNumber <= cases.size()) System.out.println();
        }
        scanner.close();
    }

    public static List<ValidSubstring> findValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        boolean[] valid = new boolean[s.length()];
        Arrays.fill(valid, false);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (!stack.isEmpty()) {
                valid[i] = true;
                valid[stack.pop()] = true;
            }
        }

        List<ValidSubstring> results = new ArrayList<>();
        int start = -1;

        for (int i = 0; i < s.length(); i++) {
            if (valid[i]) {
                if (start == -1) start = i;
            } else {
                if (start != -1) {
                    results.add(new ValidSubstring(start, i - start, s.substring(start, i)));
                    start = -1;
                }
            }
        }
        if (start != -1) {
            results.add(new ValidSubstring(start, s.length() - start, s.substring(start)));
        }

        return results;
    }
}
