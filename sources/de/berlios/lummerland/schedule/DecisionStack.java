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

package de.berlios.lummerland.schedule;

import java.util.ArrayList;
import de.berlios.lummerland.Game;
import de.berlios.lummerland.Lummerland;
import de.berlios.lummerland.decision.Decision;

/**
 * @author Joerg Zuther
 */
public class DecisionStack {
	private Game game;
	private ArrayList decisions = new ArrayList();
	private int current = 0;

	/**
	 * Constructor for DecisionStack
	 * @param game
	 */
	public DecisionStack(Game game) {
		this.game = game;
	}

	/**
	 * Method push
	 * @param callerName   the name of the calling entity
	 * @param decision     the decision to put onto the stack
	 */
	public void push(String callerName, Decision decision) {
		if (game.getState() != Game.Running) {
			Lummerland.getLogger().error(
				callerName
					+ " tried to put decision onto the decision stack"
					+ " while game was not running");
			return;
		}
		if (current != decisions.size()) {
			Lummerland.getLogger().error(
				callerName
					+ " tried to put decision onto the decision stack"
					+ " while current position was not at the top");
			return;
		}
		decisions.add(decision);
	}

	/**
	 * Method current
	 * @param callerName   the name of the calling entity
	 */
	public Decision next(String callerName) {
		if (decisions.isEmpty()) {
			Lummerland.getLogger().error(
				callerName + " tried to get an element of empty decision stack");
			return null;
		}
		Decision d;
		if (game.getState() == Game.Undoing) {
			if (current <= 0 || current > decisions.size()) {
				Lummerland.getLogger().error(
					callerName
						+ " tried to get current decision at "
						+ current
						+ " while undoing. size is "
						+ decisions.size());
				return null;
			}
			--current;
			d = (Decision) decisions.get(current);
		}
		else {
			if (current < 0 || current >= decisions.size()) {
				Lummerland.getLogger().error(
					callerName
						+ " tried to get current decision at "
						+ current
						+ " while running or redoing. size is "
						+ decisions.size());
				return null;
			}
			d = (Decision) decisions.get(current);
			++current;
		}
		return d;
	}

	/**
	 * Method cut
	 * @param callerName   the name of the calling entity
	 */
	public void cut() {
		for (int i = decisions.size() - 1; i > current; --i) {
			decisions.remove(i);
		}
	}
}
