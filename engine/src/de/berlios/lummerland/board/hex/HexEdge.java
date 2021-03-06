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

import de.berlios.lummerland.board.tile.IEdge;

/**
 * @author Gregor
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class HexEdge implements IEdge {
    private String name;

    /**
     * @param string
     */
    public HexEdge(String name) {

        this.name = name;
    }

    public final static HexEdge NORTH = new HexEdge("north");

    public final static HexEdge NORTHEAST = new HexEdge("northeast");

    public final static HexEdge SOUTHEAST = new HexEdge("southeast");

    public final static HexEdge SOUTH = new HexEdge("south");

    public final static HexEdge SOUTHWEST = new HexEdge("southwest");

    public final static HexEdge NORTHWEST = new HexEdge("northwest");

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
}