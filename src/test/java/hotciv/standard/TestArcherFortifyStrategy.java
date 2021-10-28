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
        game = new GammaCivImpl(new AlphaFactory());
    }

    @Test
    public void redArcherFortifies() {
        Position oldRedArcher = new Position(1, 0);
        Position newRedArcher = new Position(2, 0);
        assertThat(game, is(notNullValue()));
        if (newRedArcher.equals("archer")) {
            //Check if defensive strength doubles
            int oldDoubled = game.getUnitAt(oldRedArcher).getDefensiveStrength() * 2;
            assertThat(oldDoubled, is(game.getUnitAt(newRedArcher).getDefensiveStrength()));
            //Check move count set to 0
            assertThat(game.getUnitAt(newRedArcher).getMoveCount(), is(0));
        }
    }

    @Test
    public void blueArcherFortifies() {
        Position oldBlueArcher = new Position(1, 1);
        Position newBlueArcher = new Position(0, 2);
        assertThat(game, is(notNullValue()));
        if (newBlueArcher.equals("archer")) {
            //Check if defensive strength doubles
            int oldDoubled = game.getUnitAt(oldBlueArcher).getDefensiveStrength() * 2;
            assertThat(oldDoubled, is(game.getUnitAt(newBlueArcher).getDefensiveStrength()));
            //Check move count set to 0
            assertThat(game.getUnitAt(newBlueArcher).getMoveCount(), is(0));
        }
    }
}


