package hotciv.standard;

import hotciv.framework.Unit;
import hotciv.framework.Player;

public class UnitImpl implements Unit {

    Player owner;
    int attack = 1;
    int defense = 0;
    int move = 0;

    public String getTypeString() {return "archer";}

    //Constructor assigning Owner
    public UnitImpl(Player p){this.owner = p;}

    //Accessor to get Owner
    public Player getOwner(){return this.owner;}

    //Accessor to get attacking strength
    public int getAttackingStrength() {return attack; }

    //Accessor to get defensive strength
    public int getDefensiveStrength() {return defense;}

    //Accessor to get move count
    public int getMoveCount() {return move;}

}




