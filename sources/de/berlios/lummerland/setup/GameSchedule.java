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

package de.berlios.lummerland.setup;

import de.berlios.lummerland.Game;
import de.berlios.lummerland.graphics.Color;
import de.berlios.lummerland.schedule.Schedule;
import de.berlios.lummerland.schedule.ScheduleComposite;
import de.berlios.lummerland.stock.StockRound;

/**
 * @author Joerg Zuther
 */

public class GameSchedule extends ScheduleComposite
{
	/**
	 * Constructor for GameSchedule.
	 * @param game
	 * @param parent
	 */
	public GameSchedule(Game game, ScheduleComposite parent)
	{
		super(game, "Game", parent);
	}
	
    protected void preAllChildrenRun() {
	    game.setPriorityDealHolder(game.getPlayer(Color.Red));
    }

    protected Schedule createNextSchedule() {
        game.setYear(game.getYear() + 1);
		return new StockRound(game, this, game.getYear ());
    }
}
