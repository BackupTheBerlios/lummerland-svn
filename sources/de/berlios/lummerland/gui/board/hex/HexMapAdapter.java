/**************************************************************************/
/*  Copyright 2004 Gregor Zeitlinger, Bjoern Rabenstein                   */
/*                                                                        */
/*  This file is part of Lummerland.                                      */
/*                                                                        */
/*  Lummerland is free software; you can redistribute it and/or modify    */
/*  it under the terms of the GNU General Public License as published by  */
/*  the Free Software Foundation; either version 2 of the License, or     */
/*  (at your option) any later version.                                   */
/*                                                                        */
/*  Lummerland is distributed in the hope that it will be useful,         */
/*  but WITHOUT ANY WARRANTY; without even the implied warranty of        */
/*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         */
/*  GNU General Public License for more details.                          */
/*                                                                        */
/*  You should have received a copy of the GNU General Public License     */
/*  along with Lummerland; if not, write to the Free Software             */
/*  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA  */
/**************************************************************************/

package de.berlios.lummerland.gui.board.hex;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.util.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import de.berlios.lummerland.board.hex.HexEdge;
import de.berlios.lummerland.board.hex.HexTile;
import de.berlios.lummerland.board.map.Board;
import de.berlios.lummerland.board.map.MapLocation;
import de.berlios.lummerland.board.tile.Connection;
import de.berlios.lummerland.board.tile.hex.HexStation;
import de.berlios.lummerland.board.tile.hex.HexStationLocation;
import de.berlios.lummerland.graphics.Color;
import de.berlios.lummerland.gui.board.IBoardAdapter;

