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
import de.berlios.lummerland.decision.Decision;
import de.berlios.lummerland.schedule.DecisionConsumer;
import de.berlios.lummerland.schedule.ScheduleComposite;
/**
 * @author Joerg Zuther
 */
public class DummyDecisionConsumer extends DecisionConsumer {
	/**
	 * Constructor for ScheduleComposite.
	 * 
	 * @param game,
	 *            parent
	 */
	public DummyDecisionConsumer(Game game, String name,
			ScheduleComposite parent) {
		super(game, name, parent);
	}
	/**
	 * Method consumeDecision.
	 */
	protected void consumeDecision(Decision d) {
	}
	protected void undoBody() {
	}
}