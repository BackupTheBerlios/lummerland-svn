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
import java.util.List;

//import org.eclipse.jface.util.Assert;

import de.berlios.lummerland.Game;
import de.berlios.lummerland.Lummerland;
import de.berlios.lummerland.decision.Decision;

/**
 * @author Joerg Zuther
 */

public abstract class ScheduleComposite extends Schedule {

	private final int ReadyIndex = -1;

	protected List schedules = new ArrayList();
	protected int schedulesIndex = ReadyIndex;

	/**
	 * Constructor for ScheduleComposite.
	 * @param game
	 * @param name
	 * @param parent
	 */
	public ScheduleComposite(
		Game game,
		String name,
		ScheduleComposite parent) {
		super(game, name, parent);
	}

	/**
	 * method addSchedule.
	 * @param s
	 */
	public void addSchedule(Schedule s) {
		schedules.add(s);
	}

	/**
	 * method removeLastSchedule.
	 */
	public void removeLastSchedule() {
		schedules.remove(getNumberOfSchedules() - 1);
	}

	/**
	 * method getNumberOfSchedules.
	 * @return int
	 */
	public int getNumberOfSchedules() {
		return schedules.size();
	}

	/**
	 * @return int
	 */
	public int getSchedulesIndex() {
		return schedulesIndex;
	}

	/**
	 * Sets the schedulesIndex.
	 * @param schedulesIndex The schedulesIndex to set
	 */
	public void setSchedulesIndex(int schedulesIndex) {
		this.schedulesIndex = schedulesIndex;
	}

	/**
	* @see de.berlios.lummerland.schedule.Schedule#execute()
	*/
	public final void execute(String callerName) {
		Lummerland.getLogger().info(
			name + ".execute() started from " + callerName);

		if (game.getState() == Game.StopRequested) {
			return;
		}
		if (!hasCorrectSchedulesIndex()) {
			Lummerland.getLogger().error(
				"Illegal schedulesIndex "
					+ schedulesIndex
					+ " when getNumberOfSchedules() was "
					+ getNumberOfSchedules()
					+ " and Game.getState() was "
					+ game.getState());
			return;
		}

		setSchedulesIndex(getSchedulesIndex() + game.getIndexModifierByState());
		while (getSchedulesIndex() > ReadyIndex
			&& getSchedulesIndex() < getNumberOfSchedules()) {
			Schedule s = (Schedule) schedules.get(schedulesIndex);
			switch (game.getState()) {
				case Game.Running :
					executeRun(s);
					break;
				case Game.Undoing :
					executeUndo(s);
					break;
				case Game.Redoing :
					setSchedulesIndex(executeRedo(getSchedulesIndex(), s));
					break;
				default :
					break;
			}
			setSchedulesIndex(
				getSchedulesIndex() + game.getIndexModifierByState());
		}

		Lummerland.getLogger().info(
			name + ".execute() finished - was started by " + callerName);
	}

	private boolean hasCorrectSchedulesIndex() {
		switch (game.getState()) {
			case Game.Running :
			case Game.Redoing :
				return schedulesIndex == ReadyIndex;
			case Game.Undoing :
				return schedulesIndex == getNumberOfSchedules();
			default :
				return false;
		}
	}

	private void executeRun(Schedule s) {
		if (s instanceof DecisionProducer) {
			s.execute(name);
			Decision d = ((DecisionProducer) s).getDecision();
//			Assert.isNotNull(d);
			if (!(s instanceof ConfirmationItem)) {
				game.getDecisionStack().push(name, d);
			}
			/** 
			 * The following means either a human or ai player to choose
			 * from the alternatives as specified by the decision; in case
			 * of a human player it is possible that the Undo-Button or Redo-
			 * Button becomes hit...
			 */
			d.evaluate();
			/**
			 * ...such that the state of the game could be changed here.
			 */
			if (game.getState() == Game.Running) {
				game.getDecisionStack().cut();
			}
		} else if (s instanceof DecisionConsumer) {
			((DecisionConsumer) s).setDecision(
				game.getDecisionStack().next(name));
			s.execute(name);
		} else {
			s.execute(name);
		}
	}

	private void executeUndo(Schedule s) {
		if (s instanceof DecisionProducer) {
			game.getDecisionStack().next(name);
		} else if (s instanceof DecisionConsumer) {
			((DecisionConsumer) s).undo(name);
			game.setState(Game.Running);
		} else if (s instanceof ScheduleItem) {
			((ScheduleItem) s).undo(name);
		} else {
			s.execute(name);
		}
	}

	private int executeRedo(int i, Schedule s) {
		if (s instanceof DecisionProducer) {
			game.setState(Game.Running);
			i--;
		} else if (s instanceof DecisionConsumer) {
			((DecisionConsumer) s).setDecision(
				game.getDecisionStack().next(name));
			s.execute(name);
		} else {
			s.execute(name);
		}
		return i;
	}
}
