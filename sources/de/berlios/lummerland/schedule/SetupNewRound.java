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

import de.berlios.lummerland.Game;

/**
 * @author Joerg Zuther
 */

public class SetupNewRound extends ScheduleItem {

	/**
	 * Constructor for SetupNewRound.
	 * @param game
	 * @param parent
	 */
	public SetupNewRound(Game game, ScheduleComposite parent) {
		super(game, "SetupNewRound", parent);
	}

	/**
	 * @see de.berlios.lummerland.schedule.ScheduleItem#executeBody()
	 */
	public void executeBody() {

	}

	/**
	 * @see de.berlios.lummerland.schedule.ScheduleItem#undoBody()
	 */
	protected void undoBody() {
		if (game.getRoundNumber() < 1) {
			return;
		}
		ScheduleComposite gameSchedule = getAncestorWithName("Game");
		gameSchedule.removeLastSchedule();
	}
}
