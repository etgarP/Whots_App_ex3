package page.chat;

public class Message {
    private String content;
    private Side side;

    public Message(String content, Side side) {
        this.content = content;
        this.side = side;
    }

    public String getContent() {
        return content;
    }

    public Side getSide() {
        return side;
    }

    private enum Side {
        LEFT,
        RIGHT
    }
}