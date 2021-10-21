package data.game;

import java.io.Serializable;

public class TreasureHuntMap implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    private final int[][][] mapList = {{{1, 1, 1, 1, 1, 1}, {1, 2, 0, 0, 0, 1},
        {1, 1, 1, 1, 0, 1}, {1, 0, 0, -1, 0, 1}, {1, 1, 1, 1, 1, 1}},
        {{1, 1, 1, 1, 1, 1, 1, 1}, {1, 2, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 0, 1, 1, 1, 1}, {1, -1, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1}}};
    private final int size = this.mapList.length;

    public final int[][] getMap() {
        final double randomNum = Math.random();
        return this.mapList[(int) (randomNum * 100) % this.mapList.length];
    }
}
