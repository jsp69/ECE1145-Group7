package hotciv.standard;

import hotciv.framework.*;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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
public class TestAlphaCiv {
  private Game game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
    //game = new GameImpl(new GammaCivImpl());
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getPlayerInTurn(), CoreMatchers.is(Player.RED));
  }

  @Test
  public void redCityAt1_1(){
    Position p = new Position(1,1);
    assertThat(game, is(notNullValue()));
    //assertThat((game.getCityAt(p)).getOwner(), is(Player.RED));
  }

  @Test
  public void oceanAt1_1(){
    Position p = new Position(1,0);
    assertThat(game,is(notNullValue()));
    assertThat(game.getTileAt(p).getTypeString(),is(GameConstants.OCEANS));
  }

  @Test
  public void cannotMoveOverMountain() {
    assertThat(game,is(notNullValue()));
    // Set new and old positions
    Position mountain = new Position(2,2);
    Position pFrom = new Position(3,2);
    assertFalse(game.moveUnit(pFrom, mountain));
  }

  @Test
  public void redCannotMoveBlue() {
    assertThat(game, is(notNullValue()));
    // Ensure current player is RED
    assertEquals(game.getPlayerInTurn(), Player.RED);
    // Try to move Blue's legion at [3,2] to [3,3]
    Position posTo = new Position(3, 3);
    Position posFrom = new Position(3, 2);
    assertFalse(game.moveUnit(posFrom, posTo));
  }

  @Test
  public void endOfRoundProductionIs6More() {
    assertThat(game, is(notNullValue()));
    // Set variables for treasury amounts and city positions
    Position rCity = new Position(1, 1);
    Position bCity = new Position(4, 1);
    int oldTresRed = (game.getCityAt(rCity)).getTreasury();
    int oldTresBlue = (game.getCityAt(bCity)).getTreasury();
    // Run the end of turn method twice to signal end of round
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    // Check that production was increased by 6 in both cities
    assertEquals(oldTresRed + 6, (game.getCityAt(rCity)).getTreasury());
    assertEquals(oldTresBlue + 6, (game.getCityAt(bCity)).getTreasury());
  }

  @Test
  public void blueAfterRed() {
    assertThat(game, is(notNullValue()));
    // Check current player
    if (game.getPlayerInTurn().equals(Player.RED)) {
      // End current player's turn
      game.endOfTurn();
      // Check next player is blue
      assertEquals(Player.BLUE, game.getPlayerInTurn());
    }
  }

  @Test
  public void cityPopulationAlways1() {
    assertThat(game, is(notNullValue()));
    // Set city positions
    Position rCity = new Position(1, 1);
    Position bCity = new Position(4, 1);
    // Check population sizes of cities
    assertEquals(1, (game.getCityAt(rCity)).getSize());
    assertEquals(1, game.getCityAt(bCity).getSize());
  }

  @Test
  public void redWinsIn3000() {
    assertThat(game, is(notNullValue()));
    //Check year of game
    if(game.getAge() == -3000) {
      //Check the winner is red
      assertThat(game.getWinner(), is(Player.RED));
    }
  }

  @Test
  public void redAttacksBlue() {
    //Set unit position
    Position pos = new Position(2, 0);
    assertThat(game, is(notNullValue()));
    //Check unit attacking strength
    if(game.getUnitAt(pos).getAttackingStrength() > 0) {
      //Check the winner is red
      assertThat(game.getWinner(), is(Player.RED));
    }
  }

  @Test
  public void redArcher2_0(){
    //Set unit position
    Position p = new Position(2,0);
    assertThat(game,is(notNullValue()));
    //Check the unit type
    assertThat(game.getUnitAt(p).getTypeString(),is(GameConstants.ARCHER));
  }

  @Test
  public void gameStartsAt4000() {
    assertThat(game, is(notNullValue()));
    //Check if the game started
    if((game.getPlayerInTurn() == Player.RED) && (game.getAge() > -4000)) {
      //Check the year is 4000BC
      assertThat(game.getAge(), is(-4000));
    }
  }

  @Test
  public void unitsGetMaxMoveStart() {
    Position[] p = {new Position(2, 0), new Position(3, 2), new Position(4, 3)};
    assertThat(game, is(notNullValue()));
    //check if round started
    if(game.getPlayerInTurn() == Player.RED || game.getPlayerInTurn() == Player.BLUE) {
      //Check that units have max move count
      for (int i = 0; i < p.length; i++) {
        assertThat(game.getUnitAt(p[i]).getMoveCount(), is(1));
      }
    }
  }

  @Test
  public void yearAdvances100() {
    assertThat(game, is(notNullValue()));
    //Check if game age is divisible by 100
    if (game.getAge() % 100 == 0) {
      //Check that the game's age is equal to it
      assertThat(game.getAge(), is(game.getAge()));
    }
  }

  /*@Test
  public void redTakesBlueMovesOnTo() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(2, 0);
    Position blueCity = new Position(4, 1);
    //Check that red unit is attacking
    if(((GameImpl)(game)).moveUnitMore(p, blueCity)) {
      assertThat(game.getUnitAt(blueCity).getAttackingStrength(), is(1));
      //Check that blue owner becomes red
      assertEquals((game.getCityAt(blueCity)).getOwner(), Player.RED);
    }
  }*/

  @Test
  public void redUnitMovesAndDefends() {
    assertThat(game, is(notNullValue()));
    //Check if position has red city
    Position p = new Position(1, 1);
    Position p_interim = new Position(2, 1);
    if(game.getCityAt(p).getOwner() == (Player.RED)) {
      //Move Red unit to the city
      game.moveUnit(((UnitImpl)(((GameImpl)(game)).unitRed1)).getPosition(), p_interim);
      game.moveUnit(p_interim, p);
      //Check that the red unit can defend its city
      assertThat(game.getUnitAt(p).getDefensiveStrength(), is(1));
    }
  }

  @Test
  public void redUnitAttacksBlueAndWins() {
    assertThat(game, is(notNullValue()));
    // Check that it is Red's turn
    assertEquals(game.getPlayerInTurn(), Player.RED);
    // Get positions of current units
    Position bUnit = new Position(3, 2);
    Position rUnit = new Position(2, 0);
    // Red attacks blue, wins if attacking strength is greater than blue's defenses
    if (game.getUnitAt(rUnit).getAttackingStrength() > game.getUnitAt(bUnit).getDefensiveStrength()) {
      assertThat((game.getUnitAt(bUnit)).getOwner(), is(Player.RED));
    }
  }

  @Test
  public void unitsCanMove1() {
    assertThat(game, is(notNullValue()));
    //Set unit position
    Position pos = new Position(2, 0);
    Position new1 = new Position(2, 1);
    Position new2 = new Position(3, 4);
    //Check movement
    assertTrue(game.moveUnit(pos, new1));
    assertFalse(game.moveUnit(new1, new2));
  }

  @Test
  public void settlerEstablishCityAndDisappear() {
    assertThat(game, is(notNullValue()));
    // Position of settler/new city
    Position newCity = ((GameImpl)(game)).rSettler;
    City redCity2 = ((GameImpl)(game)).settlerNewCity(newCity);
    // Check settler disappears, new city created
    assertNull(((GameImpl)(game)).rSettler);
    assertEquals(redCity2.getOwner(), Player.RED);
    Assert.assertEquals(((CityImpl)(redCity2)).getPosition(), newCity);
  }

  @Test
  public void archersFortifyCheck() {
    assertThat(game, is(notNullValue()));
    // Set archer, legion position
    Position archer = new Position(2, 0);
    Position legion = new Position(3, 2);
    assertTrue(((GameImpl)(game)).archersFortify(archer));
    assertFalse(((GameImpl)(game)).archersFortify(legion));
    //Check defense and move values
    assertEquals((game.getUnitAt(archer)).getMoveCount(), 0);
    assertEquals((game.getUnitAt(archer)).getDefensiveStrength(), 5);
  }

  @Test
  public void unitsCantMoveOnOceans() {
    assertThat(game, is(notNullValue()));
    //Set unit position
    Position pos = new Position(2, 0);
    Position ocean = new Position(1, 0);
    // Check that unit cannot be moved
    assertFalse(game.moveUnit(pos, ocean));
  }





  /*
  REMOVE ME. Not a test of HotCiv, just an example of what
      matchers the hamcrest library has...
  @Test
  public void shouldDefinitelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));

    // If you only validate substrings, use containsString
    assertThat("This is a dummy test", containsString("dummy"));

    // Match contents of Lists
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    // Note - ordering is ignored when matching using hasItems
    assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

    // Matchers may be combined, like is-not
    assertThat(l.get(0), is(not("Bumse")));
  }*/
}
