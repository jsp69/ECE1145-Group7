package hotciv.stub;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class StubGameObserver implements GameObserver {
    private Position pos;
    private Player nextPlayer;
    private int age;
    private Position position;

    @Override
    public void worldChangedAt(Position pos) {
        this.pos = pos;
    }
    @Override
    public void turnEnds(Player nextPlayer, int age) {
        this.nextPlayer = nextPlayer;
        this.age = age;
    };
    @Override
    public void tileFocusChangedAt(Position position) {
        this.position = position;
    };
}
