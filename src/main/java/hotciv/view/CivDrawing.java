package hotciv.view;

import hotciv.framework.*;
import minidraw.framework.*;
import minidraw.standard.ImageFigure;
import minidraw.standard.StandardDrawing;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** CivDrawing is a specialized Drawing (model component) from
 * MiniDraw that dynamically builds the list of Figures for MiniDraw
 * to render the Unit and other information objects that are visible
 * in the Game instance.
 *
 * TODO: This is a TEMPLATE for the SWEA Exercise! This means
 * that it is INCOMPLETE and that there are several options
 * for CLEANING UP THE CODE when you add features to it!

 This source code is from the book
 "Flexible, Reliable Software:
 Using Patterns and Agile Development"
 published 2010 by CRC Press.
 Author:
 Henrik B Christensen
 Department of Computer Science
 Aarhus University

 Please visit http://www.baerbak.com/ for further information.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

public class CivDrawing
        implements Drawing, GameObserver {

  protected Drawing delegate;
  /** store all moveable figures visible in this drawing = units */
  protected Map<Unit,UnitFigure> unitFigureMap;
  /** store all unmoveable figures visible in this drawing = cities */
  protected Map<City,CityFigure> cityFigureMap;

  /** the Game instance that this CivDrawing is going to render units
   * from */
  protected Game game;

  public CivDrawing( DrawingEditor editor, Game game ) {
    super();
    this.delegate = new StandardDrawing();
    this.game = game;
    this.unitFigureMap = new HashMap<>();
    this.cityFigureMap = new HashMap<>();

    // register this unit drawing as listener to any game state
    // changes...
    game.addObserver(this);
    // ... and build up the set of figures associated with
    // units in the game.
    defineUnitMap();
    // and the set of 'icons' in the status panel
    defineIcons();
    // and the cities in the game.
    defineCityMap();
  }

  /** The CivDrawing should not allow client side
   * units to add and manipulate figures; only figures
   * that renders game objects are relevant, and these
   * should be handled by observer events from the game
   * instance. Thus this method is 'killed'.
   */
  public Figure add(Figure arg0) {
    throw new RuntimeException("Should not be used...");
  }


  /** erase the old list of units, and build a completely new
   * one from scratch by iterating over the game world and add
   * Figure instances for each unit in the world.
   */
  protected void defineUnitMap() {
    // ensure no units of the old list are accidental in
    // the selection!
    clearSelection();

    // remove all unit figures in this drawing
    removeAllUnitFigures();

    // iterate world, and create a unit figure for
    // each unit in the game world, as well as
    // create an association between the unit and
    // the unitFigure in 'unitFigureMap'.
    Position p;
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        p = new Position(r,c);
        Unit unit = game.getUnitAt(p);
        if ( unit != null ) {
          String type = unit.getTypeString();
          // convert the unit's Position to (x,y) coordinates
          Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                  GfxConstants.getYFromRow(p.getRow()) );
          UnitFigure unitFigure =
                  new UnitFigure( type, point, unit );
          unitFigure.addFigureChangeListener(this);
          unitFigureMap.put(unit, unitFigure);

          // also insert in delegate list as it is
          // this list that is iterated by the
          // graphics rendering algorithms
          delegate.add(unitFigure);
        }
      }
    }
  }

  protected void defineCityMap() {
    // ensure no cities of the old list are accidental in
    // the selection!
    clearSelection();

    // remove all unit figures in this drawing
    removeAllCityFigures();

    // iterate world, and create a city figure for
    // each city in the game world, as well as
    // create an association between the city and
    // the cityFigure in 'cityFigureMap'.
    Position p;
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        p = new Position(r,c);
        City city = game.getCityAt(p);
        if ( city != null ) {
          // convert the city's Position to (x,y) coordinates
          Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                  GfxConstants.getYFromRow(p.getRow()) );
          CityFigure cityFigure =
                  new CityFigure( city, point );
          cityFigure.addFigureChangeListener(this);
          cityFigureMap.put(city, cityFigure);

          // also insert in delegate list as it is
          // this list that is iterated by the
          // graphics rendering algorithms
          delegate.add(cityFigure);
        }
      }
    }
  }

  /** remove all unit figures in this
   * drawing, and reset the map (unit->unitfigure).
   * It is important to actually remove the figures
   * as it forces a graphical redraw of the screen
   * where the unit figure was.
   */
  protected void removeAllUnitFigures() {
    for (Unit u : unitFigureMap.keySet()) {
      UnitFigure uf = unitFigureMap.get(u);
      delegate.remove(uf);
    }
    unitFigureMap.clear();
  }

  protected void removeAllCityFigures() {
    for (City c : cityFigureMap.keySet()) {
      CityFigure cf = cityFigureMap.get(c);
      delegate.remove(cf);
    }
    cityFigureMap.clear();
  }

  // Icons that appear and change with tile focus
  protected ImageFigure turnShieldIcon, cityShield, unitShield;
  protected TextFigure ageText, unitMoves, unitType;
  protected ImageFigure cityProduce, cityBalance;

  protected void defineIcons() {
    turnShieldIcon =
            new ImageFigure( GfxConstants.RED_SHIELD,
                    new Point( GfxConstants.TURN_SHIELD_X,
                            GfxConstants.TURN_SHIELD_Y ) );

    ageText = new TextFigure("4000 BC", new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y) );
    cityShield=new ImageFigure(GfxConstants.NOTHING,new Point(GfxConstants.CITY_SHIELD_X,GfxConstants.CITY_SHIELD_Y));
    unitShield=new ImageFigure(GfxConstants.NOTHING,new Point(GfxConstants.UNIT_SHIELD_X,GfxConstants.UNIT_SHIELD_Y));
    unitMoves=new TextFigure("0",new Point(GfxConstants.UNIT_COUNT_X,GfxConstants.UNIT_COUNT_Y));
    cityProduce=new ImageFigure(GfxConstants.NOTHING,new Point(GfxConstants.CITY_PRODUCTION_X,GfxConstants.CITY_PRODUCTION_Y));
    cityBalance=new ImageFigure(GfxConstants.NOTHING,new Point(GfxConstants.WORKFORCEFOCUS_X,GfxConstants.WORKFORCEFOCUS_Y));
    unitType=new TextFigure("N/A",new Point(GfxConstants.UNIT_SHIELD_X-27,GfxConstants.UNIT_SHIELD_Y-42));

    // insert in delegate figure list to ensure graphical
    // rendering.
    delegate.add(turnShieldIcon);
    delegate.add(ageText);
    delegate.add(cityShield);
    delegate.add(unitShield);
    delegate.add(unitMoves);
    delegate.add(cityProduce);
    delegate.add(cityBalance);
    delegate.add(unitType);
  }

  // === Observer Methods ===

  public void worldChangedAt(Position pos) {
    clearSelection();
    // this is a really brute-force algorithm: destroy
    // all known units and build up the entire set again
    for ( Figure f : unitFigureMap.values()) {
      remove(f);
    }
    defineUnitMap();

    for ( Figure f : cityFigureMap.values()) {
      remove(f);
    }
    defineCityMap();

  }

  public void turnEnds(Player nextPlayer, int age) {
    String playerName = "red";
    if ( nextPlayer == Player.BLUE ) { playerName = "blue"; }
    turnShieldIcon.set( playerName + "shield", turnShieldIcon.displayBox().getLocation());

    // Check if age is BC or AD
    String BC_or_AD;
    if (game.getAge() >= 0) { BC_or_AD = " AD"; }
    else { BC_or_AD = " BC"; }
    ageText.setText(Math.abs(game.getAge()) + BC_or_AD);
  }

  public void tileFocusChangedAt(Position position) {
    Point cityS = new Point(GfxConstants.CITY_SHIELD_X,GfxConstants.CITY_SHIELD_Y);
    Point unitS = new Point(GfxConstants.UNIT_SHIELD_X,GfxConstants.UNIT_SHIELD_Y);
    Point cityP = new Point(GfxConstants.CITY_PRODUCTION_X,GfxConstants.CITY_PRODUCTION_Y);
    Point cityB = new Point(GfxConstants.WORKFORCEFOCUS_X,GfxConstants.WORKFORCEFOCUS_Y);

    //Change Unit stuff
    if(game.getUnitAt(position)==null){
      unitShield.set(GfxConstants.NOTHING,unitS);
      unitMoves.setText("0");
    }else{
      if(game.getUnitAt(position).getOwner()==Player.RED){
        unitShield.set(GfxConstants.RED_SHIELD,unitS);
      }else{
        unitShield.set(GfxConstants.BLUE_SHIELD,unitS);
      }
      unitMoves.setText(String.valueOf(game.getUnitAt(position).getMoveCount()));
      unitType.setText(game.getUnitAt(position).getTypeString());
    }

    //Change City stuff
    if(game.getCityAt(position)==null) {
      cityShield.set(GfxConstants.NOTHING, cityS);
      cityProduce.set(GfxConstants.NOTHING,cityP);
      cityBalance.set(GfxConstants.NOTHING,cityB);
    }else{
      if (game.getCityAt(position).getOwner() == Player.RED) {
        cityShield.set(GfxConstants.RED_SHIELD, cityS);
      } else{
        cityShield.set(GfxConstants.BLUE_SHIELD, cityS);
      }

      cityBalance.set(game.getCityAt(position).getWorkforceFocus(),cityB);

      if (game.getCityAt(position).getProduction() == null){
        cityProduce.set(GfxConstants.NOTHING,cityP);
      } else{
        cityProduce.set(game.getCityAt(position).getProduction(),cityP);
      }
    }
  }

  @Override
  public void requestUpdate() {
    // A request has been issued to repaint
    // everything. We simply rebuild the
    // entire Drawing.
    defineUnitMap();
    defineIcons();
    defineCityMap();
  }

  @Override
  public void addToSelection(Figure arg0) {
    delegate.addToSelection(arg0);
  }

  @Override
  public void clearSelection() {
    delegate.clearSelection();
  }

  @Override
  public void removeFromSelection(Figure arg0) {
    delegate.removeFromSelection(arg0);
  }

  @Override
  public List<Figure> selection() {
    return delegate.selection();
  }

  @Override
  public void toggleSelection(Figure arg0) {
    delegate.toggleSelection(arg0);
  }

  @Override
  public void figureChanged(FigureChangeEvent arg0) {
    delegate.figureChanged(arg0);
  }

  @Override
  public void figureInvalidated(FigureChangeEvent arg0) {
    delegate.figureInvalidated(arg0);
  }

  @Override
  public void figureRemoved(FigureChangeEvent arg0) {
    delegate.figureRemoved(arg0);
  }

  @Override
  public void figureRequestRemove(FigureChangeEvent arg0) {
    delegate.figureRequestRemove(arg0);
  }

  @Override
  public void figureRequestUpdate(FigureChangeEvent arg0) {
    delegate.figureRequestUpdate(arg0);
  }

  @Override
  public void addDrawingChangeListener(DrawingChangeListener arg0) {
    delegate.addDrawingChangeListener(arg0);
  }

  @Override
  public void removeDrawingChangeListener(DrawingChangeListener arg0) {
    delegate.removeDrawingChangeListener(arg0);
  }

  @Override
  public Figure findFigure(int arg0, int arg1) {
    return delegate.findFigure(arg0, arg1);
  }

  @Override
  public Iterator<Figure> iterator() {
    return delegate.iterator();
  }

  @Override
  public void lock() {
    delegate.lock();
  }

  @Override
  public Figure remove(Figure arg0) {
    return delegate.remove(arg0);
  }

  @Override
  public void unlock() {
    delegate.unlock();
  }
}
