package hotciv.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements list of all the Game observers
 */
public class GameObserverList implements GameObserver {
    private List<GameObserver> observers;

    public GameObserverList() {
        observers = new ArrayList<>();
    }

    public void addObserver(GameObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void worldChangedAt(Position pos) {
        for (GameObserver obs : observers) {
            obs.worldChangedAt(pos);
        }
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        for (GameObserver obs : observers) {
            obs.turnEnds(nextPlayer, age);
        }
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        for (GameObserver obs : observers) {
            obs.tileFocusChangedAt(position);
        }
    }
}
