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

import de.berlios.lummerland.Game;
import de.berlios.lummerland.schedule.Schedule;
import de.berlios.lummerland.schedule.ScheduleComposite;
import junit.framework.TestCase;

/**
 * @author Zuther
 */

public class ScheduleTest extends TestCase {

	private Schedule testSchedule;
	private ScheduleComposite parent;

	/**
	 * Constructor for ScheduleTest.
	 * @param arg0
	 */
	public ScheduleTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Game game = null;
		parent = new DummyScheduleComposite(game, "Parent", null);
		testSchedule = new DummySchedule(game, "DummySchedule", parent);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSchedule() {
		assertEquals(Schedule.Ready, testSchedule.getState());
	}

	public void testGetAncestorWithName() {
		assertEquals(null, testSchedule.getAncestorWithName("DummySchedule"));
		assertEquals(parent, testSchedule.getAncestorWithName("Parent"));
	}

	public void testGetName() {
		assertEquals("DummySchedule", testSchedule.getName());
	}

	public void testGetNamePath() {
		assertEquals("Parent-DummySchedule", testSchedule.getNamePath());
		assertEquals("Parent", parent.getNamePath());
	}

	public void testGetParent() {
		assertEquals(null, parent.getParent());
		assertEquals(parent, testSchedule.getParent());
	}
}
