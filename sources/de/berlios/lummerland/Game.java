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

package de.berlios.lummerland;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import de.berlios.lummerland.board.map.Board;
import de.berlios.lummerland.graphics.*;
import de.berlios.lummerland.player.Player;
import de.berlios.lummerland.schedule.DecisionStack;
import de.berlios.lummerland.schedule.GameSchedule;

public class Game implements Runnable {
	private int state;
	public static final int Running = 0;
	public static final int Undoing = 1;
	public static final int Redoing = 2;
	public static final int StopRequested = 3;


	private Board board;
	private Collection companies = new ArrayList ();
	private Collection players = new ArrayList();
	private DecisionStack decisionStack = new DecisionStack(this);
	private GameSchedule gameSchedule;
	private int roundNumber;
	

	public Game() {
		Player redPlayer = new Player(this, Color.Red);
		redPlayer.setName("Red");
		Player bluePlayer = new Player(this, Color.Blue);
		bluePlayer.setName("Blue");
		Player greenPlayer = new Player(this, Color.Green);
		greenPlayer.setName("Green");
		Player yellowPlayer = new Player(this, Color.Yellow);
		yellowPlayer.setName("Yellow");
		
		new Company (this, "NYC");
		new Company (this, "NYNH");
		new Company (this, "C&O");
		new Company (this, "PRR");
		
		board = new Board(this);
		gameSchedule = new GameSchedule(this);
	}
	
    /**
     * @return Returns the companies.
     */
    public Collection getCompanies() {
        return companies;
    }

	/**
	 * Method getNumberOfPlayers.
	 * @return int
	 */
	public int getNumberOfPlayers() {
		return players.size();
	}

	/**
	 * Method testInitialize.
	 */
	public void run() {
		gameSchedule.execute("ObjGame");
		//@todo: finish the game properly
		Lummerland.getLogger().info("game completed regularly");
	}

	/**
	 * Returns the board.
	 * @return Board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Method addPlayer.
	 * @param player
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}

	/**
	 * Returns the players.
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
	 * @return List
	 */
	public Collection getPlayers() {
		return players;
	}

	/**
	 * Method getPlayersByScore
	 * Returns the player list ordered by score (highest first)
	 * @return List
	 */
	public Collection getPlayersByScore() {
	    List playersByScore = new ArrayList();
		playersByScore = (List) players;
		Collections.sort(playersByScore, new Comparator() {
			public int compare(Object o1, Object o2) {
				if (((Player) o1).getMoney() < ((Player) o2).getMoney()) {
					return -1;
				}
				else {
					return 1;
				}
			}
			public boolean equals(Object o1, Object o2) {
				return false;
			}
		});
		return playersByScore;
	}

	/**
	 * Returns the scheduleComposite.
	 * @return ScheduleComposite
	 */
	public GameSchedule getGameSchedule() {
		return gameSchedule;
	}

	/**
	 * Returns the roundNumber.
	 * @return int
	 */
	public int getRoundNumber() {
		return roundNumber;
	}

	/**
	 * Sets the roundNumber.
	 * @param roundNumber The roundNumber to set
	 */
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	/**
	 * @return DecisionStack
	 */
	public DecisionStack getDecisionStack() {
		return decisionStack;
	}

	/**
	 * @return int
	 */
	public int getState() {
		return state;
	}

	/**
	 * Sets the state.
	 * @param int state The state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return int
	 * returns the modifier for indexes (forward: 1, backward: -1)
	 * determined by game.getState()
	 */
	public int getIndexModifierByState() {
		switch (this.getState()) {
			case Game.Running :
			case Game.Redoing :
				return 1;
			case Game.Undoing :
				return -1;
			default :
				return 1;
		}
	}

    /**
     * @param company
     */
    public void addCompany(Company company) {
        companies.add (company);
        
    }


}
