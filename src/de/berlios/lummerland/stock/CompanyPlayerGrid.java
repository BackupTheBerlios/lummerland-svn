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
 * Created on Jun 19, 2004
 *
 */
package de.berlios.lummerland.stock;

import java.util.HashMap;
import java.util.Iterator;

import de.berlios.lummerland.Engine;
import de.berlios.lummerland.company.ICompany;
import de.berlios.lummerland.player.Player;

/**
 * @author Gregor
 *  
 */
public class CompanyPlayerGrid {

    private HashMap players = new HashMap();

    /**
     * @param game
     */
    public CompanyPlayerGrid(Engine game) {
        for (Iterator iter = game.getPlayers().iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            players.put(player, new HashMap());
        }
    }

    public boolean contains(Player p, ICompany c) {
        HashMap map = (HashMap) players.get(p);

        return map.containsKey(c);
    }

    public void add(Player p, ICompany c) {
        HashMap map = (HashMap) players.get(p);

        map.put(c, new Object());

    }

    public void remove(Player p, ICompany c) {
        HashMap map = (HashMap) players.get(p);

        map.remove(c);

    }

}