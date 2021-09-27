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
  // Set cities
  City redCity = new CityImpl(Player.RED);
  City blueCity = new CityImpl(Player.BLUE);
  // Set turn count
  int turn = 0;
  Position rArcher = new Position(2, 0);
  Position bLegion = new Position(3, 2);
  Position rSettler = new Position(4, 3);
  // Set tiles
  Tile ocean = new TileImpl(GameConstants.OCEANS, new Position(1, 0));
  Tile hills = new TileImpl(GameConstants.HILLS, new Position(0, 1));
  Tile mountains = new TileImpl(GameConstants.MOUNTAINS, new Position(2, 2));
  // Set units
  Unit unitRed1 = new UnitImpl(GameConstants.ARCHER, Player.RED, rArcher);
  Unit unitRed2 = new UnitImpl(GameConstants.SETTLER, Player.RED, rSettler);
  Unit unitBlue = new UnitImpl(GameConstants.LEGION, Player.BLUE, bLegion);

  public Tile getTileAt( Position p ) {
    if ((p.getColumn() == 0) && (p.getRow() == 1)) {
      return ocean;
    }
    else if ((p.getColumn() == 1) && (p.getRow() == 0)) {
      return hills;
    }
    else if ((p.getColumn() == 2) && (p.getRow() == 2)) {
      return mountains;
    }
    else {
      return new TileImpl(GameConstants.PLAINS, p);
    }
  }
  public Unit getUnitAt( Position p ) {
    // Return correct unit
    if (p.equals(rArcher)) {
      return unitRed1;
    }
    else if (p.equals(bLegion)) {
      return unitBlue;
    }
    else if (p.equals(rSettler)) {
      return unitRed2;
    }
    else {
      return null;
    }
  }
  public City getCityAt( Position p ) {
    // Return correct city
    if (p.equals(((CityImpl)(redCity)).getPosition())) {
      return redCity;
    }
    else if (p.equals(((CityImpl)(blueCity)).getPosition())) {
      return blueCity;
    }
    else {
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
    //Red's unit wins if attacks
    if (unitRed1.getAttackingStrength() > 0) {
      return Player.RED;
    }
    else {
      return null;
    }
  }
  public int getAge() {
    int age = 0;
    //Set start year to 4000 BC
    if (turn == 0) {
      int start = -4000;
      age = start;
    }
    //Increment by 100 years
    if (turn > 0){
      age = age + 100;
      endOfRound();
    }
    return age;
  }

  public boolean moveUnit( Position from, Position to ) {
    //Ensure new position isn't mountains
    if (getTileAt(to).equals(mountains) || getTileAt(to).equals(ocean)) {
      return false;
    }
    // Ensure it is the correct player's unit
    if (getPlayerInTurn() != getUnitAt(from).getOwner()) {
      return false;
    }
    else {
      // Change position of unit
      ((UnitImpl)(getUnitAt(from))).changePosition(to);
      // Return true if unit was moved
      if (getUnitAt(to).equals(getUnitAt(to))) {
        //Change city ownership
        if ((to == ((CityImpl)(blueCity)).getPosition()) && (getUnitAt(to).getOwner() == Player.RED)) {
          ((CityImpl)(blueCity)).setOwner(Player.RED);
        }
        return true;
      }
      else {
        return false;
      }
    }
  }
  public void endOfTurn() {
    // Increment turn count
    turn = turn + 1;
    // Check if round is over
    if (turn > 1) {
      endOfRound();
    }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}
  public void endOfRound() {
    // Add 6 production to each city
    ((CityImpl)(redCity)).setTreasury(((CityImpl)(redCity)).getTreasury() + 6);
    ((CityImpl)(blueCity)).setTreasury(((CityImpl)(blueCity)).getTreasury() + 6);
    // Reset turn counter
    turn = 0;
  }



  // Establish new city
  public City settlerNewCity(Position p) {
    // Check position is a settler
    if (p == rSettler) {
      // Create new city, delete settler
      rSettler = null;
      return new CityImpl(Player.RED, p);
    }
    else {
      return null;
    }
  }

  // Fortify the archers
  public boolean archersFortify(Position pos) {
    Unit archers = getUnitAt(pos);
    //Check that unit is archers
    if (archers.getTypeString().equals(GameConstants.ARCHER)) {
      // Set attack to 0
      ((UnitImpl)(archers)).setAttack(0);
      // Set defenses to 5
      ((UnitImpl)(archers)).setDefenses(5);
      // Set max move count to 0
      ((UnitImpl)(archers)).setMoveCount(0);
      return true;
    }
    else {
      //Unit not archers
      return false;
    }
  }


}
