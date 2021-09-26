package bowling.model;

import java.util.Objects;

public class Score {
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int countOfPin) {
        return new Score(countOfPin + score, left - 1);
    }

    public int getScore() {
        return this.score;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofMiss(int countOfPin) {
        return new Score(countOfPin, 0);
    }

    public static Score ofGutter() {
        return new Score(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
