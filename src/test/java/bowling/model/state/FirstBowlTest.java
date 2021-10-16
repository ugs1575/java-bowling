package bowling.model.state;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FirstBowlTest {
    @Test
    public void 상태가_스페어로_바뀌어야한다() {
        FirstBowl firstBowl = new FirstBowl(9);
        State state = firstBowl.bowl(1);
        assertTrue(state instanceof Spare);
    }

    @Test
    public void 상태가_미스로_바뀌어야한다() {
        FirstBowl firstBowl = new FirstBowl(2);
        State state = firstBowl.bowl(1);
        assertTrue(state instanceof Miss);
    }
}
