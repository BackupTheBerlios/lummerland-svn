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
import de.berlios.lummerland.Lummerland;
/**
 * @author Joerg Zuther
 */
public abstract class ScheduleItem extends Schedule
{
	/**
	 * Constructor for ScheduleItem.
	 * @param game
	 * @param name
	 * @param parent
	 */
	public ScheduleItem(Game game, String name, ScheduleComposite parent)
	{
		super(game, name, parent);
	}
	/**
	 * Method execute.
	 */
	public final void execute(String callerName)
	{
		Lummerland.getLogger().info(name + ".execute() started from " + callerName);
		if (game.getState() == Game.Undoing)
		{
			Lummerland.getLogger().error(
				callerName
					+ " tried to execute the scheduleItem "
					+ name
					+ " while game was undoing");
			return;
		}
		if (state != Ready)
		{
			Lummerland.getLogger().error(
				callerName
					+ " tried to execute the scheduleItem "
					+ name
					+ " with the illegal state "
					+ state);
			return;
		}
		state = Busy;
		executeBody();
		state = Finished;
		Lummerland.getLogger().info(
			name + ".execute() finished - was started by " + callerName);
	}
	/**
	 * Method executeBody.
	 */
	protected abstract void executeBody();
	/**
	 * Method undo.
	 */
	public final void undo(String callerName)
	{
		if (game.getState() != Game.Undoing)
		{
			Lummerland.getLogger().error(
				callerName
					+ " tried to undo the scheduleItem "
					+ name
					+ " while game was running or redoing");
			return;
		}
		if (state != Finished)
		{
			Lummerland.getLogger().error(
				callerName
					+ " tried to undo the scheduleItem "
					+ name
					+ " with the illegal state "
					+ state);
			return;
		}
		state = Busy;
		undoBody();
		state = Ready;
	}
	/**
	 * Method undoBody.
	 */
	protected abstract void undoBody();
}
