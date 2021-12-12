package hotciv.tools;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool {
    Game game;
    DrawingEditor editor;

    public SetFocusTool(DrawingEditor editor, Game game){
        this.game=game;
        this.editor=editor;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        //Getting offset coordinates for click
        game.setTileFocus(GfxConstants.getPositionFromXY(x,y));
        //editor.showStatus(String.valueOf(posY)+" - "+String.valueOf(posX));
    }
}
