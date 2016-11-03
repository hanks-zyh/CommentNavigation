/**
 * Created by hanks on 2016/11/3.
 */
public class Comment {
    private String comment;
    private int line;

    public Comment(String comment, int line) {
        this.comment = comment;
        this.line = line;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
