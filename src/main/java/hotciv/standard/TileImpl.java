package hotciv.standard;

import hotciv.framework.Tile;

public class TileImpl implements Tile {
    String tType;
    int produce;

    public TileImpl(String type) { this.tType = type; }

    public String getTypeString() { return "ocean"; }
    
}
