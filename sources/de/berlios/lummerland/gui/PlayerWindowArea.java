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

package de.berlios.lummerland.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

import de.berlios.lummerland.Game;
import de.berlios.lummerland.gui.layout.LayoutFactory;

/**
 * @author Zuther
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class PlayerWindowArea extends Canvas {

    private DecisionAdapter decisionAdapter;

    //	private Game game;

    public PlayerWindowArea(Shell shell, Game game) {
        super(shell, SWT.NONE);

        //		this.game = game;

        this.setLayout(LayoutFactory.getHorizontalLayout());

        new PlayerAdapter(this, game);

        decisionAdapter = new DecisionAdapter(this, game);
        //decisionAdapter.setSize(180, 180);
    }

    /**
     * @return
     */
    public DecisionAdapter getDecisionAdapter() {
        return decisionAdapter;
    }

}