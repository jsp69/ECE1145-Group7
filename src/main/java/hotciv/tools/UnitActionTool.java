package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class UnitActionTool extends NullTool {
    private DrawingEditor editor;
    private Game game;
    private Position p;

    public UnitActionTool(DrawingEditor e, Game g) {
        this.editor = e;
        this.game = g;
    }

    //unit shield (594,188)
    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        //Getting offset coordinates for click
        int newX = x - 20;
        int newY = y - 16;

        //Getting grid position from 0-15
        int posX = (int) Math.floor(newX/30);
        int posY = (int) Math.floor(newY/30);

        this.p = GfxConstants.getPositionFromXY(x,y);

        //Perform designated unit action at position if shift is down
        if(e.isShiftDown()) {
            game.setTileFocus(p);
            game.performUnitActionAt(p);
            //System.out.print("shift is held down");
        }
        editor.showStatus(posY +" - "+ posX);
    }
}
