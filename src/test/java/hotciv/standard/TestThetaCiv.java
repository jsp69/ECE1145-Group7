package hotciv.standard;

import hotciv.framework.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestThetaCiv {

    private Game game;
    Position redUFO = new Position(2, 1);
    Position blueUFO = new Position(1,1);
    Position blueCity = new Position(3,2);

    /** Fixture for ThetaCiv testing. */
    @Before
    public void setUp() { game = new GameImpl(new ThetaFactory()); }

    /*@Test public void ufoProductionCost60() {

    }*/

    @Test public void moveOverMountains() {
        assertThat(game, is(notNullValue()));
        Position m = new Position(2, 2);
        assertTrue(game.moveUnit(redUFO, m));
    }

    @Test public void moveOverOceans() {
        assertThat(game, is(notNullValue()));
        game.endOfTurn();   //Change player to BLUE
        Position o = new Position(1, 0);
        assertTrue(game.moveUnit(blueUFO, o));
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
    /*@Test public void ufoActionDestroysCity() {
        assertThat(game, is(notNullValue()));
        Position redCity = new Position(1,1);
        game.performUnitActionAt(blueUFO);

    }*/

    @Test public void changeProductionAtCityToUFO() {
        assertThat(game, is(notNullValue()));
        game.changeProductionInCityAt(blueCity, GameConstants.UFO);
        assertEquals(game.getCityAt(blueCity).getProduction(), GameConstants.UFO);
    }

    /*@Test public void flyOverCityWithNoUnits() {
        assertThat(game, is(notNullValue()));

    }*/
}
