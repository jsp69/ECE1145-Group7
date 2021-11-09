package hotciv.standard;

import hotciv.framework.*;

public class ThetaGame implements GameStrat {
    public ThetaGame(){
        gameBoard();
    }

    @Override
    public void gameBoard() {
        u[2][0] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        u[4][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        u[2][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        u[3][3] = new UnitImpl(GameConstants.LEGION, Player.RED);
        u[0][2] = new UnitImpl(GameConstants.ARCHER, Player.BLUE);
        u[3][4] = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
        u[3][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        u[4][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        u[5][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        u[2][1] = new UnitImpl(GameConstants.UFO, Player.RED);
        u[1][1] = new UnitImpl(GameConstants.UFO, Player.BLUE);
        u[1][2] = new UnitImpl(GameConstants.UFO, Player.BLUE);

        t[1][0] = new TileImpl(GameConstants.OCEANS, new Position(1, 0));
        t[0][1] = new TileImpl(GameConstants.HILLS, new Position(0, 1));
        t[2][2] = new TileImpl(GameConstants.MOUNTAINS, new Position(2, 2));
        t[0][3] = new TileImpl(GameConstants.MOUNTAINS, new Position(0, 3));
        t[2][1] = new TileImpl(GameConstants.FOREST, new Position(2,1));

        for(int i=0;i<16;i++){
            for(int j=0;j<16;j++){
                if(t[i][j]==null){
                    t[i][j]=new TileImpl(GameConstants.PLAINS,new Position(i,j));
                }
            }
        }

        c[1][1] = new CityImpl(Player.RED);
        c[4][1] = new CityImpl(Player.BLUE);
        c[3][2] = new CityImpl(Player.BLUE);
    }

    @Override
    public City[][] getCitiesArray() { return c; }

    @Override
    public Tile[][] getTilesArray() {
        return t;
    }

    @Override
    public Unit[][] getUnitsArray() {
        return u;
    }
}
