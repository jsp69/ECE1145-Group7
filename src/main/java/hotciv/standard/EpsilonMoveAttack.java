package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;
import java.util.Random;

public class EpsilonMoveAttack implements MoveAttackStrat {
    DiceRoll diceA, diceD;

    public EpsilonMoveAttack(DiceRoll A, DiceRoll D) {
        diceA = A;
        diceD = D;
    }

    public EpsilonMoveAttack() {
        diceA = new RandomRoll();
        diceD = new RandomRoll();
    }

    Unit[][] u;
    City[][] c;
    Tile[][] t;

    @Override
    public boolean moveUnit(Position from, Position to, Game game, City[][] cityLoc, Unit[][] unitLoc) {
        //Update arrays
        u = GameImpl.getUnitLoc();
        c = GameImpl.getCityLoc();
        t = GameImpl.getTileLoc();

        //Get unit x and y coordinates
        int xFrom = from.getRow();
        int xTo = to.getRow();
        int yFrom = from.getColumn();
        int yTo = to.getColumn();

        //Ensure unit is not null
        if (u[xFrom][yFrom] == null) {
            return false;
        } else {
            //Ensure new position isn't mountains
            if (Objects.equals(t[xTo][yTo].getTypeString(), GameConstants.MOUNTAINS)) {
                return false;
            }
            // Ensure new position isn't oceans
            if (Objects.equals(t[xTo][yTo].getTypeString(), GameConstants.OCEANS)) {
                return false;
            }
            // Ensure it is the correct player's unit
            if (game.getPlayerInTurn() != u[xFrom][yFrom].getOwner()) {
                return false;
            }
            // Complete move
            return canMove(from, to);
        }
    }

    private boolean canMove(Position from, Position to) {
        int x1 = from.getRow();
        int y1 = from.getColumn();
        int x2 = to.getRow();
        int y2 = to.getColumn();

        // Distance in columns, rows
        int disCol = from.getColumn() - to.getColumn();
        int disRow = from.getRow() - to.getRow();
        int absDisCol = Math.abs(disCol);
        int absDisRow = Math.abs(disRow);

        // Type of move: vertical (v), horizontal (h), diagonal (d)
        String moveType = null;
        if (absDisCol == absDisRow) {
            if (absDisCol == 1) { moveType = "d"; }
            else { return false; }
        }
        else if (x1 == x2) {
            if (absDisCol == 1) { moveType = "v"; }
            else { return false; }
        }
        else if (y1 == y2){
            if (absDisRow == 1) { moveType = "h"; }
            else { return false; }
        }
        else { return false; }

        //Check if the tile is occupied by a unit
        if (u[x2][y2] != null) {
            //Check if both units are owned by the same player
            if (u[x2][y2].getOwner() == u[x1][y1].getOwner()) {
                return false;
            } else {
                int A = diceA.rollDice() * getTerrain(from) * getStrength(u[x1][y1].getAttackingStrength(), from);
                int D = diceD.rollDice() * getTerrain(to) * getStrength(u[x2][y2].getDefensiveStrength(), to);
                if (A > D) {
                    u[x2][y2] = u[x1][y1];
                    u[x1][y1] = null;
                    if (c[x2][y2] != null) {
                        ((CityImpl)(c[x2][y2])).setOwner(u[x2][y2].getOwner());
                    }
                } else {
                    u[x1][y1] = null;
                }
                return true;
            }
        } else {
            u[x2][y2] = u[x1][y1];
            u[x1][y1] = null;
            if (c[x2][y2] != null) {
                ((CityImpl)(c[x2][y2])).setOwner(u[x2][y2].getOwner());
            }
            return true;
        }
    }

    private int getStrength(int base, Position p){
        int count = 0;
        //Loop through rows around p
        for(int i = -1;i < 2;i++){
            //Only look at columns if within map bounds
            if((p.getRow()+ i >= 0) && (p.getRow() + i <= 16)){
                //Loop through cols around p
                for(int j = -1;j < 2;j++){
                    //Only look at positions if within map bounds
                    if((p.getColumn() + j >= 0) && (p.getColumn() + i <= 16)){
                        //Don't count current unit
                        if (j != 0 || i != 0) {
                            //Check if a unit is at that location
                            if(u[p.getRow() + i][p.getColumn() + j] != null) {
                                //Iterate count if a unit exists at that position with the same owner as the base unit
                                if (u[p.getRow() + i][p.getColumn() + j].getOwner() == u[p.getRow()][p.getColumn()].getOwner()) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count+base;
    }

    private int getTerrain(Position p){
        if(t[p.getRow()][p.getColumn()].getTypeString().equals(GameConstants.FOREST) || t[p.getRow()][p.getColumn()].getTypeString().equals(GameConstants.HILLS)){
            return 2;
        }
        if(c[p.getRow()][p.getColumn()] != null){
            return 3;
        }
        return 1;
    }

    @Override
    public City[][] getCitiesArray() { return c; }

    @Override
    public Tile[][] getTilesArray() { return t; }

    @Override
    public Unit[][] getUnitsArray() { return u; }
}
