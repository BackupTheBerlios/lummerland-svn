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

package de.berlios.lummerland.decision;

import de.berlios.lummerland.schedule.Schedule;

/**
 * @author zutherj
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MoveChoice extends Choice {

	private int cost;
	private Schedule schedule; 

	/**
	 * Constructor for MoveChoice.
	 */
	public MoveChoice(String description, int cost, Schedule item) {
		super(description);
		
		this.cost = cost;
		this.schedule = item;
		
	}

	/**
	 * Returns the cost.
	 * @return int
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Returns the schedule.
	 * @return ScheduleItem
	 */
	public Schedule getSchedule() {
		return schedule;
	}

}
