package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EndOfTurnTool extends NullTool {
    private Game game;
    private DrawingEditor editor;
    private Position p;

    public EndOfTurnTool(DrawingEditor e, Game g) {
        game = g;
        editor = e;
    }

    //tile size: 30x30
    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        //Top shield in age section from gfx constants
        Rectangle turnShield = new Rectangle(GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y, 30, 30);

        //Check whether the mouse click coordinates are within the rectangle
        if (turnShield.contains(x, y)) {
            game.endOfTurn();
        }
    }
}
