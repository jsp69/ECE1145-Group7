package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestArcherFortifyStrategy {

    private Game game;
    /** Fixture for GammaCiv Strategy testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new GammaFactory());
    }

    @Test
    public void redArcherFortifies() {
        Position redArcher = new Position(2, 0);
        assertThat(game, is(notNullValue()));
        if (game.getUnitAt(redArcher).getTypeString().equals(GameConstants.ARCHER)) {
            //Check if defensive strength doubles
            int doubled = game.getUnitAt(redArcher).getDefensiveStrength() * 2;
            game.performUnitActionAt(redArcher);
            assertThat(doubled, is(game.getUnitAt(redArcher).getDefensiveStrength()));
            //Check move count set to 0
            assertThat(game.getUnitAt(redArcher).getMoveCount(), is(0));
        }
    }

    @Test
    public void blueArcherFortifies() {
        Position blueArcher = new Position(0, 2);
        assertThat(game, is(notNullValue()));
        game.endOfTurn();
        if (game.getUnitAt(blueArcher).getTypeString().equals(GameConstants.ARCHER)) {
            //Check if defensive strength doubles
            int doubled = game.getUnitAt(blueArcher).getDefensiveStrength() * 2;
            game.performUnitActionAt(blueArcher);
            assertThat(doubled, is(game.getUnitAt(blueArcher).getDefensiveStrength()));
            //Check move count set to 0
            assertThat(game.getUnitAt(blueArcher).getMoveCount(), is(0));
        }
    }
}


