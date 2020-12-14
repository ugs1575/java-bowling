package bowling.domain.bowl;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Pin;
import bowling.dto.BowlDto;
import bowling.dto.FrameEnumsDto;
import bowling.dto.FramesDto;
import bowling.dto.ScoresDto;

import java.util.LinkedList;

import static bowling.asset.Const.MAX_FRAME_NUMBER;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Bowl {
    private final LinkedList<Frame> frames = new LinkedList<>();
    private BowlState state = new FirstBowlState();

    void setState(BowlState state) {
        this.state = state;
    }

    void addFrame(Frame frame) {
        frames.add(frame);
    }

    public void addPin(Pin pin) {
        state.addPin(pin, this);
        update();
    }

    public void update() {
        state.updateState(this);
    }

    public boolean isPlayable() {
        return state.isPlayable();
    }

    public int getFrameNumber() {
        return frames.size() + state.getFrameNumberAdder();
    }

    boolean isMaxFrame() {
        return getFrameNumber() + 1 >= MAX_FRAME_NUMBER;
    }

    private FrameEnumsDto exportFrameEnumsDto() {
        return (frames.size() > MAX_FRAME_NUMBER
                ? frames.subList(0, MAX_FRAME_NUMBER)
                : frames).stream()
                .map(Frame::exportFrameDto)
                .collect(collectingAndThen(toList(), FrameEnumsDto::new));
    }

    private FramesDto exportFramesDto() {
        return new FramesDto(state.exportPinsDto(), exportFrameEnumsDto());
    }

    private ScoresDto exportScoresDto() {
        Scores scores = new Scores();
        frames.stream()
                .filter(Frame::hasScore)
                .map(Frame::getCountOfPins)
                .forEach(scores::accumulate);
        return scores.exportScoresDto();
    }

    public BowlDto exportBowlDto() {
        return new BowlDto(exportFramesDto(), exportScoresDto());
    }
}