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

    //Test Variant World Aging from 4000BC to 1973AD
    @Test
    public void betaAgingAlgorithm(){
        assertThat(game,is(notNullValue()));
        assertThat(game.getAge(),is(-4000));
        assertThat(game.getAge(),is(-3900));
        for(int i=0;i<77;i++){
            if(i%2==0){
                assertThat(game.getAge(),is((int)(-3900+(100*.5*i))));
            }else{
                assertThat(game.getAge(),is((int)(-3900+(100*Math.floor(.5*i+1)))));
            }
        }
        assertThat(game.getAge(),is(-1));
        assertThat(game.getAge(),is(-1));
        assertThat(game.getAge(),is(1));
        assertThat(game.getAge(),is(1));
        assertThat(game.getAge(),is(50));
        for(int i=0;i<67;i++) {
            if (i % 2 == 0) {
                assertThat(game.getAge(), is((int) (50 + 50 * .5 * i)));
            } else {
                assertThat(game.getAge(), is((int) (50 + 50 * Math.floor(.5 * i + 1))));
            }
        }
        assertThat(game.getAge(),is(1750));
        for(int i=0;i<11;i++){
            if (i % 2 == 0) {
                assertThat(game.getAge(), is((int) (1750 + 25 * .5 * i)));
            } else {
                assertThat(game.getAge(), is((int) (1750 + 25 * Math.floor(.5 * i + 1))));
            }
        }
        assertThat(game.getAge(),is(1900));
        for(int i=0;i<27;i++){
            if (i % 2 == 0) {
                assertThat(game.getAge(), is((int) (1900 + 5 * .5 * i)));
            } else {
                assertThat(game.getAge(), is((int) (1900 + 5 * Math.floor(.5 * i + 1))));
            }
        }
        assertThat(game.getAge(),is(1970));
        assertThat(game.getAge(),is(1970));
        assertThat(game.getAge(),is(1971));
        assertThat(game.getAge(),is(1971));
        assertThat(game.getAge(),is(1972));
        assertThat(game.getAge(),is(1972));
        assertThat(game.getAge(),is(1973));
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
