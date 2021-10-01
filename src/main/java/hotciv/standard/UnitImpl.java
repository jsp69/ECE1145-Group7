package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.Player;

public class UnitImpl implements Unit {

    Player owner;
    int attack = 1;
    int defense = 1;
    int move = 0;
    String tType;
    Position p;

    //Constructor assigning type and owner
    public UnitImpl(String type, Player play, Position pos) {
        this.tType = type;
        this.owner = play;
        this.p = pos;
    }

    //Accessor to return type
    public String getTypeString() {
        return this.tType;
    }

    //Accessor to get Owner
    public Player getOwner() {
        return this.owner;
    }

    //Accessor to get position
    public Position getPosition() {
        return this.p;
    }

    //Accessor to get attacking strength
    public int getAttackingStrength() {
        if (getOwner() == Player.RED) {
            this.owner = Player.BLUE;
        } else {
            this.owner = Player.RED;
        }
        return this.attack;
    }

    public int getDefensiveStrength() {
        return this.defense;
    }

    public int getMoveCount() {
<<<<<<< HEAD
        if(getAttackingStrength() == 1) {
            move++;
=======
        if (getDefensiveStrength() == 1 || getAttackingStrength() == 1) {
            this.move++;
>>>>>>> 5bdffd7c633b41d61309fd62ebde96887b940242
        }
        return this.move;
    }

    //Change unit position
    public void changePosition(Position pos) {
        this.p = pos;
    }

    //Set max move count
    public void setMoveCount(int m) {
        this.move = m;
    }

    // Set defensive strength
    public void setDefenses(int d) {
        this.defense = d;
    }

    // Set attack strength
    public void setAttack(int a) {
        this.attack = a;
    }

}




