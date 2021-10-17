package bowling.model.state;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ReadyTest {
    @Test
    public void 상태가_스트라이크로_바뀌어야한다() {
        Ready ready = new Ready();
        State state = ready.bowl(10);
        assertTrue(state instanceof Strike);
    }

    @Test
    public void 상태가_FirstBowl로_바뀌어야한다() {
        Ready ready = new Ready();
        State state = ready.bowl(9);
        assertTrue(state instanceof FirstBowl);
    }
}