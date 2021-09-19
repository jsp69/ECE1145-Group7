package hotciv.standard;

import hotciv.framework.Unit;
import hotciv.framework.Player;

public class UnitImpl implements Unit {

    Player owner;
    int attack = 1;
    String tType;

    //Constructor assigning type
    public UnitImpl(String type) { this.tType = type; }

    //Accessor to return type
    public String getTypeString() {return "archer";}

    //Constructor assigning Owner
    public UnitImpl(Player p){this.owner = p;}

    //Accessor to get Owner
    public Player getOwner(){return this.owner;}

    //Accessor to get attacking strength
    public int getAttackingStrength() {return attack; }

    //TODO
    public int getDefensiveStrength() {return 0;}
    public int getMoveCount() {return 0;}
}




