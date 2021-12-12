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

        this.p = GfxConstants.getPositionFromXY(x,y);

        //Perform designated unit action at position if shift is down
        if(e.isShiftDown()) {
            game.performUnitActionAt(new Position(this.p));
        }
       editor.showStatus("unit defensive strength is " +game.getUnitAt(new Position(this.p)).getDefensiveStrength());
    }
}
