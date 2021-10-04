package bowling.model;

import java.util.Objects;

import bowling.CannotBowlException;

public class ThirdStrike implements State {
    private final Point point;

    public ThirdStrike() {
        this.point = new Point(10);
    }

    @Override
    public State bowl(int countOfPin) throws CannotBowlException {
        throw new CannotBowlException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThirdStrike strike = (ThirdStrike) o;
        return Objects.equals(point, strike.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

}
