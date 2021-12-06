package hotciv.standard;

import hotciv.framework.*;
import hotciv.stub.StubGameObserver;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class TestGameObserver {
    private Game game;
    private Object StubGameObserver;

    @Before
    public void setUp() {
        game = new GameImpl(new AlphaFactory());
    }

    @Test
    public void worldChanged() {
        //assertThat(game, is(notNullValue()));
        //Check if unit appears or disappears
        Position redArcher = new Position(2, 0);
        //Check unit attacking strength
        if (game.getUnitAt(redArcher).getAttackingStrength() == 0) {
            //Check the winner is blue
            assertThat(game.moveUnit(new Position(3, 3), new Position(3, 2)), is(true));
        }
        assertNotNull(StubGameObserver);
    }
}
