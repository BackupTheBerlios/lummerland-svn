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

package de.berlios.lummerland.gui.player;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.berlios.lummerland.Engine;
import de.berlios.lummerland.company.ICompany;
import de.berlios.lummerland.gui.stock.StockAdapter;
import de.berlios.lummerland.player.Player;

/**
 * @author Joerg Zuther
 */
public class PlayerAdapter extends Canvas {

    /**
     * Constructor for SquareAdapter.
     * 
     * @param arg0
     * @param arg1
     */
    public PlayerAdapter(Composite parent, Engine game) {
        super(parent, SWT.BORDER);

        GridLayout gridLayout = new GridLayout();

        Collection players = game.getPlayers();
        gridLayout.numColumns = players.size() + 2;

        setLayout(gridLayout);

        Collection companies = game.getCompanies();

        new Text(this, SWT.PUSH).setText("Player");
        new Text(this, SWT.PUSH).setText("Money");

        for (Iterator iter = companies.iterator(); iter.hasNext();) {
            ICompany company = (ICompany) iter.next();
            new Text(this, SWT.PUSH).setText(company.getName());
        }

        for (Iterator iter = players.iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();

            new Text(this, SWT.PUSH).setText(player.getName());

            new MoneyAdapter(this, player);

            for (Iterator iterator = companies.iterator(); iterator.hasNext();) {
                ICompany company = (ICompany) iterator.next();
                new StockAdapter(this, player, company);
            }

        }

    }

    public Point computeSize(int wHint, int hHint, boolean changed) {
        return new Point(900, 180);
    }

}