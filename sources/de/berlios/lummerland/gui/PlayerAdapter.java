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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import de.berlios.lummerland.Lummerland;
import de.berlios.lummerland.gui.layout.LayoutFactory;
import de.berlios.lummerland.player.MoneyListener;
import de.berlios.lummerland.player.Player;
/**
 * @author Joerg Zuther
 */
public class PlayerAdapter
	extends Canvas
	implements MoneyListener {
	private Text name;
	private Text color;

	private Text money;
	//	public SquareAdapter() {
	//		super();
	//	}
	private Player player;
	/**
	 * Constructor for SquareAdapter.
	 * @param arg0
	 * @param arg1
	 */
	public PlayerAdapter(Composite parent, Player p) {
		super(parent, SWT.BORDER);
		setLayout(LayoutFactory.getVerticalLayout());

		this.player = p;

		name = new Text(this, SWT.DEFAULT);
		name.setText("Name: " + player.getName());

		color = new Text(this, SWT.DEFAULT);
		color.setText("Color: " + player.getColor().getDescription());

		money = new Text(this, SWT.DEFAULT);
		money.setText("Money: " + player.getMoney());

		player.addMoneyListener(this);
	}

	public Point computeSize(int wHint, int hHint, boolean changed) {
		return new Point(180, 180);
	}

	/**
	 * @see de.zeitlinger.ursuppe.player.BioPointListener#updateScore()
	 */
	public void updateMoney() {
		Display d = Lummerland.getInstance().getMainWindow().getDisplay();
		d.asyncExec(new Runnable() {
			public void run() {
				money.setText("Score: " + player.getMoney());
                money.pack();
			}
		});
	}
}
