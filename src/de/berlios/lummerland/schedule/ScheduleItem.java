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

import java.util.List;

import de.berlios.lummerland.Engine;
import de.berlios.lummerland.schedule.model.IControlFlowModel;
import de.berlios.lummerland.util.Assert;

/**
 * @author Joerg Zuther
 */
public abstract class ScheduleItem extends Schedule {
    public List getChildren() {
        return null;
    }

    protected boolean hasChildren() {
        return false;
    }

    /**
     * Constructor for ScheduleItem.
     * 
     * @param game
     * @param name
     * @param parent
     */
    public ScheduleItem(Engine game, String name, ScheduleComposite parent) {
        super(game, name, parent);
    }

    /**
     * Method execute.
     */
    public final void execute(String callerName) {
        Engine.getLogger().info(
                name + ".execute() started from " + callerName);
        if (game.getState() == IControlFlowModel.Undoing) {
            Assert.fail (callerName
                    + " tried to execute the scheduleItem " + name
                    + " while game was undoing");
        }
        if (state != Ready) {

            Assert.fail (callerName
                    + " tried to execute the scheduleItem " + name
                    + " with the illegal state " + state);
        }
        state = Busy;
        run();
        state = Finished;
        Engine.getLogger().info(
                name + ".execute() finished - was started by " + callerName);
    }

    /**
     * Method executeBody.
     */
    protected abstract void run();

    /**
     * Method undo.
     */
    public final void rawUndo(String callerName) {
        if (game.getState() != IControlFlowModel.Undoing) {
            Assert.fail (callerName
                    + " tried to undo the scheduleItem " + name
                    + " while game was running or redoing");
        }
        if (state != Finished) {
            Assert.fail (callerName
                    + " tried to undo the scheduleItem " + name
                    + " with the illegal state " + state);
        }
        state = Busy;
        undo();
        state = Ready;
    }

    /**
     * Method undoBody.
     */
    protected abstract void undo();
}