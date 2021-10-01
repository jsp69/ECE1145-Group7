package hotciv.standard;

import hotciv.framework.Unit;
import hotciv.framework.Player;

public class UnitImpl implements Unit {

    Player owner;
    int attack = 1;
    int defense = 1;
    int move = 0;
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
    public int getAttackingStrength() {
        if(getOwner() == Player.RED) {
            owner = Player.BLUE;
        }
        if(getOwner() == Player.BLUE) {
            owner = Player.RED;
        }
        return attack;
    }

    public int getDefensiveStrength() {return defense;}

    public int getMoveCount() {
        if(getAttackingStrength() == 1) {
            move++;
        }
        return move;}
}




