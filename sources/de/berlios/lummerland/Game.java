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
/** *********************************************************************** */

package de.berlios.lummerland;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import de.berlios.lummerland.board.map.Board;
import de.berlios.lummerland.company.Company;
import de.berlios.lummerland.configuration.Config;
import de.berlios.lummerland.graphics.Color;
import de.berlios.lummerland.player.Player;
import de.berlios.lummerland.schedule.model.IControlFlowModel;
import de.berlios.lummerland.setup.RootSchedule;

public class Game {
    private int state;

    private Board board;

    private Collection companies = new ArrayList();

    private IControlFlowModel controlFlowModel = new IControlFlowModel () {

        private int nextAction = IControlFlowModel.Running;
        
        public void undoAction() {
            nextAction = IControlFlowModel.Undoing;
            resume ();
        }

        public void redoAction() {
            nextAction = IControlFlowModel.Redoing;
            resume ();
        }

        public int getNextAction() {
            return nextAction;
        }
        
    };

    private List players = new ArrayList();

    private RootSchedule rootSchedule;

    private int year;

    private Player priorityDealHolder;

    private Config config;

    private Thread thread;

    public Player getPriorityDealHolder() {
        return priorityDealHolder;
    }

    public void setPriorityDealHolder(Player priorityDealHolder) {
        this.priorityDealHolder = priorityDealHolder;
    }

    public Game() {
        thread = Thread.currentThread();
        
        config = new Config ("plugin.xml");
        
        Player redPlayer = new Player(this, Color.Red);
        redPlayer.setName("Red");
        Player bluePlayer = new Player(this, Color.Blue);
        bluePlayer.setName("Blue");
        Player greenPlayer = new Player(this, Color.Green);
        greenPlayer.setName("Green");
        Player yellowPlayer = new Player(this, Color.Yellow);
        yellowPlayer.setName("Yellow");

        new Company(this, "NYC");
        new Company(this, "NYNH");
        new Company(this, "C&O");
        new Company(this, "PRR");

        board = new Board(this);
        rootSchedule = new RootSchedule(this);
    }

    /**
     * @return Returns the companies.
     */
    public Collection getCompanies() {
        return companies;
    }

    public IControlFlowModel getControlFlowModel() {
        return controlFlowModel;
    }
    
    /**
     * Method getNumberOfPlayers.
     * 
     * @return int
     */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * Returns the board.
     * 
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Method addPlayer.
     * 
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Returns the players.
     * 
     * @return Vector
     */
    public Player getPlayer(Color c) {
        for (Iterator iter = players.iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            if (player.getColor() == c) {
                return player;
            }
        }
        return null;

    }

    /**
     * Returns the players.
     * 
     * @return List
     */
    public Collection getPlayers() {
        return players;
    }

    /**
     * Method getPlayersByScore Returns the player list ordered by score
     * (highest first)
     * 
     * @return List
     */
//    public Collection getPlayersByScore() {
//        List playersByScore = new ArrayList();
//        playersByScore = (List) players;
//        Collections.sort(playersByScore, new Comparator() {
//            public int compare(Object o1, Object o2) {
//                if (((Player) o1).getMoney() < ((Player) o2).getMoney()) {
//                    return -1;
//                } else {
//                    return 1;
//                }
//            }
//
//            public boolean equals(Object o1, Object o2) {
//                return false;
//            }
//        });
//        return playersByScore;
//    }

    /**
     * Returns the scheduleComposite.
     * 
     * @return ScheduleComposite
     */
    public RootSchedule getRootSchedule() {
        return rootSchedule;
    }

    /**
     * Returns the roundNumber.
     * 
     * @return int
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the roundNumber.
     * 
     * @param roundNumber
     *            The roundNumber to set
     */
    public void setYear(int roundNumber) {
        this.year = roundNumber;
    }

    /**
     * @return int
     */
    public int getState() {
        return state;
    }

    /**
     * @return
     */
    public boolean isRunning() {
        return getState() == IControlFlowModel.Running;
    }

    /**
     * @return
     */
    public boolean isStopped() {
        return getState() == IControlFlowModel.StopRequested;
    }

    /**
     * Sets the state.
     * 
     * @param int
     *            state The state to set
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return int returns the modifier for indexes (forward: 1, backward: -1)
     *         determined by game.getState()
     */
    public int getIndexModifierByState() {
        switch (this.getState()) {
        case IControlFlowModel.Running:
        case IControlFlowModel.Redoing:
            return 1;
        case IControlFlowModel.Undoing:
            return -1;
        default:
            return 1;
        }
    }

    /**
     * @param company
     */
    public void addCompany(Company company) {
        companies.add(company);

    }

    /**
     *  
     */
    public void execute() {
        rootSchedule.execute("ObjGame");
        //@todo: finish the game properly
        Lummerland.getLogger().info("game completed regularly");
    }

    /**
     * @param player
     * @return
     */
    public Player getNextPlayer(Player player) {
        int index = players.indexOf(player);
        if (index == players.size() - 1) {
            return (Player) players.get(0);
        }
        else {
            return (Player) players.get(index + 1);
        }
    }

    public Config getConfig() {
        return config;
    }

    /**
     * 
     */
    public void suspend() {
        thread.suspend();
    }

    /**
     * 
     */
    public void resume() {
        thread.resume();
    }
}