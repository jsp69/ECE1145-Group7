package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestBetaCiv {
    private Game game;

    @Before
    public void setUp(){
        game= new VarCivImpl(new BetaCivImpl());
    }

    //Test that RED will only win if they own all cities on map
    @Test
    public void betaWinOnAllCitiesConquered(){
        Position rC=new Position(1,1);
        Position bC=new Position(4,1);
        assertThat(game,is(notNullValue()));
        assertThat(game.getCityAt(rC).getOwner(),is(Player.RED));
        assertThat(game.getCityAt(bC).getOwner(),is(Player.BLUE));
        assertNull(game.getWinner());
        ((CityImpl)(game.getCityAt(bC))).setOwner(Player.RED);
        assertThat(game.getCityAt(bC).getOwner(),is(Player.RED));
        assertThat(game.getWinner(),is(Player.RED));
    }
}