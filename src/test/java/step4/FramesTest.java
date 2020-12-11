package step4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import step4.domain.*;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    private Player player;

    @BeforeEach
    void setup() {
        player = Player.of("tst");
    }


    @DisplayName("10개의 프레임 생성 테스트")
    @Test
    void makeFrame() {
        Frames frames = player.getFrames();
        assertThat(frames.size()).isEqualTo(10);
    }

    @DisplayName("첫번째 프레임과 마지막 프레임 클래스 타입 테스트")
    @Test
    void checkFirstAndLastFrame() {
        Frames frames = player.getFrames();
        assertThat(frames.getHead()).isInstanceOf(NormalFrame.class);
    }

    @DisplayName("특정 프레임 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9})
    void getFrame(int frameNo) {
        Frames frames = player.getFrames();

        assertThat(frames.getFrame(frameNo).getFrameNo()).isEqualTo(frameNo);
        assertThat(frames.getFrame(8).next()).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("투구 테스트")
    @Test
    void pitches() {
        player.pitches(10);
        player.pitches(10);
        player.pitches(10);
        player.pitches(5);

        assertThat(player.getFrames().getTotalScores()).isEqualTo(75);
        assertThat(player.getFrames().getFrame(3).getScore()).isEqualTo(5);
    }

}