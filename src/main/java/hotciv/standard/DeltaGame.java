package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;
import java.util.Map;

public class DeltaGame implements GameStrat {

    Map<Position,Tile> world;

    DeltaGame(){
        world=defineWorld();
        gameBoard();
    }

    @Override
    public void gameBoard() {
        Position p=new Position(0,0);
        for(int i=0;i<16;i++){
            p.setRow(i);
            for(int j=0;j<16;j++){
                p.setColumn(j);
                t[i][j]=world.get(p);
            }
        }

        u[2][0] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        u[4][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        u[2][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        u[3][3] = new UnitImpl(GameConstants.LEGION, Player.RED);
        u[0][2] = new UnitImpl(GameConstants.ARCHER, Player.BLUE);
        u[3][4] = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
        u[3][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        u[4][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        u[5][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);

        c[1][1] = new CityImpl(Player.RED);
        c[4][1] = new CityImpl(Player.BLUE);
        c[3][2] = new CityImpl(Player.BLUE);
        c[8][12]= new CityImpl(Player.RED);
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
        Map<Position,Tile> theWorld = new HashMap<>();
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
