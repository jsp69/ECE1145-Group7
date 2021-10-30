package hotciv.standard;

import hotciv.framework.*;

import java.util.*;
/** DeltaCiv implementation of HotCiv */

public class DeltaCivImpl extends GameImpl {
    /** Code copied from StubGame1.java and edited for the DeltaCiv */
    public DeltaCivImpl(HotCivFactory factory) {
        super(factory);
        world = defineWorld();
        // Create cities at (8,12) and (4,5)
        cityLoc[8][12] = new CityImpl(Player.RED);
        cityLoc[4][5] = new CityImpl(Player.BLUE);
    }

    // A simple implementation to draw the map of DeltaCiv
    protected Map<Position, Tile> world;
    public Tile getTileAt( Position p ) { return world.get(p); }

    /** Define the world as the DeltaCiv layout */
    private Map<Position,Tile> defineWorld() {
        // Basically we use a 'data driven' approach - code the
        // layout in a simple semi-visual representation, and
        // convert it to the actual Game representation.
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        // Conversion...
        Map<Position,Tile> theWorld = new HashMap<Position,Tile>();
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                theWorld.put(p, new TileImpl(type));
            }
        }
        return theWorld;
        }
}
