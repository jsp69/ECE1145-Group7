package hotciv.stub;

import hotciv.framework.*;

public class StubCity implements City {
    Player owner;
    int size = 1;
    int treasure = 0;
    String focus;
    String unit;
    boolean redOwns = true;

    public StubCity(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return (redOwns ? Player.RED : Player.BLUE);
        // return owner;
    }

    public int getSize() {
        return (redOwns ? 4 : 9);
        // return size;
    }

    public int getTreasury() {
        return treasure;
    }

    public String getProduction() {
        return unit;
    }

    public String getWorkforceFocus() {
        return focus;
    }

    public void  makeAChange() { redOwns = ! redOwns; }
}
