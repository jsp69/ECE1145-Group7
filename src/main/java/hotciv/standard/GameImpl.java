package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {

  // Preliminary set-up for AlphaCiv
  // Set turn count
  int turn = 0;
  int age = -4000;
  Position rArcher = new Position(2, 0);
  Position rSettler = new Position(4, 3);
  Position rLegion = new Position (2,3);
  Position bArcher = new Position(0,2);
  Position bSettler = new Position(3, 4);
  Position bLegion = new Position(3, 2);

  // Set array to use for units
  // Array index corresponds to unit position unitLoc[Position row][Position Column]
  Unit[][] unitLoc=new UnitImpl[16][16];

  // Array index corresponds to tile position tileLoc[Position row][Position Column]
  Tile [][] tileLoc = new TileImpl[16][16];

  // Array index corresponds to city position cityLoc[Position row][Position Column]
  City [][] cityLoc = new CityImpl[16][16];

  public GameImpl(){
    unitLoc[2][0] = new UnitImpl(GameConstants.ARCHER, Player.RED, rArcher);
    unitLoc[4][3] = new UnitImpl(GameConstants.SETTLER, Player.RED, rSettler);
    unitLoc[2][3] = new UnitImpl(GameConstants.SETTLER, Player.RED, rLegion);
    unitLoc[0][2] = new UnitImpl(GameConstants.ARCHER, Player.BLUE, bArcher);
    unitLoc[3][4] = new UnitImpl(GameConstants.SETTLER, Player.BLUE, bSettler);
    unitLoc[3][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE, bLegion);
    unitsMaxMoveAtStart();

    tileLoc[1][0] = new TileImpl(GameConstants.OCEANS, new Position(1, 0));
    tileLoc[0][1] = new TileImpl(GameConstants.HILLS, new Position(0, 1));
    tileLoc[2][2] = new TileImpl(GameConstants.MOUNTAINS, new Position(2, 2));

    cityLoc[1][1] = new CityImpl(Player.RED, new Position(1, 1));
    cityLoc[4][1] = new CityImpl(Player.BLUE, new Position(4, 1));
  }

  public Tile getTileAt( Position p ) {
    if ((p.getColumn() == 0) && (p.getRow() == 1)) {
      return tileLoc[1][0];
    }
    else if ((p.getColumn() == 1) && (p.getRow() == 0)) {
      return tileLoc[0][1];
    }
    else if ((p.getColumn() == 2) && (p.getRow() == 2)) {
      return tileLoc[2][2];
    }
    else {
      // Every other tile is PLAINS
      tileLoc[p.getRow()][p.getColumn()] = new TileImpl(GameConstants.PLAINS, p);
      return tileLoc[p.getRow()][p.getColumn()];
    }
  }
  public Unit getUnitAt( Position p ) {
    //Check if a unit exists at position p and return if so
    if(unitLoc[p.getRow()][p.getColumn()] != null){
      return unitLoc[p.getRow()][p.getColumn()];
    }else{
      return null;
    }
  }
  public City getCityAt( Position p ) {
    //Check if a city exists at position p and return if so
    if(cityLoc[p.getRow()][p.getColumn()] != null){
      return cityLoc[p.getRow()][p.getColumn()];
    }else{
      return null;
    }
  }
  public Player getPlayerInTurn() {
    if (turn == 0) {
      return Player.RED;
    }
    else {
      return Player.BLUE;
    }
  }
  public Player getWinner() {
    //Red wins if 3000 BC
    if (getAge() == -3000) {
      return Player.RED;
    }
    //Attacking unit wins
    if (unitAttacks()) {
      if (Objects.equals(unitLoc[2][0].getTypeString(), GameConstants.ARCHER)) {
        return unitLoc[2][0].getOwner();
      }
      if (Objects.equals(unitLoc[4][3].getTypeString(), GameConstants.SETTLER)) {
        return unitLoc[4][3].getOwner();
      }
      if (Objects.equals(unitLoc[2][3].getTypeString(), GameConstants.LEGION)) {
        return unitLoc[2][3].getOwner();
      }
      if (Objects.equals(unitLoc[0][2].getTypeString(), GameConstants.ARCHER)) {
        return unitLoc[0][2].getOwner();
      }
      if (Objects.equals(unitLoc[3][4].getTypeString(), GameConstants.SETTLER)) {
        return unitLoc[3][4].getOwner();
      }
      if (Objects.equals(unitLoc[3][2].getTypeString(), GameConstants.ARCHER)) {
        return unitLoc[3][2].getOwner();
      }
    }
    return null;
  }
  public int getAge() {
    return this.age;
  }

  public boolean moveUnit( Position from, Position to ) {
    //Ensure new position isn't mountains
    System.out.print("moveUnit(): ");
    System.out.print(from);
    System.out.println(to);
    if (Objects.equals(getTileAt(to).getTypeString(), GameConstants.MOUNTAINS)) {
      return false;
    }
    // Ensure it is the correct player's unit
    System.out.println("getUnitAt(from): ");
    System.out.println(getUnitAt(from));
    if (getPlayerInTurn() != getUnitAt(from).getOwner()) {
      return false;
    }
    else {
      // Return true if unit was moved
      int disCol = from.getColumn() - to.getColumn();
      int disRow = from.getRow() - to.getRow();
      boolean southNorth = (disCol == -1) || (disCol == 1);
      boolean eastWest = (disRow == -1) || (disRow == 1);
      boolean zeros = (disCol == 0) || (disRow == 0);
      // Check that position is only a distance of 1 in any given direction
      if ((southNorth || eastWest) && zeros) {
        // Change position of unit
        unitLoc[to.getRow()][to.getColumn()] = unitLoc[from.getRow()][from.getColumn()];
        unitLoc[from.getRow()][from.getColumn()] = null;
        ((UnitImpl)(getUnitAt(from))).changePosition(to);
        //Change city ownership
        if ((getCityAt(to).equals(cityLoc[4][1])) && (getUnitAt(to).getOwner() == Player.RED)) {
          ((CityImpl)(cityLoc[4][1])).setOwner(Player.RED);
        }
        return true;
      }
      else {
        return false;
      }
    }
  }

  public boolean moveUnitMore(Position from, Position to) {
    Position oldP = new Position(from);
    while (!moveUnit(from,to)) {
      System.out.print("while: \n");
      //Add one to row, move unit
      if (from.getRow() != to.getRow()) {
        from.setRow(from.getRow() + 1);
        System.out.print("oldP: ");
        System.out.println(getUnitAt(oldP));
        System.out.print("p1: ");
        System.out.println(getUnitAt(from));
        moveUnit(oldP, from);
        oldP.setRow(from.getRow());
      }
      //Add one to column, move unit
      if (from.getColumn() != to.getColumn()) {
        from.setColumn(from.getColumn() + 1);
        System.out.print("oldP: ");
        System.out.println(getUnitAt(oldP));
        System.out.print("p1: ");
        System.out.println(getUnitAt(from));
        moveUnit(oldP, from);
        oldP.setColumn(from.getColumn());
      }
    }
    System.out.print("outta");
    //Check if unit has been moved
    boolean unitMoveCheck = (from.getColumn() == to.getColumn()) && (from.getRow() == to.getRow());
    return unitMoveCheck;
  }

  public void endOfTurn() {
    // Increment turn count
    this.turn++;
    // Check if round is over
    if (this.turn > 1) {
      endOfRound();
    }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}
  public void endOfRound() {
    // Add 6 production to each city
    ((CityImpl)(cityLoc[1][1])).setTreasury((cityLoc[1][1]).getTreasury() + 6);
    ((CityImpl)(cityLoc[4][1])).setTreasury((cityLoc[4][1]).getTreasury() + 6);
    // Reset turn counter
    this.turn = 0;
    increaseAge();
  }

  //Moves age forward
  public void increaseAge() { this.age = this.age+100; }

  // Establish new city
  public City settlerNewCity(Position p) {
    // Check position is a settler
    if (p == rSettler) {
      // Create new city, delete settler
      rSettler = null;
      unitLoc[p.getRow()][p.getColumn()] = null;
      cityLoc[p.getRow()][p.getColumn()] = new CityImpl(Player.RED, p);
      return cityLoc[p.getRow()][p.getColumn()];
    }
    else {
      return null;
    }
  }

  // Fortify the archers
  public boolean archersFortify(Position pos) {
    int r = pos.getRow();
    int c = pos.getColumn();
    //Check that unit is archers
    if (unitLoc[r][c].getTypeString().equals(GameConstants.ARCHER)) {
      // Set attack to 0
      ((UnitImpl)(unitLoc[r][c])).setAttack(0);
      // Set defenses to 5
      unitLoc[r][c].setDefenses(5);
      // Set max move count to 0
      unitLoc[r][c].setMoveCount(0);
      return true;
    }
    else {
      //Unit not archers
      return false;
    }
  }

  //Attacking unit is always true
  public boolean unitAttacks() {
    return true;
  }

  //Units get max move count at round start
  public void unitsMaxMoveAtStart() {
    //Check if round started
    if (getPlayerInTurn() == Player.RED || getPlayerInTurn() == Player.BLUE) {
      //Set max move count to 1
      for (int i = 0; i < 16; i++) {
        for(int j = 0; j < 16; j++) {
          if(unitLoc[i][j] != null) {
            unitLoc[i][j].setMoveCount(1);
          }
        }
      }
    }
  }

}
