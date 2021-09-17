package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

public class CityImpl implements City {
    Player owner;
    int size;
    int treasure;

    //Constructor assigning Owner
    public CityImpl(Player p){this.owner=p;}

    //Accessor to get Owner
    public Player getOwner(){return this.owner;}

    //Accessor to get size
    public int getSize(){return this.size;}

    //Accessor to get amount of Production
    public int getTreasury(){return this.treasure;}

    //TODO
    public String getProduction(){ return null; }
    public String getWorkforceFocus(){return null;}

}
