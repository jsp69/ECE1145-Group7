package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.Collection;
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

  // Set array to use for units
  // Array index corresponds to unit position unitLoc[Position row][Position Column]
  Unit[][] unitLoc = new UnitImpl[16][16];

  // Array index corresponds to tile position tileLoc[Position row][Position Column]
  Tile [][] tileLoc = new TileImpl[16][16];

  // Array index corresponds to city position cityLoc[Position row][Position Column]
  City [][] cityLoc = new CityImpl[16][16];

  //Factory object and strategies for variation control
  HotCivFactory civFactory;
  WinStrat winStrat;
  AgingStrat ageStrat;
  MoveAttackStrat moveStrat;
  CityStrat cityStrat;
  GameStrat gameStrat;
  UnitActionStrat unitStrat;

  public GameImpl(HotCivFactory factory){
    //Use Abstract Factory object to generate variant method algorithms
    civFactory=factory;
    winStrat=factory.createWinStrat();
    ageStrat=factory.createAgingStrat();
    moveStrat=factory.createMoveAttackStrat();
    cityStrat=factory.createCityStrat();
    gameStrat=factory.createGameStrat();
    unitStrat=factory.createUnitActionStrat();

    unitLoc = gameStrat.getUnitsArray();
    tileLoc = gameStrat.getTilesArray();
    cityLoc = gameStrat.getCitiesArray();
    unitsMaxMoveAtStart();
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
    return winStrat.getWinner(new WinnerStrategyContext());
  }

  @Override
  public int getAge() {
    return age;
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
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {((CityImpl)(getCityAt(p))).setWorkforceFocus(balance);}
  public void changeProductionInCityAt( Position p, String unitType ) {((CityImpl)(getCityAt(p))).setProduction(unitType);}
  public void performUnitActionAt( Position p ) {
    System.out.print("hey");
    unitStrat.performUnitActionAt(p);
  }
  public void endOfRound() {
    //Grow all cities that meet conditions and increase food/production for each city
    cityStrat.cityGrow(this);
    cityStrat.cityProduce(this);

    //Go through all cities and see if they have finished production of their units
    //If so, place them in/around the city in the first available spot
    Position p=new Position(0,0);
    for(int i=0;i<16;i++){
      p.setRow(i);
      for(int j=0;j<16;j++){
        p.setColumn(j);
        if(cityLoc[i][j]!=null){
          if(Objects.equals(cityLoc[i][j].getProduction(), GameConstants.ARCHER) && cityLoc[i][j].getTreasury()>=10){
            if(placeUnitAround(p,GameConstants.ARCHER,getCityAt(p).getOwner())){
              ((CityImpl)(cityLoc[i][j])).setTreasury(cityLoc[i][j].getTreasury()-10);
            }
          }else if(Objects.equals(cityLoc[i][j].getProduction(), GameConstants.LEGION) && cityLoc[i][j].getTreasury()>=15){
            if(placeUnitAround(p,GameConstants.LEGION,getCityAt(p).getOwner())){
              ((CityImpl)(cityLoc[i][j])).setTreasury(cityLoc[i][j].getTreasury()-15);
            }
          }else if(Objects.equals(cityLoc[i][j].getProduction(), GameConstants.SETTLER) && cityLoc[i][j].getTreasury()>=20){
            if(placeUnitAround(p,GameConstants.SETTLER,getCityAt(p).getOwner())){
              ((CityImpl)(cityLoc[i][j])).setTreasury(cityLoc[i][j].getTreasury()-20);
            }
          }
        }
      }
    }

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

  private boolean placeUnitAround(Position p, String type, Player o){
    if(unitLoc[p.getRow()][p.getColumn()]==null){
      unitLoc[p.getRow()][p.getColumn()]=new UnitImpl(type,o);
      return true;
    }else if(p.getRow()>0 && checkSafeUnitTile(new Position(p.getRow()-1,p.getColumn()))) {
      if (unitLoc[p.getRow() - 1][p.getColumn()] == null) {
        unitLoc[p.getRow() - 1][p.getColumn()] = new UnitImpl(type, o);
        return true;
      }
    }else if(p.getRow()>0 && p.getColumn()<15 && checkSafeUnitTile(new Position(p.getRow()-1,p.getColumn()+1))){
      if (unitLoc[p.getRow() - 1][p.getColumn()+1] == null) {
        unitLoc[p.getRow() - 1][p.getColumn()+1] = new UnitImpl(type, o);
        return true;
      }
    }else if(p.getColumn()<15 && checkSafeUnitTile(new Position(p.getRow(),p.getColumn()+1))){
      if (unitLoc[p.getRow()][p.getColumn()+1] == null) {
        unitLoc[p.getRow()][p.getColumn()+1] = new UnitImpl(type, o);
        return true;
      }
    }else if(p.getRow()<15 && p.getColumn()<15 && checkSafeUnitTile(new Position(p.getRow()+1,p.getColumn()+1))){
      if (unitLoc[p.getRow() + 1][p.getColumn() + 1] == null) {
        unitLoc[p.getRow() + 1][p.getColumn() + 1] = new UnitImpl(type, o);
        return true;
      }
    }else if(p.getRow()<15 && checkSafeUnitTile(new Position(p.getRow()+1,p.getColumn()))){
      if (unitLoc[p.getRow() + 1][p.getColumn()] == null) {
        unitLoc[p.getRow() + 1][p.getColumn()] = new UnitImpl(type, o);
        return true;
      }
    }else if(p.getRow()<15 && p.getColumn()>0 && checkSafeUnitTile(new Position(p.getRow()+1,p.getColumn()-1))){
      if (unitLoc[p.getRow() + 1][p.getColumn()-1] == null) {
        unitLoc[p.getRow() + 1][p.getColumn()-1] = new UnitImpl(type, o);
        return true;
      }
    }else if(p.getColumn()>0 && checkSafeUnitTile(new Position(p.getRow(),p.getColumn()-1))){
      if (unitLoc[p.getRow()][p.getColumn()-1] == null) {
        unitLoc[p.getRow()][p.getColumn()-1] = new UnitImpl(type, o);
        return true;
      }
    }else if(p.getRow()>0 && p.getColumn()>0 && checkSafeUnitTile(new Position(p.getRow()-1,p.getColumn()-1))){
      if (unitLoc[p.getRow() - 1][p.getColumn()-1] == null) {
        unitLoc[p.getRow() - 1][p.getColumn()-1] = new UnitImpl(type, o);
        return true;
      }
    }
    return false;
  }

  private boolean checkSafeUnitTile(Position p){
    return !tileLoc[p.getRow()][p.getColumn()].getTypeString().equals(GameConstants.OCEANS) && !tileLoc[p.getRow()][p.getColumn()].getTypeString().equals(GameConstants.MOUNTAINS);
  }

  public void createNewCity(int x, int y, Player player) {
    cityLoc[x][y] = new CityImpl(player);
  }
}
