package hotciv.standard;

import hotciv.framework.*;
import hotciv.stub.StubTile;

import java.util.HashMap;
import java.util.Map;

public class DeltaWorld implements WorldStrat {

    Map<Position, Tile> world;

    DeltaWorld() {
        for(int i=0;i<16;i++){
            for(int j=0;j<16;j++){
                u[i][j] = null;
                t[i][j] = null;
                c[i][j] = null;
            }
        }
        gameBoard();
    }

    @Override
    public void gameBoard() {

        // Basically we use a 'data driven' approach - code the
        // layout in a simple semi-visual representation, and
        // convert it to the actual Game representation.
        String[] layout =
                new String[]{
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
        world = new HashMap<>();
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            line = layout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char tileChar = line.charAt(c);
                String type = "error";
                if (tileChar == '.') {
                    type = GameConstants.OCEANS;
                }
                if (tileChar == 'o') {
                    type = GameConstants.PLAINS;
                }
                if (tileChar == 'M') {
                    type = GameConstants.MOUNTAINS;
                }
                if (tileChar == 'f') {
                    type = GameConstants.FOREST;
                }
                if (tileChar == 'h') {
                    type = GameConstants.HILLS;
                }
                Position p = new Position(r, c);
                world.put(p, new TileImpl(type));
            }
        }

        Position p = new Position(0, 0);
        for (int i = 0; i < 16; i++) {
            p.setRow(i);
            for (int j = 0; j < 16; j++) {
                p.setColumn(j);
                t[i][j] = world.get(p);
            }
        }

        // Original units in DeltaWorld
        u[3][8] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        u[4][4] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        u[5][5] = new UnitImpl(GameConstants.SETTLER, Player.RED);

        // Original cities in DeltaWorld
        c[8][12] = new CityImpl(Player.RED);
        c[4][5] = new CityImpl(Player.BLUE);
    }

    @Override
    public City[][] getCitiesArray() {
        return c;
    }

    @Override
    public Tile[][] getTilesArray() {
        return t;
    }

    @Override
    public Unit[][] getUnitsArray() {
        return u;
    }

}
