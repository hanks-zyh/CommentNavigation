import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanks on 2016/11/2.
 */
public class AnalyzeCode {
    public static final String lineBreak = System.getProperty("line.separator");
    public static final String SY = "///";

    public static List<Comment> analyzeCodeComment(String code) {
        List<Comment> result = new ArrayList<>();
        int start = 0;
        int end = -1;
        do {
            start = code.indexOf(SY, end + 1);
            if (start == -1) {
                break;
            }
            end = code.indexOf(lineBreak, start);
            System.out.println("start = " + start + ",end = " + end);
            String substring = null;
            try {
                substring = code.substring(start + 3, end);
                int lineNumber = getLine(code, end);
                System.out.println("lineNumber = " + lineNumber);
                result.add(new Comment(substring, lineNumber));
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        } while (start != -1);
        return result;
    }

    public static int getLine(String code, int end) {
        int start = 0;
        int lineCount = -1;
        do {
            lineCount++;
            start = code.indexOf(lineBreak, start+1);
        } while (start != -1 && start < end);
        return lineCount + 1;
    }
}
