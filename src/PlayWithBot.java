import logic.ISnakeBot;

public final class PlayWithBot {
    public static void Play(ISnakeBot bot) throws Exception {
        Frame frame = new Frame(bot);
        frame.execute();
    }
}