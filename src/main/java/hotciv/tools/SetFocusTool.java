package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool {
    Game game;
    DrawingEditor editor;

    public SetFocusTool(DrawingEditor editor, Game game){
        this.game = game;
        this.editor = editor;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        Position p = new Position(GfxConstants.getPositionFromXY(x,y));
        if(p.getRow()<16 && p.getColumn()<16) {
            game.setTileFocus(p);
        }
    }
}
