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

import de.berlios.lummerland.Game;
import de.berlios.lummerland.decision.Choice;
import de.berlios.lummerland.decision.Confirmation;
import de.berlios.lummerland.decision.Decision;

/**
 * @author Joerg Zuther
 */
public abstract class ConfirmationItem extends DecisionProducer {

	/**
	 * Constructor for ScheduleComposite.
	 * @param game, parent
	 */
	public ConfirmationItem(Game game, String name, ScheduleComposite parent) {
		super(game, name, parent);
	}
	
    /* (non-Javadoc)
     * @see de.zeitlinger.ursuppe.schedule.DecisionProducer#createDecision()
     */
    protected Decision createDecision() {
		List choices = new ArrayList ();
        choices.add(new Choice("confirm"));
        String caller = ConfirmationItem.class.getName();
        Decision d = new Confirmation(caller,getConfirmationString(),choices);
        return d;
    }

	protected abstract String getConfirmationString();
}
