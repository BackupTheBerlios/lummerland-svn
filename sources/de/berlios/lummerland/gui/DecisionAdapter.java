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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import de.berlios.lummerland.Game;
import de.berlios.lummerland.Lummerland;
import de.berlios.lummerland.decision.Choice;
import de.berlios.lummerland.decision.Decision;
import de.berlios.lummerland.gui.layout.LayoutFactory;

/**
 * @author Joerg Zuther
 */
public class DecisionAdapter extends Composite implements DecisionEvaluatorIF {

	protected List list;
	protected int currentSelection;
	protected boolean isRunning;
	protected int requestedGameState;
	protected Text title;

	/**
	 * Constructor for DecisionListener.
	 * @param arg0
	 * @param arg1
	 */
	public DecisionAdapter(Composite parent, Game game) {
		super(parent, SWT.BORDER);
		//new UndoButton(this,null);

		setLayout(LayoutFactory.getVerticalLayout());

		title = new Text(this, SWT.DEFAULT);
		list = new List(this, SWT.SINGLE);
		list.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				currentSelection = list.getSelectionIndex();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		Button undo = new Button(this, SWT.NONE);
		undo.setText("Undo");
		undo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				isRunning = false;
				requestedGameState = Game.Undoing;
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		Button redo = new Button(this, SWT.NONE);
		redo.setText("Redo");
		redo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				isRunning = false;
				requestedGameState = Game.Redoing;
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		Button ok = new Button(this, SWT.NONE);
		ok.setText("OK");
		ok.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				isRunning = false;
				requestedGameState = Game.Running;
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		//pack();
	}

	/* 
	 * @see de.zeitlinger.ursuppe.gui.DecisionAdapterIF#show(de.zeitlinger.ursuppe.schedule.Decision)
	 */
	public int updateDecision(final Decision d) {
		final java.util.List choices = d.getChoices();
		isRunning = true;
		Display dis = Lummerland.getInstance().getMainWindow().getDisplay();
		dis.asyncExec(new Runnable() {
			public void run() {
				title.setText(d.getQuestion());
                title.pack();
				for (int i = 0; i < choices.size(); i++) {
					Choice c = (Choice) choices.get(i);
					list.add(c.getDescription());
				}
				list.pack();
				list.select(0);
				currentSelection = 0;
			}
		});
		while (isRunning) {
			try {
				Thread.sleep(200);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		dis.asyncExec(new Runnable() {
			public void run() {
				list.removeAll();
				title.setText("");
			}
		});
		if (requestedGameState == Game.Running) {
			d.setResult((Choice) choices.get(currentSelection));
		}
		return requestedGameState;
	}

	public Point computeSize(int wHint, int hHint, boolean changed) {
		return new Point(180, 180);
	}
}
