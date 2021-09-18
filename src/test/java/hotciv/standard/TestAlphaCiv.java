package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

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
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void redCityAt1_1(){
    Position p = new Position(1,1);
    assertThat(game, is(notNullValue()));
    assertThat(game.getCityAt(p).getOwner(),is(Player.RED));
  }

  @Test
  public void oceanAt1_1(){
    Position p = new Position(1,0);
    assertThat(game,is(notNullValue()));
    assertThat(game.getTileAt(p).getTypeString(),is("ocean"));
  }

  @Test
  public void cannotMoveOverMountain(){
    Position pTo = new Position(3,3);
    Position pFrom = new Position(2,2);
    assertThat(game,is(notNullValue()));
    assertThat(game.moveUnit(pFrom,pTo),is(false));
  }

  @Test
  public void redCannotMoveBlue() {
    assertThat(game, is(notNullValue()));
    // Ensure current player is RED
    Player currentPlayer = game.getPlayerInTurn();
    assertEquals(currentPlayer, Player.RED);
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
  public void cityPopulationAlways1() {
    assertThat(game, is(notNullValue()));
    // Set city positions
    Position rCity = new Position(1, 1);
    Position bCity = new Position(4, 1);
    // Check population sizes of cities
    assertEquals(1, game.getCityAt(rCity).getSize());
    assertEquals(1, game.getCityAt(bCity).getSize());
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
  public void redWinsIn3000() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void redAttacksBlue() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void gameStartsAt4000() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void unitsCannotMoveOceans() {
    Position pTo = new Position(1,0);
    Position pFrom = new Position(1,1);
    assertThat(game,is(notNullValue()));
    assertThat(game.moveUnit(pFrom,pTo),is(false));
  }







  /*
  REMOVE ME. Not a test of HotCiv, just an example of what
      matchers the hamcrest library has...
  @Test
  public void shouldDefinetelyBeRemoved() {
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
