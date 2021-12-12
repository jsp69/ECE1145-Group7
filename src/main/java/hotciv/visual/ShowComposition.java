package hotciv.visual;

import hotciv.standard.EtaFactory;
import hotciv.standard.GameImpl;
import hotciv.standard.SemiFactory;
import hotciv.tools.CompositionTool;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;


/** Template code for exercise FRS 36.44.

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
public class ShowComposition {
  
  public static void main(String[] args) {
    Game game = new GameImpl(new SemiFactory());

    DrawingEditor editor = 
      new MiniDrawApplication( "Click and/or drag any item to see all game actions",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click and drag any item to see Game's proper response.");

    editor.setTool( new CompositionTool(editor, game) );
  }

}
