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

package de.berlios.lummerland.test.schedule;

import de.berlios.lummerland.Game;
import de.berlios.lummerland.schedule.ScheduleComposite;
import de.berlios.lummerland.schedule.ScheduleItem;

/**
 * @author Joerg Zuther
 */

public final class DummyScheduleItem extends ScheduleItem {

    private boolean executeVisited = false;
    private boolean undoVisited = false;
	private int executeGameState;
	private int undoGameState;

	/**
	 * Constructor for ScheduleItem.
	 * @param game
	 * @param name
	 * @param parent
	 */
	public DummyScheduleItem(Game game, String name, ScheduleComposite parent) {
		super(game, name, parent);
	}

	/**
	 * Method executeBody.
	 */
	protected void executeBody() {
		executeVisited = true;
		executeGameState = game.getState();
	}

	/**
	 * Method undoBody.
	 */
	protected void undoBody() {
		undoVisited = true;
		undoGameState = game.getState();
	}

	/**
	 * @return int
	 */
	public int getExecuteGameState() {
		return executeGameState;
	}

	/**
	 * @return boolean
	 */
	public boolean isExecuteVisited() {
		return executeVisited;
	}

	/**
	 * @return int
	 */
	public int getUndoGameState() {
		return undoGameState;
	}

	/**
	 * @return boolean
	 */
	public boolean isUndoVisited() {
		return undoVisited;
	}

	/**
	 * Sets the executeGameState.
	 * @param executeGameState The executeGameState to set
	 */
	public void setExecuteGameState(int executeGameState) {
		this.executeGameState = executeGameState;
	}

	/**
	 * Sets the executeVisited.
	 * @param executeVisited The executeVisited to set
	 */
	public void setExecuteVisited(boolean executeVisited) {
		this.executeVisited = executeVisited;
	}

	/**
	 * Sets the undoGameState.
	 * @param undoGameState The undoGameState to set
	 */
	public void setUndoGameState(int undoGameState) {
		this.undoGameState = undoGameState;
	}

	/**
	 * Sets the undoVisited.
	 * @param undoVisited The undoVisited to set
	 */
	public void setUndoVisited(boolean undoVisited) {
		this.undoVisited = undoVisited;
	}

}
