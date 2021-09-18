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
  City redCity = new CityImpl(Player.RED);
  City blueCity = new CityImpl(Player.BLUE);
  int turn = 0;

  public Tile getTileAt( Position p ) {
    return new TileImpl("ocean");
  }
  public Unit getUnitAt( Position p ) { return null; }
  public City getCityAt( Position p ) {
    // Set city positions
    Position rCity = new Position(1, 1);
    Position bCity = new Position(4, 1);
    if (p.equals(rCity)) {
      return redCity;
    }
    else if (p.equals(bCity)) {
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
    int attack = 1;
    if (getAge() == -3000) {
      return Player.RED;
    }
    if (attack == 1) {
      return Player.RED;
    }
    else {
      return null;
    }
  }
  public int getAge() {
    int age;
    int start = -4000;
    age = start;
    return age;
  }

  public boolean moveUnit( Position from, Position to ) {
    return false;
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
}
