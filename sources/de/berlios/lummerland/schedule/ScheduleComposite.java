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
import de.berlios.lummerland.Lummerland;
import de.berlios.lummerland.exception.AssertionFailedException;
import de.berlios.lummerland.schedule.model.IControlFlowModel;

/**
 * @author Joerg Zuther
 */

public abstract class ScheduleComposite extends Schedule {

    private final int ReadyIndex = -1;

    protected List children = new ArrayList();

    protected int cursorIndex = ReadyIndex;

    /**
     * Constructor for ScheduleComposite.
     * 
     * @param game
     * @param name
     * @param parent
     */
    public ScheduleComposite(Game game, String name, ScheduleComposite parent) {
        super(game, name, parent);
    }

    /**
     * method addSchedule.
     * 
     * @param s
     */
    public void addAfterLastChild(Schedule s) {
        children.add(s);
        getTreeModel().fireAddChild(s);
    }

    /**
     * method removeLastSchedule.
     */
    public void removeLastChild() {
        Schedule s = (Schedule) children.get(getChildCount() - 1);
        children.remove(s);
        s.getTreeModel().fireRemove();
    }

    /**
     * method getNumberOfSchedules.
     * 
     * @return int
     */
    public int getChildCount() {
        return children.size();
    }

    /**
     * @return int
     */
    public int getCursorIndex() {
        return cursorIndex;
    }

    /**
     * Sets the schedulesIndex.
     * 
     * @param schedulesIndex
     *            The schedulesIndex to set
     */
    public void setCursorIndex(int schedulesIndex) {
        this.cursorIndex = schedulesIndex;
    }

    /**
     * @see de.berlios.lummerland.schedule.Schedule#execute()
     */
    public final void execute(String callerName) {

        if (game.isRunning()) {
            preAllChildrenRun();
        }

        Lummerland.getLogger().info(
                name + ".execute() started from " + callerName);

        if (game.getState() == IControlFlowModel.StopRequested) {
            return;
        }
        if (!hasCorrectSchedulesIndex()) {
            throw new AssertionFailedException("Illegal cursorIndex "
                    + cursorIndex + " when getNumberOfSchedules() was "
                    + getChildCount() + " and Game.getState() was "
                    + game.getState());
        }

        setCursorIndex(getCursorIndex() + game.getIndexModifierByState());

        if (game.isRunning()) {
            addNextschedule();
        }

        while (getCursorIndex() > ReadyIndex
                && getCursorIndex() < getChildCount()) {
            Schedule s = (Schedule) children.get(cursorIndex);
            
            IControlFlowModel model = game.getControlFlowModel();
            
            switch (model.getNextAction ()) {
            case IControlFlowModel.Running:
                s.execute(name);
                addNextschedule();
                break;
            case IControlFlowModel.Undoing:
                executeUndo(s);
                break;
            case IControlFlowModel.Redoing:
                s.execute(name);
                break;
            default:
                break;
            }
            setCursorIndex(getCursorIndex() + game.getIndexModifierByState());
        }

        if (game.isRunning()) {
            postAllChildrenRun();
        }
        Lummerland.getLogger().info(
                name + ".execute() finished - was started by " + callerName);
    }

    /**
     *  
     */
    private void addNextschedule() {
        Schedule next = createNextSchedule();
        if (next != null) {
            addAfterLastChild(next);
        }
    }

    protected Schedule createNextSchedule() {
        return null;
    }

    protected void preAllChildrenRun() {
    }

    protected void postAllChildrenRun() {
    }

    private boolean hasCorrectSchedulesIndex() {
        switch (game.getState()) {
        case IControlFlowModel.Running:
        case IControlFlowModel.Redoing:
            return cursorIndex == ReadyIndex;
        case IControlFlowModel.Undoing:
            return cursorIndex == getChildCount();
        default:
            return false;
        }
    }

    private void executeUndo(Schedule s) {
        if (s instanceof ScheduleItem) {
            ((ScheduleItem) s).rawUndo(name);
        } else {
            s.execute(name);
        }
    }

    public List getChildren() {
        return children;
    }

    protected boolean hasChildren() {
        return children.size() > 0;
    }
}