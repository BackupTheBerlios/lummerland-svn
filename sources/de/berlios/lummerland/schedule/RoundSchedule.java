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

public class RoundSchedule extends ScheduleComposite
{
	private int roundNumber;
	/**
	 * Constructor for RoundSchedule.
	 * @param Game game
	 * @param ScheduleComposite parent
	 * @param int number
	 */
	public RoundSchedule(Game game, ScheduleComposite parent, int number)
	{
		super(game, "Round" + number, parent);
		this.roundNumber = number;
		game.setRoundNumber(number);

	}
	/**
	 * @return
	 */
	public int getRoundNumber() {
		return roundNumber;
	}

}
