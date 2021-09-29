package bowling.model;

import java.util.Objects;

public class FirstBowl implements State {
    private Point countOfPin;

    public FirstBowl(int countOfPin) {
        this.countOfPin = new Point(countOfPin);
    }

    @Override
    public State bowl(int countOfPin) {
        Point currentPin = this.countOfPin.add(countOfPin);

        if (currentPin.isStrike()) {
            return new Spare();
        }

        return new Miss(currentPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(countOfPin, firstBowl.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }
}