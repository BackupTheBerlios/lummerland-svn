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
package de.berlios.lummerland.gui;

import java.util.Iterator;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import de.berlios.lummerland.Game;
import de.berlios.lummerland.player.Player;
import de.berlios.lummerland.stock.IStockTradeListener;
import de.berlios.lummerland.stock.IStockTradingController;


class RootKeyAdapter extends KeyAdapter implements
        IStockTradeListener {

    private IStockTradingController controller;

    /**
     * @param game
     */
    public RootKeyAdapter(Game game) {

        for (Iterator iter = game.getPlayers().iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            player.addStockTradeListener(this);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.character == ' ') {
            controller.pass();
        }
    }

    public void activateStockTrading(IStockTradingController controller) {
        this.controller = controller;
    }

    public void deactivateStockTrading() {
        controller = null;
    }
}