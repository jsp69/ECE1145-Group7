package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;

public class ThetaMoveAttack implements MoveAttackStrat {
    Unit[][] u;
    City[][] c;
    Tile[][] t;

    //X and y values of position from and to
    int xFrom, xTo, yFrom, yTo;

    @Override
    public boolean moveUnit(Position from, Position to, Game game, City[][] cityLoc, Unit[][] unitLoc) {
        //Update arrays
        u = GameImpl.getUnitLoc();
        c = GameImpl.getCityLoc();
        t = GameImpl.getTileLoc();

        //Get unit x and y coordinates
        xFrom = from.getRow();
        xTo = to.getRow();
        yFrom = from.getColumn();
        yTo = to.getColumn();

        //Ensure unit is not null
        if (u[xFrom][yFrom] == null) {
            return false;
        } else {
            // Check for UFO
            if (!Objects.equals(u[xFrom][yFrom].getTypeString(), GameConstants.UFO)) {
                //Ensure new position isn't mountains
                if (Objects.equals(t[xTo][yTo].getTypeString(), GameConstants.MOUNTAINS)) {
                    return false;
                }
                // Ensure new position isn't oceans
                if (Objects.equals(t[xTo][yTo].getTypeString(), GameConstants.OCEANS)) {
                    return false;
                }
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
        String unitType = u[xFrom][yFrom].getTypeString();

        // Distance in columns, rows
        int disCol = from.getColumn() - to.getColumn();
        int disRow = from.getRow() - to.getRow();
        int absDisCol = Math.abs(disCol);
        int absDisRow = Math.abs(disRow);

        // Type of move: vertical (v), horizontal (h), diagonal (d)
        String moveType = null;
        if (absDisCol == absDisRow) {
            if (absDisCol == 2) { if (Objects.equals(unitType, GameConstants.UFO)) { moveType = "d"; } }
            else if (absDisCol == 1) { if (!Objects.equals(unitType, GameConstants.UFO)) { moveType = "d"; } }
            else { return false; }
        }
        else if (from.getRow() == to.getRow()) {
            if (absDisCol == 2) { if (Objects.equals(unitType, GameConstants.UFO)) { moveType = "v"; } }
            else if (absDisCol == 1) { if (!Objects.equals(unitType, GameConstants.UFO)) { moveType = "v"; } }
            else { return false; }
        }
        else if (from.getColumn() == to.getColumn()){
            if (absDisRow == 2) { if (Objects.equals(unitType, GameConstants.UFO)) { moveType = "h"; } }
            else if (absDisRow == 1) { if (!Objects.equals(unitType, GameConstants.UFO)) { moveType = "h"; } }
            else { return false; }
        }
        else { return false; }

        int moveCount = u[xFrom][yFrom].getMoveCount();
        if (moveCount == 2) {
            assert moveType != null;
            if (moveType.equals("d")) {
                //up and left
                if ((disCol > 0) && (disRow > 0)) {
                    moveUnit1(xFrom, yFrom, xTo+1, yTo+1);
                    moveUnit1(xTo+1,yTo+1,xTo,yTo);
                }
                //up and right
                else if ((disCol > 0) && (disRow < 0)) {
                    moveUnit1(xFrom, yFrom, xTo-1, yTo+1);
                    moveUnit1(xTo-1,yTo+1,xTo,yTo);
                }
                //down and right
                else if ((disCol < 0) && (disRow < 0)) {
                    moveUnit1(xFrom, yFrom, xTo-1, yTo-1);
                    moveUnit1(xTo-1,yTo-1,xTo,yTo);
                }
                //down and left
                else {
                    moveUnit1(xFrom, yFrom, xTo+1, yTo-1);
                    moveUnit1(xTo+1,yTo-1,xTo,yTo);
                }
            }
            else if (moveType.equals("v")) {
                //up
                if (disCol > 0) {
                    moveUnit1(xFrom, yFrom, xTo, yTo+1);
                    moveUnit1(xTo,yTo+1,xTo,yTo);
                }
                //down
                else {
                    moveUnit1(xFrom, yFrom, xTo, yTo-1);
                    moveUnit1(xTo, yTo-1, xTo, yTo);
                }
            }
            else {
                //left
                if (disRow > 0) {
                    moveUnit1(xFrom, yFrom, xTo - 1, yTo);
                    moveUnit1(xTo - 1, yTo, xTo, yTo);
                }
                //right
                else {
                    moveUnit1(xFrom, yFrom, xTo + 1, yTo);
                    moveUnit1(xTo + 1, yTo, xTo, yTo);
                }
            }

        }
        else {
            moveUnit1(xFrom, yFrom, xTo, yTo);
        }

        return true;
    }

    private void moveUnit1(int x1, int y1, int x2, int y2) {
        u[x2][y2] = u[x1][y1];  // Copy unit
        u[x1][y1] = null;       // Delete old unit
    }

    private void checkBattle() {
        //Get player in turn
        Player currPlayer = u[xFrom][yFrom].getOwner();
        //Check for UFO
        if (Objects.equals(u[xFrom][yFrom].getTypeString(), GameConstants.UFO)) {
            if (u[xTo][yTo] != null) {
                //UFO destroys city
                c[xTo][yTo] = null;
            }
        }
    }

    @Override
    public City[][] getCitiesArray() { return c; }

    @Override
    public Tile[][] getTilesArray() { return t; }

    @Override
    public Unit[][] getUnitsArray() { return u; }
}

