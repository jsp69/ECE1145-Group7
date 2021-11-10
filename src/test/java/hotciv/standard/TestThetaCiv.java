package hotciv.standard;

import hotciv.framework.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestThetaCiv {

    private Game game;
    Position redUFO = new Position(2, 1);
    Position blueUFO = new Position(1,1);
    Position blueUFO1 = new Position(1,2);
    Position blueCity = new Position(3,2);
    Position blueCity1 = new Position(4,1);

    /** Fixture for ThetaCiv testing. */
    @Before
    public void setUp() { game = new GameImpl(new ThetaFactory()); }

    /*@Test public void ufoProductionCost60() {

    }*/

    @Test public void moveOverMountains() {
        assertThat(game, is(notNullValue()));
        // Tile (0,3) is mountains
        Position m = new Position(0, 3);
        assertTrue(game.moveUnit(redUFO, m));
    }

    @Test public void moveOverOceans() {
        assertThat(game, is(notNullValue()));
        game.endOfTurn();   //Change player to BLUE
        Position o = new Position(1, 0);
        assertTrue(game.moveUnit(blueUFO1, o));
    }

    @Test public void defenseStrength8() {
        assertThat(game, is(notNullValue()));
        assertEquals(game.getUnitAt(redUFO).getDefensiveStrength(), 8);
    }

    @Test public void attackStrength1() {
        assertThat(game, is(notNullValue()));
        assertEquals(game.getUnitAt(redUFO).getAttackingStrength(), 1);
    }

    @Test public void ufoActionDecreaseCityPopBy1() {
        assertThat(game, is(notNullValue()));
        game.performUnitActionAt(blueUFO);
        assertEquals(game.getCityAt(blueUFO).getSize(), 0);
    }

    @Test public void forestChangesToPlains() {
        assertThat(game, is(notNullValue()));
        assertEquals(game.getTileAt(redUFO).getTypeString(), GameConstants.FOREST);
        game.performUnitActionAt(redUFO);
        assertEquals(game.getTileAt(redUFO).getTypeString(), GameConstants.PLAINS);
    }

    @Test public void ufoActionDestroysCity() {
        assertThat(game, is(notNullValue()));
        // game.moveUnit(redUFO, blueCity);
    }

    @Test public void changeProductionAtCityToUFO() {
        assertThat(game, is(notNullValue()));
        game.changeProductionInCityAt(blueCity, GameConstants.UFO);
        assertEquals(game.getCityAt(blueCity).getProduction(), GameConstants.UFO);
    }

    @Test public void flyOverCityWithNoUnits() {
        assertThat(game, is(notNullValue()));
        assertTrue(game.moveUnit(redUFO, blueCity1));
        assertEquals(game.getCityAt(blueCity1).getOwner(), Player.BLUE);
    }
}
