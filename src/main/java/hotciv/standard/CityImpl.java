package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City {
    Player owner;
    static int size = 1;
    Position pos;
    int treasure;

    //Constructor assigning Owner
    public CityImpl(Player p){
        this.owner = p;
        // Set city position
        if (p == Player.RED) {
            this.pos = new Position(1, 1);
        }
        else {
            this.pos = new Position(4, 1);
        }
    }

    //Constructor assigning Owner, position
    public CityImpl(Player play, Position p) {
        this.owner = play;
        this.pos = p;
    }

    //Accessor to get Owner
    public Player getOwner(){return this.owner;}

    //Accessor to get size
    public int getSize(){return this.size;}

    //Accessor to get amount of Production
    public int getTreasury(){return this.treasure;}

    //Accessor to get position
    public Position getPosition() { return this.pos; }

    //TODO
    public String getProduction(){return null;}
    public String getWorkforceFocus(){return null;}

    //Method to change the value of a city's treasure
    public void setTreasury(int t) { treasure = t; }

    //Change city owner
    public void setOwner(Player p) { this.owner = p; }

}
