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

/*
 * Created on Jun 11, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.berlios.lummerland.board.hex;

import java.util.Collection;

import de.berlios.lummerland.board.map.MapLocation;
import de.berlios.lummerland.board.tile.ITile;

/**
 * @author Gregor
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class HexTile implements ITile {

    private MapLocation location;

    private Collection connections;

    private Collection stations;

    /**
     * @param location
     */
    public HexTile(MapLocation location, Collection stations,
            Collection connections) {
        this.location = location;
        this.stations = stations;
        this.connections = connections;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.board.tile.ITile#getStations()
     */
    public Collection getStations() {
        return stations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.board.tile.ITile#getLocation()
     */
    public MapLocation getLocation() {
        return location;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.board.tile.ITile#getConnections()
     */
    public Collection getConnections() {
        return connections;
    }

}