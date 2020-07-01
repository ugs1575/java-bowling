package bowling.model.framestatus;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import bowling.model.KnockedDownPinsTest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SpareTest {

  @ParameterizedTest
  @MethodSource("provideCurrentIndexWithExpectedList")
  void getIndexOfNextFrames(int currentIndex, List<Integer> expected) {
    FrameStatus frameStatus = new Spare(new RequiredFirstRoll(currentIndex));

    assertThat(frameStatus.getIndexOfScoredFrames()).isEqualTo(expected);
    assertThat(frameStatus.isOver()).isTrue();
  }

  static Stream<Arguments> provideCurrentIndexWithExpectedList() {
    return Stream.of(
        arguments(
            0,
            Arrays.asList(
                0, 1
            )
        )
    );
  }

  @Test
  void testToString() {
    FrameStatus frameStatus = new Spare(new RequiredFirstRoll(0));

    assertThat(frameStatus.getResultBy(KnockedDownPinsTest.knockedDownPins5_5)).isEqualTo("5|/");
  }
}