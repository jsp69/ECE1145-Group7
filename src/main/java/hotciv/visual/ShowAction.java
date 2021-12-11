package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Rectangle;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.43.

 This source code is from the book
 "Flexible, Reliable Software:
 Using Patterns and Agile Development"
 published 2010 by CRC Press.
 Author:
 Henrik B Christensen
 Computer Science Department
 Aarhus University

 This source code is provided WITHOUT ANY WARRANTY either
 expressed or implied. You may study, use, modify, and
 distribute it for non-commercial purposes. For any
 commercial use, see http://www.baerbak.com/
 */
public class ShowAction {

    public static void main(String[] args) {
        Game game = new StubGame2();

        DrawingEditor editor =
                new MiniDrawApplication( "Shift-Click unit to invoke its action",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Shift-Click on unit to see Game's performAction method being called.");

        // TODO: Replace the setting of the tool with your ActionTool implementation.
        editor.setTool( new ActionTool(editor, game) );
    }
}

class ActionTool extends NullTool {
    private DrawingEditor editor;
    private Game game;
    private Position p;

    public ActionTool(DrawingEditor e, Game g) {
        this.editor = e;
        this.game = g;
    }

    //unit shield (594,188)
    @Override
    public void mouseDown(MouseEvent e, int x, int y) {

        //Getting offset coordinates for click
        int newX = x-20;
        int newY = y-16;

        //Getting grid position from 0-15
        int posX = (int) Math.floor(newX/30);
        int posY = (int) Math.floor(newY/30);

        this.p = GfxConstants.getPositionFromXY(posX,posY);

        //Perform designated unit action at position if shift is down
        if(e.isShiftDown()) {
            game.setTileFocus(new Position(posY,posX));
            game.performUnitActionAt(new Position(posY,posX));
            //System.out.print("shift is held down");
        }
        editor.showStatus(String.valueOf(posY)+" - "+String.valueOf(posX));
    }
}
