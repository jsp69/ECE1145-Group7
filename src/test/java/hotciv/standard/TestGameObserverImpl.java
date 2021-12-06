package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class TestGameObserverImpl implements GameObserver {

    private Position pos;
    private Player nextPlayer;
    private int age;
    private Position position;

    @Override
    public void worldChangedAt(Position pos) {
        this.pos = pos;
        System.out.print("worldChangedAt called");
    }
    @Override
    public void turnEnds(Player nextPlayer, int age) {
        this.nextPlayer = nextPlayer;
        this.age = age;
        System.out.print("turnEnds called");
    };
    @Override
    public void tileFocusChangedAt(Position position) {
        this.position = position;
        System.out.print("tileFocusChangedAt called");
    };
}
