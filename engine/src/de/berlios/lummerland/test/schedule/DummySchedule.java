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

package de.berlios.lummerland.test.schedule;

import java.util.List;

import de.berlios.lummerland.Engine;
import de.berlios.lummerland.schedule.Schedule;
import de.berlios.lummerland.schedule.ScheduleComposite;

/**
 * @author Joerg Zuther
 */

public final class DummySchedule extends Schedule {

    private boolean visited = false;

    public DummySchedule(Engine game, String name, ScheduleComposite parent) {
        super(game, name, parent);
    }

    public void execute(String callerName) {
        visited = true;
    }

    /**
     * @return Boolean
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the visited.
     * 
     * @param visited
     *            The visited to set
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.schedule.Schedule#getChildren()
     */
    public List getChildren() {
        return null;
    }

    protected boolean hasChildren() {
        return false;
    }
}