package bowling.controller;

import bowling.CannotBowlException;
import bowling.model.BowlingGame;
import bowling.model.State;
import bowling.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static bowling.view.InputView.ask;
import static bowling.view.InputView.askDigit;
import static bowling.view.OutputView.changeScore;
import static bowling.view.OutputView.printResult;

public class Main {
    public static void main(String[] args) throws CannotBowlException {
        String userName = ask("플레이어 이름은(3 english letters)?:");
        User user = new User(userName);

        BowlingGame game = new BowlingGame();
        List<Integer> scoreResult = new ArrayList<>();
        LinkedList<String> stateResult = new LinkedList<>();
        while (!game.isEndGame()) {
            int pinCount = askDigit(game.getFrameNo() + "프레임 투구 : ");

            State state = game.bowl(pinCount);
            List<Integer> scores = game.getScore();

            stateResult.add(changeScore(stateResult, state, pinCount));
            scoreResult.addAll(scores);

            printResult(userName, stateResult, scoreResult);
        }
    }
}
