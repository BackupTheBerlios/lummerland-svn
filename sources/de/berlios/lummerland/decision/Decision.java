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

import java.util.List;
import de.berlios.lummerland.Lummerland;
import de.berlios.lummerland.gui.DecisionEvaluatorFactory;
import de.berlios.lummerland.gui.DecisionEvaluatorIF;

/**
 * @author Joerg Zuther
 */
public class Decision {

	String sourceName;
	String question;
	List choices;
	Choice result;

	/**
	 * @param String question
	 * @param ArrayList descriptions
	 */
	public Decision(String sourceName, String question, List choices) {
		this.sourceName = sourceName;
		this.question = question;
		if (question == "") {
			Lummerland.getLogger().error(
				"Source "
					+ sourceName
					+ " tried to create decision with an empty question");
			return;
		}
		this.choices = choices;
		if (choices.size() < 1) {
			Lummerland.getLogger().error(
				"Source "
					+ sourceName
					+ " tried to create decision without any choices");
			return;
		}
	}

	/**
	 * @return String
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @return int
	 */
	public Choice getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 * @param result The result to set
	 */

	public void setResult(Choice result) {
		this.result = result;
	}

	/**
	 * @return int
	 */
	public final int evaluate() {
		if (choices.size() == 1 && !(this instanceof Confirmation)) {
			result = (Choice) choices.get(0);
			return 0;
		}
		DecisionEvaluatorIF e = DecisionEvaluatorFactory.getDecisionEvaluator();
		return e.updateDecision(this);
	}

	/**
	 * @return String
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * @return List
	 */
	public List getChoices() {
		return choices;
	}
}