/**
 * @author Zuther
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class HexMapAdapter extends Canvas implements PaintListener,
        IBoardAdapter {
    private static final double COS60 = Math.cos(Math.PI / 3);

    private static final double SIN60 = Math.sin(Math.PI / 3);

    private Collection tiles;

    public HexMapAdapter(Composite parent, Board board) {
        super(parent, SWT.NONE);
        tiles = board.getTiles();
        addPaintListener(this);
    }

    public Point computeSize(int wHint, int hHint, boolean changed) {
        return new Point(1000, 500);
    }

    public void paintControl(PaintEvent e) {
        
        for (Iterator iter = tiles.iterator(); iter.hasNext();) {
            HexTile tile = (HexTile) iter.next();
            MapLocation location = tile.getLocation();
            drawHexBorder(e.gc, location);
            drawConnections(e.gc, tile.getConnections(), location);
            drawStations(e.gc, tile.getStations(), location);
        }
    }

    //        Color old = e.gc.getBackground();
    //		Color c = amoeba.getPlayer().getColor().getSwtColor(getDisplay());
    //e.gc.setLineWidth(4);
    //e.gc.drawRectangle(0, 0, 10, 10);
    //		e.gc.fillOval(e.x, e.y, e.width, e.height);
    //		
    //		e.gc.drawText(
    //				"1",
    //				e.x + e.width / 4,
    //				e.y);
    //		e.gc.fillRectangle(0, 0, 100, 100);
    //		e.gc.fillOval(e.x, e.y, e.width, e.height);
    //e.gc.fillOval(0, 0, 10, 10);
    //        e.gc.setBackground(old);
    /**
     * @param gc
     * @param collection
     */
    private void drawStations(GC gc, Collection stations, MapLocation location) {

        double orX = getOriginX(location);
        double orY = getOriginY(location);

        double size = getEdgeLength() / 1.5;
        
        gc.setBackground(Color.Blue.getSwtColor(getDisplay ()));

        for (Iterator iter = stations.iterator(); iter.hasNext();) {
            HexStation station = (HexStation) iter.next();

            double centerX = orX + getStationX(station);
            double centerY = orY + getStationY(station);

            gc.fillOval((int) (centerX - size/2), (int) (centerY - size/2),
                    (int) (size), (int) (size));
        }

        //		for (Iterator iter = stations.iterator(); iter.hasNext();) {
        //			IStation station = (IStation) iter.next();
        //		}
    }

    /**
     * @param gc
     * @param location
     */
    private void drawConnections(GC gc, Collection connections,
            MapLocation location) {
        for (Iterator iter = connections.iterator(); iter.hasNext();) {
            Connection conn = (Connection) iter.next();
            Object src = conn.getSource();
            Object dest = conn.getDestination();
            double orX = getOriginX(location);
            double orY = getOriginY(location);

            double srcX = getX(src);
            double srcY = getY(src);
            double destX = getX(dest);
            double destY = getY(dest);
            gc.drawLine((int) (orX + srcX), (int) (orY + srcY),
                    (int) (orX + destX), (int) (orY + destY));

        }
    }

    /**
     * @param src
     * @return
     */
    private double getY(Object o) {
        if (o instanceof HexEdge) {
            HexEdge edge = (HexEdge) o;
            return getEdgeY(edge);
        } else if (o instanceof HexStation) {
            HexStation station = (HexStation) o;
            return getStationY(station);
        }
        Assert.isTrue(false);
        return 0;
    }

    private double getX(Object o) {
        if (o instanceof HexEdge) {
            HexEdge edge = (HexEdge) o;
            return getEdgeX(edge);
        } else if (o instanceof HexStation) {
            HexStation station = (HexStation) o;
            return getStationX(station);
        }
        Assert.isTrue(false);
        return 0;
    }

    /**
     * @param station
     * @return
     */
    private double getStationX(HexStation station) {
        HexStationLocation location = station.getLocation();
        if (location == HexStationLocation.EAST) {
            return getCos();
        } else if (location == HexStationLocation.CENTER) {
            return getCos() + (getEdgeLength() / 2);
        } else if (location == HexStationLocation.WEST) {
            return getCos() + getEdgeLength();
        } else {
            Assert.isTrue(false);
            return 0;
        }
    }

    /**
     * @param station
     * @return
     */
    private double getStationY(HexStation station) {
        return getSin();
    }

    /**
     * @param src
     * @return
     */
    private int getEdgeY(HexEdge src) {
        double val = 0;
        if (src == HexEdge.NORTH) {
            val = 0;
        } else if (src == HexEdge.NORTHEAST || src == HexEdge.NORTHWEST) {
            val = SIN60 * getEdgeLength() / 2;
        } else if (src == HexEdge.SOUTHEAST || src == HexEdge.SOUTHWEST) {
            val = (SIN60 * getEdgeLength() / 2) + getSin();
        } else if (src == HexEdge.SOUTH) {
            val = getTileHight();
        }
        return (int) val;
    }

    /**
     * @param src
     * @return
     */
    private int getEdgeX(HexEdge src) {
        double val = 0;
        if (src == HexEdge.NORTHEAST || src == HexEdge.SOUTHEAST) {
            val = COS60 * getEdgeLength() / 2;
        } else if (src == HexEdge.NORTH || src == HexEdge.SOUTH) {
            val = getEdgeLength();
        } else if (src == HexEdge.NORTHWEST || src == HexEdge.SOUTHWEST) {
            val = getTileWidth() - (COS60 * getEdgeLength() / 2);
        }
        return (int) val;
    }

    /**
     * @param gc
     * @param location
     */
    private void drawHexBorder(GC gc, MapLocation location) {
        gc.setBackground(Color.Red.getSwtColor(getDisplay()));

        double cos = getCos();
        double tileW = getTileWidth();
        double sin = getSin();
        double tileH = getTileHight();
        double edge = getEdgeLength();
        double orX = getOriginX(location);
        double orY = getOriginY(location);
        gc.fillPolygon(new int[] { (int) (orX + cos), (int) (orY),
                (int) (orX + cos + edge), (int) (orY), (int) (orX + tileW),
                (int) (orY + sin), (int) (orX + cos + edge),
                (int) (orY + tileH), (int) (orX + cos), (int) (orY + tileH),
                (int) (orX), (int) (orY + sin) });
    }

    private double getOriginY(MapLocation location) {
        return location.y * getSin();
    }

    private double getOriginX(MapLocation location) {
        return location.x * (getCos() + getEdgeLength());
    }

    private double getTileHight() {
        return 2 * getSin();
    }

    private double getTileWidth() {
        return 2 * getCos() + getEdgeLength();
    }

    private double getSin() {
        return SIN60 * getEdgeLength();
    }

    private double getCos() {
        return COS60 * getEdgeLength();
    }

    private int getEdgeLength() {
        return 50;
    }
}