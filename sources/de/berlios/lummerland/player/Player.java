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

package de.berlios.lummerland.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import de.berlios.lummerland.Game;
import de.berlios.lummerland.graphics.Color;
import de.berlios.lummerland.gui.ISpecificStockListener;
import de.berlios.lummerland.stock.ICertificateHolder;
import de.berlios.lummerland.stock.IStockTradeListener;
import de.berlios.lummerland.stock.IStockTradingController;

/**
 * @author Joerg Zuther
 */
public class Player implements ICertificateHolder {
    private Game game;

    private Color color;

    private String name;

    private int money;

    private Collection moneyListeners = new ArrayList();

    private Collection certificates = new ArrayList();

    private Collection specificStockListeners = new ArrayList();

    private Collection stockTradeListeners = new ArrayList ();

    /**
     * @return Returns the certificates.
     */
    public Collection getCertificates() {
        return certificates;
    }

    /**
     * Constructor for Player.
     * 
     * @param Game
     *            game
     * @param Color
     *            color
     */
    public Player(Game game, Color color) {
        this.game = game;
        this.color = color;
        game.addPlayer(this);
    }

    /**
     * Returns the score.
     * 
     * @return int
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the score.
     * 
     * @param score
     *            The score to set
     */
    public void setMoney(int money) {
        this.money = money;
        notifyMoneyListeners();
    }

    /**
     * method notifyScoreListeners
     */
    public void notifyMoneyListeners() {
        for (Iterator iter = moneyListeners.iterator(); iter.hasNext();) {
            MoneyListener l = (MoneyListener) iter.next();
            l.updateMoney();
        }
    }

    /**
     * Returns the color.
     * 
     * @return int
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the game.
     * 
     * @return Game
     */
    public Game getGame() {
        return game;
    }

    /**
     *  
     */
    public boolean isHuman() {
        // todo returns true if player is human
        return true;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }
    public String toString () {
        return getName();
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method addScoreListener.
     * 
     * @param ScoreListener
     *            l
     */
    public void addMoneyListener(MoneyListener l) {
        moneyListeners.add(l);
    }

    /**
     * Method removeScoreListener.
     * 
     * @param ScoreListener
     *            l
     */
    public void removeMoneyListener(MoneyListener l) {
        moneyListeners.remove(l);
    }

    /**
     * @param company
     * @param adapter
     */
    public void addSpecificStockListener(ISpecificStockListener l) {
        specificStockListeners.add(l);
    }

    public void removeSpecificStockListener(ISpecificStockListener l) {
        specificStockListeners.remove(l);
    }

    public void addStockTradeListener(IStockTradeListener l) {
        stockTradeListeners.add(l);
    }

    public void removeStockTradeListener(IStockTradeListener l) {
        stockTradeListeners.remove(l);
    }

    public void activateStockTrading(IStockTradingController c) {
        for (Iterator iter = stockTradeListeners.iterator(); iter.hasNext();) {
            IStockTradeListener l = (IStockTradeListener) iter.next();
            l.activateStockTrading(c);
        }
    }
    
    public void deactivateStockTrading() {
        for (Iterator iter = stockTradeListeners.iterator(); iter.hasNext();) {
            IStockTradeListener l = (IStockTradeListener) iter.next();
            l.deactivateStockTrading();
        }
    }

    public Player getNextPlayer() {
        return game.getNextPlayer (this); 
    }

}