package hotciv.standard;

import hotciv.framework.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestDeltaCiv {

    private Game game;
    /** Fixture for DeltaCiv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new DeltaFactory());
        ((GameImpl)game).createNewCity(8, 12, Player.RED);
        ((GameImpl)game).createNewCity(4, 5, Player.BLUE);
    }

    @Test
    public void TestTile_4_8() {
        assertThat(game, is(notNullValue()));
        //Check tile at (4,8)
        Position t1 = new Position(4,8);
        assertThat(game.getTileAt(t1).getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void TestTile_1_0() {
        assertThat(game, is(notNullValue()));
        //Check tile at (1,0)
        Position t1 = new Position(1,0);
        assertThat(game.getTileAt(t1).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void TestTile_2_2() {
        assertThat(game, is(notNullValue()));
        //Check tile at (0,1)
        Position t1 = new Position(2,2);
            assertThat(game.getTileAt(t1).getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void TestTile_0_5() {
        assertThat(game, is(notNullValue()));
        //Check tile at (0,5)
        Position t1 = new Position(0,5);
        assertThat(game.getTileAt(t1).getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void TestTile_12_8() {
        assertThat(game, is(notNullValue()));
        //Check tile at (12,8)
        Position t1 = new Position(12,8);
        assertThat(game.getTileAt(t1).getTypeString(), is(GameConstants.FOREST));
    }

    @Test
    public void TestRedCity() {
        assertThat(game, is(notNullValue()));
        //Check city at (8,12)
        Position t1 = new Position(8,12);
        assertThat(game.getCityAt(t1).getOwner(), is(Player.RED));
    }

    @Test
    public void TestBlueCity() {
        assertThat(game, is(notNullValue()));
        //Check city at (4,5)
        Position t1 = new Position(4,5);
        assertThat(game.getCityAt(t1).getOwner(), is(Player.BLUE));
    }
}
