package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City {
    Player owner;
    static int size = 1;
    int treasure;

    //Constructor assigning Owner
    public CityImpl(Player p) {
        this.owner = p;
    }

    //Accessor to get Owner
    public Player getOwner(){return this.owner;}

    //Accessor to get size
    public int getSize(){return size;}

    //Accessor to get amount of Production
    public int getTreasury(){return this.treasure;}

    //TODO
    public String getProduction(){return null;}
    public String getWorkforceFocus(){return null;}

    //Method to change the value of a city's treasure
    public void setTreasury(int t) { treasure = t; }

    //Method to change owner of city
    public void setOwner(Player p){owner=p;}
}
