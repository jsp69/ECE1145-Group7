package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

//Game board starts at 20,16
//Game board ends at 500,492
public class UnitMoveTool extends NullTool {
    private Game game;
    private DrawingEditor editor;
    private Position from;

    public UnitMoveTool(DrawingEditor e, Game g){
        this.editor = e;
        this.game = g;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y){
        //Getting offset coordinates for click
        int newX = x-20;
        int newY = y-16;

        //Getting grid position from 0-15
        int posX = (int) Math.floor(newX/30);
        int posY = (int) Math.floor(newY/30);

        this.from = GfxConstants.getPositionFromXY(x,y);
        editor.showStatus(String.valueOf(from.getRow()));
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        //Getting offset coordinates for click
        int newX = x-20;
        int newY = y-16;

        //Getting grid position from 0-15
        int posX = (int) Math.floor(newX/30);
        int posY = (int) Math.floor(newY/30);

        if(!(from.getRow()==posY && from.getColumn()==posX)) {
            game.moveUnit(this.from, GfxConstants.getPositionFromXY(x,y));
        }
    }
}
