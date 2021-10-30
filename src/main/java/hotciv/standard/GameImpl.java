package hotciv.standard;

import hotciv.framework.*;

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

  // Set array to use for units
  // Array index corresponds to unit position unitLoc[Position row][Position Column]
  Unit[][] unitLoc = new UnitImpl[16][16];

  // Array index corresponds to tile position tileLoc[Position row][Position Column]
  Tile [][] tileLoc = new TileImpl[16][16];

  // Array index corresponds to city position cityLoc[Position row][Position Column]
  City [][] cityLoc = new CityImpl[16][16];

  //Array to track successful attacks from players
  int[] playerAttacks = {0,0,0,0};
  //Factory object and strategies for variation control
  CivFactory civFactory;
  WinStrat winStrat;
  AgingStrat ageStrat;
  MoveAttackStrat moveStrat;

  public GameImpl(CivFactory factory){
    unitLoc[2][0] = new UnitImpl(GameConstants.ARCHER, Player.RED);
    unitLoc[4][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
    unitLoc[2][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
    unitLoc[3][3] = new UnitImpl(GameConstants.LEGION, Player.RED);
    unitLoc[0][2] = new UnitImpl(GameConstants.ARCHER, Player.BLUE);
    unitLoc[3][4] = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
    unitLoc[3][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
    unitLoc[4][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
    unitLoc[5][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
    unitsMaxMoveAtStart();

    tileLoc[1][0] = new TileImpl(GameConstants.OCEANS, new Position(1, 0));
    tileLoc[0][1] = new TileImpl(GameConstants.HILLS, new Position(0, 1));
    tileLoc[2][2] = new TileImpl(GameConstants.MOUNTAINS, new Position(2, 2));

    cityLoc[1][1] = new CityImpl(Player.RED);
    cityLoc[4][1] = new CityImpl(Player.BLUE);
    cityLoc[3][2] = new CityImpl(Player.BLUE);

    //Use Abstract Factory object to generate variant method algorithms
    civFactory=factory;
    winStrat=factory.createWinStrat();
    ageStrat=factory.createAgingStrat();
    moveStrat=factory.createMoveAttackStrat();
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
    return winStrat.getWinner(this);
  }
  public int getAge() {
    return this.age;
  }

  public boolean moveUnit( Position from, Position to ) {
    //Store unit locations before movement
    Unit attacker=unitLoc[from.getRow()][from.getColumn()];
    Unit defender=unitLoc[to.getRow()][to.getColumn()];

    boolean move= moveStrat.moveUnit(from,to,this,cityLoc,unitLoc);

    //Check if a successful attack has occurred by comparing the previous unit at "to" and the current unit
    boolean attack=(defender!=null && defender.getOwner()!=attacker.getOwner() && attacker.getOwner()==unitLoc[to.getRow()][to.getColumn()].getOwner());

    //Increase attack count of current player if successful attack occurred
    if(attack){
      winStrat.increaseAttack(getPlayerInTurn());
    }
    return move;
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
    return (from.getColumn() == to.getColumn()) && (from.getRow() == to.getRow());
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
    winStrat.incrementRound();
  }

  //Moves age forward
  public void increaseAge() {
    this.age=ageStrat.increaseAge(this.age);
  }

  // Establish new city
  public City settlerNewCity(Position p) {
    // Check position is a settler
    if (p.getRow()==2 && p.getColumn()==3) {
      // Create new city, delete settler
      unitLoc[p.getRow()][p.getColumn()] = null;
      cityLoc[p.getRow()][p.getColumn()] = new CityImpl(getPlayerInTurn());
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
