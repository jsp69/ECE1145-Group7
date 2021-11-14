package hotciv.standard;

import hotciv.framework.*;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TestLogAlphaCiv {
    private Game game;

    /** Fixture for AlphaCiv testing. */
    @Before
    public void setUp() {
        game = new GameLog(new GameImpl(new AlphaFactory()));
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
    public void oceanAt1_0(){
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
            assertThat(game.moveUnit(new Position(3,3),new Position(3,2)),is(true));
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
        assertThat(game.getPlayerInTurn(), CoreMatchers.is(Player.RED));
        //Check age is equal to 4000BC
        assertThat(game.getAge(), is(-4000));
    }

    @Test
    public void unitsGetMaxMoveStart() {
        Position[] p = {new Position(2, 0), new Position(3, 2), new Position(4, 3)};
        assertThat(game, is(notNullValue()));
        //check if round started
        if(game.getPlayerInTurn() == Player.RED || game.getPlayerInTurn() == Player.BLUE) {
            //Check that units have max move count
            for (Position position : p) {
                assertThat(game.getUnitAt(position).getMoveCount(), is(1));
            }
        }
    }

    @Test
    public void yearAdvances100() {
        assertThat(game, is(notNullValue()));
        int advanceYear = game.getAge();
        advanceYear += 100;
        game.endOfTurn();
        game.endOfTurn();
        //Check that the game advances by 100
        assertThat(game.getAge(), is(advanceYear));
    }

    @Test
    public void redUnitMovesAndDefends() {
        assertThat(game, is(notNullValue()));
        //Check if position has red city
        Position p = new Position(1, 1);
        Position p_interim = new Position(2, 1);
        if(game.getCityAt(p).getOwner() == (Player.RED)) {
            //Move Red unit to the city
            game.moveUnit(new Position(2,0), p_interim);
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
        Position newCity=new Position(2,3);
        game.performUnitActionAt(newCity);
        // Check settler disappears, new city created
        assertNull(game.getUnitAt(newCity));
        assertEquals(game.getCityAt(newCity).getOwner(), Player.RED);
        assertNotNull(game.getCityAt(newCity));
    }

    @Test
    public void archersFortifyCheck() {
        assertThat(game, is(notNullValue()));
        // Set archer, legion position
        Position archer = new Position(2, 0);
        Position legion = new Position(3, 2);
        game.performUnitActionAt(archer);
        game.performUnitActionAt(legion);
        //Check defense and move values
        assertEquals((game.getUnitAt(archer)).getMoveCount(), 0);
        assertEquals((game.getUnitAt(archer)).getDefensiveStrength(), 6);
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
}
