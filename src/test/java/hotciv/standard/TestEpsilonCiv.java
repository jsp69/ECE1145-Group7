package hotciv.standard;

import hotciv.framework.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestEpsilonCiv {
    private Game game;

    //Test battle in which defender has 6x advantage from dice roll
    //Calculated Strengths A=(4+1+1)*1*1=6 < D=(2)*1*6=12
    @Test
    public void DefenseShouldWin(){
        //Game is set so attacker rolls 1 and defender rolls 6
        game=new GameImpl(new EpsilonFactory(new FixedDice(1),new FixedDice(6)));
        Position attack=new Position(3,3);
        Position defense=new Position(3,2);
        assertNotNull(game);
        //Validate units before testing
        assertThat(game.getUnitAt(attack).getOwner(),is(Player.RED));
        assertThat(game.getUnitAt(attack).getTypeString(),is(GameConstants.LEGION));
        assertThat(game.getUnitAt(defense).getOwner(),is(Player.BLUE));
        assertThat(game.getUnitAt(defense).getTypeString(),is(GameConstants.LEGION));
        //Check if attack occurred
        assertTrue(game.moveUnit(attack,defense));
        //Check if defending unit and city remains
        assertThat(game.getUnitAt(defense).getOwner(),is(Player.BLUE));
        assertThat(game.getCityAt(defense).getOwner(),is(Player.BLUE));
    }

    //Test where attacker has 6x advantage from dice roll
    //Calculated Strengths A=(4+1+1)*1*6=36 > D=(2)*1*1=2
    @Test
    public void AttackShouldWin(){
        //Game is set so attacker rolls 1 and defender rolls 6
        game=new GameImpl(new EpsilonFactory(new FixedDice(6),new FixedDice(1)));
        Position attack=new Position(3,3);
        Position defense=new Position(3,2);
        assertNotNull(game);
        //Validate units before testing
        assertThat(game.getUnitAt(attack).getOwner(),is(Player.RED));
        assertThat(game.getUnitAt(attack).getTypeString(),is(GameConstants.LEGION));
        assertThat(game.getUnitAt(defense).getOwner(),is(Player.BLUE));
        assertThat(game.getUnitAt(defense).getTypeString(),is(GameConstants.LEGION));
        //Check if attack occurred
        assertTrue(game.moveUnit(attack,defense));
        //Check if defending unit and city remains
        assertThat(game.getUnitAt(defense).getOwner(),is(Player.RED));
        assertThat(game.getCityAt(defense).getOwner(),is(Player.RED));
    }

    //Red should win after 3 successful attacks
    @Test
    public void RedWinAfterAttacks(){
        //Attacker always rolls 6 to ensure successful attacks
        game=new GameImpl(new EpsilonFactory(new FixedDice(6),new FixedDice(1)));
        Position attack=new Position(3,3);
        Position defense1=new Position(3,2);
        Position defense2=new Position(4,2);
        Position defense3=new Position(5,2);

        assertNotNull(game);
        assertNull(game.getWinner());

        //Validate units before testing
        assertThat(game.getUnitAt(attack).getOwner(),is(Player.RED));
        assertThat(game.getUnitAt(attack).getTypeString(),is(GameConstants.LEGION));
        assertThat(game.getUnitAt(defense1).getOwner(),is(Player.BLUE));
        assertThat(game.getUnitAt(defense1).getTypeString(),is(GameConstants.LEGION));
        assertThat(game.getUnitAt(defense2).getOwner(),is(Player.BLUE));
        assertThat(game.getUnitAt(defense2).getTypeString(),is(GameConstants.LEGION));
        assertThat(game.getUnitAt(defense3).getOwner(),is(Player.BLUE));
        assertThat(game.getUnitAt(defense3).getTypeString(),is(GameConstants.LEGION));

        assertTrue(game.moveUnit(attack,defense1));
        assertTrue(game.moveUnit(defense1,defense2));
        assertTrue(game.moveUnit(defense2,defense3));

        assertThat(game.getWinner(),is(Player.RED));
    }
}
