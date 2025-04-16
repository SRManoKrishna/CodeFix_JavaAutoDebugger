import java.util.Stack;

public class SyntaxChecker {

    // Simple list of Java keywords (partial)
    private static final String[] JAVA_KEYWORDS = {
            "public", "private", "class", "static", "void", "int", "double",
            "float", "if", "else", "for", "while", "return", "String", "new", "import"
    };

    public static String checkCode(String code) {
        StringBuilder result = new StringBuilder();
        boolean errorFound = false;

        // Split code line by line
        String[] lines = code.split("\\n");
        int lineNumber = 0;

        for (String line : lines) {
            lineNumber++;

            // Check for missing semicolon
            if (line.trim().matches(".*[^;{}:]\\s*$") && line.trim().matches(".*\\)\\s*$")) {
                result.append("⚠️ Line ").append(lineNumber).append(": Possibly missing semicolon.\n");
                errorFound = true;
            }

            // Check for incorrect assignment (= vs ==)
            if (line.contains("if") && line.contains("=") && !line.contains("==")) {
                result.append("⚠️ Line ").append(lineNumber).append(": Use '==' for comparison.\n");
                errorFound = true;
            }

            // Check for misspelled keywords
            for (String word : line.trim().split("\\s+")) {
                if (word.length() > 2 && Character.isLowerCase(word.charAt(0)) &&
                        !isJavaKeyword(word) && !word.matches(".*[;{}().=]+.*")) {
                    result.append("⚠️ Line ").append(lineNumber).append(": '")
                            .append(word).append("' might be a misspelled keyword.\n");
                    errorFound = true;
                }
            }

            // Basic indentation check
            if (line.startsWith(" ") && line.length() - line.stripLeading().length() % 4 != 0) {
                result.append("⚠️ Line ").append(lineNumber).append(": Indentation not a multiple of 4.\n");
                errorFound = true;
            }
        }

        // Check for unbalanced brackets
        if (!areBracketsBalanced(code)) {
            result.append("❌ Brackets/Braces are not balanced!\n");
            errorFound = true;
        }

        if (!errorFound) {
            return "✅ No syntax issues found!";
        }

        return result.toString();
    }

    private static boolean isJavaKeyword(String word) {
        for (String keyword : JAVA_KEYWORDS) {
            if (word.equals(keyword)) return true;
        }
        return false;
    }

    private static boolean areBracketsBalanced(String code) {
        Stack<Character> stack = new Stack<>();
        for (char ch : code.toCharArray()) {
            if (ch == '{' || ch == '(' || ch == '[') {
                stack.push(ch);
            } else if (ch == '}' || ch == ')' || ch == ']') {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if ((ch == '}' && open != '{') || (ch == ')' && open != '(') || (ch == ']' && open != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
