package bowling.model;

import bowling.CannotBowlException;

import java.util.*;
import java.util.stream.Collectors;

import static bowling.model.Score.*;

public class FinalRound implements Round{
    private static int bonusCount = 1;

    private State state;
    private LinkedList<Score> scores;

    public FinalRound() {
        this.state = new Ready();
        this.scores = new LinkedList<>();
        this.scores.add(new Score());
    }

    public FinalRound(State state, LinkedList<Score> scores) {
        this.state = state;
        this.scores = scores;
    }

    @Override
    public State bowl(int countOfPin) throws CannotBowlException {
        this.state = state.bowl(countOfPin);

        calculateScore(countOfPin);

        return state;
    }

    @Override
    public boolean isFinish() {
        if (state instanceof Miss || state instanceof ThirdStrike || state instanceof ThirdSpare) {
            return true;
        }

        return false;
    }

    @Override
    public List<Integer> getScore() {
        return scores.stream()
                .filter(Score::canCalculateScore)
                .map(Score::getScore)
                .collect(Collectors.toList());
    }

    @Override
    public int calcMaxTryCount() {
        if (bonusCount == 0) {
            return 0;
        }

        if (state instanceof Strike || state instanceof Spare) {
            bonusCount -= 1;
            return 1;
        }

        return 0;
    }

    @Override
    public void calculateScore(int countOfPin) {
        for (int i = 0; i < scores.size(); i++) {
            scores.add(scores.remove().bowl(countOfPin));
        }

        if (state instanceof Strike) {
            scores.removeLast();
            scores.add(ofStrike());
        }

        if (state instanceof Spare) {
            scores.removeLast();
            scores.add(ofSpare());
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalRound that = (FinalRound) o;
        return Objects.equals(state, that.state) && Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, scores);
    }
}
