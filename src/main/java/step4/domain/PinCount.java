package step4.domain;

import java.util.Objects;

public class PinCount {
    public static final int PIN_COUNT_MIN = 0;
    public static final int PIN_COUNT_MAX = 10;
    private static final String CHECK_BOUND = "쓰러뜨린 핀의 개수가 0 ~ 10의 범위인지 확인하여 주세요.";

    private final int pinCount;

    public PinCount(int pinCount) {
        checkBound(pinCount);
        this.pinCount = pinCount;
    }

    private void checkBound(int pinCount) {
        if (PIN_COUNT_MIN > pinCount || PIN_COUNT_MAX < pinCount) {
            throw new IllegalArgumentException(CHECK_BOUND);
        }
    }

    public boolean isStrike() {
        return pinCount == PIN_COUNT_MAX;
    }

    public boolean isGutter() {
        return pinCount == PIN_COUNT_MIN;
    }

    public boolean isSpare(PinCount previousPinCount) {
        return !this.isStrike() && (plus(previousPinCount) == PIN_COUNT_MAX);
    }

    public int value() {
        return pinCount;
    }

    public int plus(PinCount pinCount) {
        return this.pinCount + pinCount.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PinCount pinCount = (PinCount) o;
        return this.pinCount == pinCount.pinCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCount);
    }
}