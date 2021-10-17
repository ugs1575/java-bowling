package bowling.model;

import java.util.LinkedList;

import static bowling.model.Score.*;

import bowling.CannotBowlException;
import bowling.model.state.Spare;
import bowling.model.state.Strike;

public abstract class AbstractFrame implements Frame {
    protected State state;
    protected LinkedList<Score> scores;

    @Override
    public State bowl(int countOfPin) throws CannotBowlException {
        this.state = state.bowl(countOfPin);

        calculateScore(countOfPin);

        return state;
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
    public boolean isFinish(int frameNo) {
        return state.isFinish(frameNo);
    }
}