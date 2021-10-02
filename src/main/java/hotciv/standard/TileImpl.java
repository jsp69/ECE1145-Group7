package hotciv.standard;

import hotciv.framework.*;

public class TileImpl implements Tile {
    String tType;
    Position pos;

    public TileImpl(String type) { this.tType = type; }

    //Constructor for type, position
    public TileImpl(String type, Position p) {
        this.tType = type;
        this.pos = p;
    }

    public String getTypeString() { return this.tType; }
    
}
