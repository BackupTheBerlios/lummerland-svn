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

import de.berlios.lummerland.Game;
import de.berlios.lummerland.Lummerland;
import de.berlios.lummerland.model.tree.AbstractTreeModel;
import de.berlios.lummerland.model.tree.ITreeModel;
import de.berlios.lummerland.model.tree.ITreeViewable;

/**
 * @author Joerg Zuther
 */
public abstract class Schedule implements ITreeViewable {

    public static final int Ready = 0;

    public static final int Busy = 1;

    public static final int Finished = 2;

    public static final int Corrupt = 3;

    protected Game game;

    protected String name;

    protected ScheduleComposite parent;

    protected int state = Schedule.Ready;



    private ITreeModel treeModel;

    /**
     * Constructor for ScheduleComposite.
     * 
     * @param game
     * @param name
     * @param parent
     */
    public Schedule(Game game, String name, ScheduleComposite parent) {
        this.game = game;
        this.name = name;
        this.parent = parent;
        Lummerland.getLogger().info(name + " created");
        
        treeModel = new AbstractTreeModel (this){
            public List getChildren() {
                return Schedule.this.getChildren();
            }

            public ITreeViewable getParent() {
                return Schedule.this.getParent();
            }

            public boolean hasChildren() {
                return Schedule.this.hasChildren();
            }

            public String getLabel() {
                return Schedule.this.getName();
            }
        };
    }

    /**
     * @return Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Returns the name.
     * 
     * @return String
     */
    public final String getName() {
        return name;
    }

    public String toString() {
        return getNamePath();
    }

    /**
     * Returns the isRoot.
     * 
     * @return boolean
     */
    public final boolean isRoot() {
        return parent == null;
    }

    /**
     * Returns the parent.
     * 
     * @return ScheduleComposite
     */
    public final ScheduleComposite getParent() {
        if (isRoot()) {
            Lummerland.getLogger()
                    .error(
                            "tried to get the parent of root ScheduleComposite "
                                    + name);
            return null;
        } else {
            return parent;
        }
    }

    /**
     * Sets the parent.
     * 
     * @param parent
     *            The parent to set
     */
    public final void setParent(ScheduleComposite parent) {
        this.parent = parent;
    }

    /**
     * @return int
     */
    public final int getState() {
        return state;
    }

    /**
     * Sets the state.
     * 
     * @param state
     *            The state to set
     */
    public final void setState(int state) {
        this.state = state;
    }

    /**
     * Returns the name path of this Schedule in the schedule tree
     * 
     * @return String
     */
    public final String getNamePath() {
        if (isRoot()) {
            return name;
        } else {
            return parent.getNamePath() + "-" + name;
        }
    }

    /**
     * Returns the ancestor whose name starts with the param name
     * 
     * @return ScheduleComposite
     */
    public final ScheduleComposite getAncestorWithName(String match) {
        if (parent == null) {
            Lummerland.getLogger()
                    .error(
                            "No ancestor found whose name starts with '"
                                    + match + "'.");
            return null;
        }
        if (parent.getName().startsWith(match)) {
            return parent;
        } else {
            return parent.getAncestorWithName(match);
        }
    }

    /**
     * Method execute.
     */
    abstract public void execute(String callerName);

    public abstract List getChildren();

    protected abstract boolean hasChildren();

    public ITreeModel getTreeModel() {
        
        return treeModel;
    }



}