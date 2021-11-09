package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;

public class ThetaMoveAttack implements MoveAttackStrat {
    Unit[][] u;
    City[][] c;
    Tile[][] t;

    @Override
    public boolean moveUnit(Position from, Position to, Game game, City[][] cityLoc, Unit[][] unitLoc) {
        //Update arrays
        u = GameImpl.getUnitLoc();
        c = GameImpl.getCityLoc();
        t = GameImpl.getTileLoc();

        //Ensure unit is not null
        if (game.getUnitAt(from) == null) {
            return false;
        } else {
            // Check for UFO
            if (!Objects.equals(game.getUnitAt(from).getTypeString(), GameConstants.UFO)) {
                //Ensure new position isn't mountains
                if (Objects.equals(game.getTileAt(to).getTypeString(), GameConstants.MOUNTAINS)) {
                    return false;
                }
                // Ensure new position isn't oceans
                if (Objects.equals(game.getTileAt(to).getTypeString(), GameConstants.OCEANS)) {
                    return false;
                }
            }

            // Ensure it is the correct player's unit
            if (game.getPlayerInTurn() != game.getUnitAt(from).getOwner()) {
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
        System.out.print("disRow: " + disRow + "\n" + "disCol: " + disCol + "\n");

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
        System.out.print("moveType: " + moveType + "\n\n");

        int moveCount = u[x1][y1].getMoveCount();
        if (moveCount == 2) {
            assert moveType != null;
            if (moveType.equals("d")) {
                //up and left
                if ((disCol > 0) && (disRow > 0)) {
                    moveUnit1(x1, y1, x2+1, y2+1);
                    moveUnit1(x2+1,y2+1,x2,y2);
                }
                //up and right
                else if ((disCol > 0) && (disRow < 0)) {
                    moveUnit1(x1, y1, x2-1, y2+1);
                    moveUnit1(x2-1,y2+1,x2,y2);
                }
                //down and right
                else if ((disCol < 0) && (disRow < 0)) {
                    moveUnit1(x1, y1, x2-1, y2-1);
                    moveUnit1(x2-1,y2-1,x2,y2);
                }
                //down and left
                else {
                    moveUnit1(x1, y1, x2+1, y2-1);
                    moveUnit1(x2+1,y2-1,x2,y2);
                }
            }
            else if (moveType.equals("v")) {
                //up
                if (disCol > 0) {
                    moveUnit1(x1, y1, x2, y2+1);
                    moveUnit1(x2,y2+1,x2,y2);
                }
                //down
                else {
                    moveUnit1(x1, y1, x2, y2-1);
                    moveUnit1(x2, y2-1, x2, y2);
                }
            }
            else {
                //left
                if (disRow > 0) {
                    moveUnit1(x1, y1, x2-1, y2);
                    moveUnit1(x2-1, y2, x2, y2);
                }
                //right
                else {
                    moveUnit1(x1, y1, x2+1, y2);
                    moveUnit1(x2+1, y2, x2, y2);
                }
            }
        }
        else {
            moveUnit1(x1, y1, x2, y2);
        }

        return true;
    }

    private void moveUnit1(int x1, int y1, int x2, int y2) {
        u[x2][y2] = u[x1][y1];  // Copy unit
        u[x1][y1] = null;       // Delete old unit
    }

    @Override
    public City[][] getCitiesArray() { return c; }

    @Override
    public Tile[][] getTilesArray() { return t; }

    @Override
    public Unit[][] getUnitsArray() { return u; }
}

