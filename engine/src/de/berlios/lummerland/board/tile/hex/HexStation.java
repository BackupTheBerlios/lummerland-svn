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
package de.berlios.lummerland.board.tile.hex;

import de.berlios.lummerland.board.tile.IStation;

/**
 * @author Gregor
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class HexStation implements IStation {

    private int value;

    private int tokenCapacity;

    private HexStationLocation location;

    /**
     * @param i
     */
    public HexStation(int value, int tokenCapacity, HexStationLocation location) {

        this.value = value;
        this.tokenCapacity = tokenCapacity;
        this.location = location;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.board.tile.IStation#getValue()
     */
    public int getValue() {
        return value;
    }

    /**
     * @return Returns the location.
     */
    public HexStationLocation getLocation() {
        return location;
    }

    /**
     * @return Returns the tokenCapacity.
     */
    public int getTokenCapacity() {
        return tokenCapacity;
    }
}