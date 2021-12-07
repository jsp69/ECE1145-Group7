package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;

public class AlphaMoveAttack implements MoveAttackStrat {
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
        String unitType = u[x1][y1].getTypeString();

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
        else if (from.getRow() == to.getRow()) {
            if (absDisCol == 1) { moveType = "v"; }
            else { return false; }
        }
        else if (from.getColumn() == to.getColumn()){
            if (absDisRow == 1) { moveType = "h"; }
            else { return false; }
        }
        else { return false; }

        u[x2][y2] = u[x1][y1];  // Copy unit
        u[x1][y1] = null;       // Delete old unit
        return true;
    }

    @Override
    public City[][] getCitiesArray() { return c; }

    @Override
    public Tile[][] getTilesArray() { return t; }

    @Override
    public Unit[][] getUnitsArray() { return u; }
}
