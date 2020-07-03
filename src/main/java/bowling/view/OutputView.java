package bowling.view;

import bowling.game.frame.Frame;
import bowling.game.frame.Frames;
import bowling.game.Scores;
import bowling.player.domain.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class OutputView {
    private static final String SCORE_BOARD_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY_SPACE = "|      |";
    private static final int FRAME_COUNT_MAX = 10;

    public static void printScoreBoard(Player player, Frames frames, Scores scores) {
        printName(player);
        printFrames(frames);
        printScores(scores);
    }

    public static void printScoreBoard(Map<Player, Frames> playerFrames) {
        System.out.println(SCORE_BOARD_TITLE);
        playerFrames.forEach((player, frames) -> printScoreBoard(player, frames, new Scores(frames.getScores())));
    }

    private static void printName(Player player) {
        System.out.print(String.format("|%5s |", player.getName()));
    }

    public static void printFrames(Frames frames) {
        List<String> states = frames.getFramesStates();

        states.stream()
                .map(state -> String.format("  %-4s|", state))
                .forEach(System.out::print);

        Stream.generate(() -> "")
                .limit(FRAME_COUNT_MAX - states.size())
                .map(empty -> String.format("  %-4s|", empty))
                .forEach(System.out::print);

        System.out.println();
    }

    public static void printScores(Scores scores) {
        List<Integer> frameScores = scores.getFrameScores();

        System.out.print(EMPTY_SPACE);

        frameScores.stream()
                .map(frameScore -> String.format("  %-4d|", frameScore))
                .forEach(System.out::print);

        Stream.generate(() -> "")
                .limit(FRAME_COUNT_MAX - frameScores.size())
                .map(empty -> String.format("  %-4s|", empty))
                .forEach(System.out::print);

        System.out.println();
    }
}