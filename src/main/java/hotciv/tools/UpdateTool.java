package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** A tool that simply 'does something' new every time
 * the mouse is clicked anywhere; as a visual testing
 * of the 'from Domain to GUI' data flow is coded correctly*/
public class UpdateTool extends NullTool {
    private Game game;
    private DrawingEditor editor;
    public UpdateTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }
    private int count = 0;
    public void mouseDown(MouseEvent e, int x, int y) {
        switch(count) {
            case 0: {
                editor.showStatus( "State change: Moving archer to (1,1)" );
                game.moveUnit( new Position(2,0), new Position(1,1) );
                break;
            }
            case 1: {
                editor.showStatus( "State change: Moving archer to (2,2)" );
                game.moveUnit( new Position(1,1), new Position(2,2) );
                break;
            }
            case 2: {
                editor.showStatus( "State change: End of Turn (over to blue)" );
                game.endOfTurn();
                break;
            }
            case 3: {
                editor.showStatus( "State change: End of Turn (over to red)" );
                game.endOfTurn();
                break;
            }
            case 4: {
                editor.showStatus( "State change: Inspect Unit at (4,3)" );
                game.setTileFocus(new Position(4,3));
                break;
            }
            case 5: {
                editor.showStatus( "State change: Inspect Unit at (2,0)" );
                game.performUnitActionAt(new Position(2,0));
                break;
            }
            // TODO: Add more state changes for other things to test
            default: {
                editor.showStatus("No more changes in my list...");
            }
        }
        count ++;
    }
}
