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

/*
 * Created on Jun 23, 2004
 *
 */
package de.berlios.lummerland.gui;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;

import de.berlios.lummerland.Engine;
import de.berlios.lummerland.gui.action.RedoAction;
import de.berlios.lummerland.gui.action.UndoAction;
/**
 * @author Gregor
 *  
 */
public class RcpWorkbenchAdvisor extends WorkbenchAdvisor {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
     */
    public String getInitialWindowPerspectiveId() {
        return "de.berlios.lummerland.gui.RcpPerspective";
    }

    public void postWindowOpen(IWorkbenchWindowConfigurer configurer) {
        super.postWindowOpen(configurer);

        configurer.setInitialSize(new Point(400, 300));
        configurer.setShowCoolBar(false);
        configurer.setShowStatusLine(false);
        configurer.setTitle("Lummerland 18xx Moderator");
    }

    public void fillActionBars(IWorkbenchWindow window,
            IActionBarConfigurer configurer, int flags) {
        super.fillActionBars(window, configurer, flags);

        if ((flags & FILL_MENU_BAR) != 0) {
            fillMenuBar(window, configurer);
        }
    }

    /**
     * @param window
     * @param configurer
     */
    private void fillMenuBar(IWorkbenchWindow window,
            IActionBarConfigurer configurer) {
        IMenuManager menuBar = configurer.getMenuManager();

        MenuManager fileMenu = new MenuManager("&File");
        menuBar.add(fileMenu);

        fileMenu.add(ActionFactory.QUIT.create(window));

        MenuManager controlFlowMenu = new MenuManager("&Action");
        menuBar.add(controlFlowMenu);

        Engine engine = Engine.getInstance ();
        
        controlFlowMenu.add(new UndoAction(engine));
        controlFlowMenu.add(new RedoAction(engine));
    }
}