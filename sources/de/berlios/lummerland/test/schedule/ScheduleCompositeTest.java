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
 * Created on 13.03.2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package de.berlios.lummerland.test.schedule;
import java.util.ArrayList;
import java.util.List;

import de.berlios.lummerland.Game;
import junit.framework.TestCase;
/**
 * @author Zuther
 */
public class ScheduleCompositeTest extends TestCase {

	private DummyScheduleComposite testScheduleComposite;
	private List dummySchedules = new ArrayList();
	private DummyScheduleItem dummyScheduleItem;
	private DummyDecisionProducer dummyDecisionProducer;
	private DummyDecisionConsumer dummyDecisionConsumer;
	private Game game;
	private String name;

	/**
	 * Constructor for ScheduleCompositeTest.
	 * @param arg0
	 */
	public ScheduleCompositeTest(String arg0) {
		super(arg0);
	}

	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		name = "ScheduleCompositeTest";
		game = new Game();
		testScheduleComposite =
			new DummyScheduleComposite(game, "DummyScheduleComposite", null);
		for (int i = 0; i < 10; i++) {
			dummySchedules.add(
				new DummySchedule(
					game,
					"DummySchedule" + i,
					testScheduleComposite));
		}
		dummyScheduleItem =
			new DummyScheduleItem(
				game,
				"DummyScheduleItem",
				testScheduleComposite);
		dummyDecisionProducer =
			new DummyDecisionProducer(
				game,
				"DummyDecisionProducer",
				testScheduleComposite);
		dummyDecisionConsumer =
			new DummyDecisionConsumer(
				game,
				"DummyDecisionConsumer",
				testScheduleComposite);
	}

	/**
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		for (int i = 0; i < dummySchedules.size(); i++) {
			dummySchedules.remove(i);
		}
	}

	public void testConstructor() {
		assertEquals(
			"No schedules expected",
			0,
			testScheduleComposite.getNumberOfSchedules());
	}

	public void testAddAndRemoveSchedule() {
		try {
			testScheduleComposite.removeLastSchedule();
			fail("ScheduleComposite.removeLastSchedule returned no error despite of an list expected to be empty");
		} catch (IndexOutOfBoundsException e) {
		}
		assertEquals(0, testScheduleComposite.getNumberOfSchedules());

		addDummySchedules(1);
		assertEquals(1, testScheduleComposite.getNumberOfSchedules());

		testScheduleComposite.removeLastSchedule();
		assertEquals(0, testScheduleComposite.getNumberOfSchedules());
	}

	public void testExecuteNoSchedule() {
		assertExecuteRunUndoRedo(0);
	}

	public void testExecuteOneSchedule() {
		assertExecuteRunUndoRedo(1);
	}

	public void testExecuteTwoSchedules() {
		assertExecuteRunUndoRedo(2);
	}

	public void testExecuteTenSchedules() {
		assertExecuteRunUndoRedo(10);
	}

	private void addDummySchedules(int nods) {
		for (int i = 0; i < nods; i++) {
			testScheduleComposite.addSchedule(
				(DummySchedule) dummySchedules.get(i));
		}
	}

	private void assertExecuteRunUndoRedo(int numberOfDummySchedules) {
		addDummySchedules(numberOfDummySchedules);
		assertEquals(
			numberOfDummySchedules,
			testScheduleComposite.getNumberOfSchedules());

		assertExecute(
			numberOfDummySchedules,
			Game.Running,
			Game.Running,
			-1,
			numberOfDummySchedules);
		assertExecute(
			numberOfDummySchedules,
			Game.Undoing,
			Game.Undoing,
			numberOfDummySchedules,
			-1);
		assertExecute(
			numberOfDummySchedules,
			Game.Redoing,
			Game.Redoing,
			-1,
			numberOfDummySchedules);
	}

	private void assertExecute(
		int numberOfDummySchedules,
		int gameState,
		int expectedGameState,
		int initialIndex,
		int expectedIndex) {
		assertEquals(initialIndex, testScheduleComposite.getSchedulesIndex());

		game.setState(gameState);

		testScheduleComposite.execute(name);

		for (int i = 0; i < numberOfDummySchedules; i++) {
			assertTrue(((DummySchedule) dummySchedules.get(i)).isVisited());
		}
		assertEquals(expectedIndex, testScheduleComposite.getSchedulesIndex());
		assertEquals(expectedGameState, game.getState());
	}

	public void testExecuteWithScheduleItem() {
		testScheduleComposite.addSchedule(dummyScheduleItem);

		assertExecuteWithScheduleItem(
			Game.Running,
			Game.Running,
			-1,
			true,
			false);
		assertExecuteWithScheduleItem(
			Game.Undoing,
			-1,
			Game.Undoing,
			false,
			true);
		assertExecuteWithScheduleItem(
			Game.Redoing,
			Game.Redoing,
			-1,
			true,
			false);
	}

	private void assertExecuteWithScheduleItem(
		int gameState,
		int expectedExecuteGameState,
		int expectedUndoGameState,
		boolean expectedIsExecuteVisited,
		boolean expectedIsUndoVisited) {
		dummyScheduleItem.setExecuteGameState(-1);
		dummyScheduleItem.setUndoGameState(-1);
		dummyScheduleItem.setExecuteVisited(false);
		dummyScheduleItem.setUndoVisited(false);

		game.setState(gameState);

		testScheduleComposite.execute(name);

		assertEquals(
			expectedExecuteGameState,
			dummyScheduleItem.getExecuteGameState());
		assertEquals(
			expectedUndoGameState,
			dummyScheduleItem.getUndoGameState());
		assertEquals(
			expectedIsExecuteVisited,
			dummyScheduleItem.isExecuteVisited());
		assertEquals(expectedIsUndoVisited, dummyScheduleItem.isUndoVisited());
	}

	public void testExecuteWithDecisionProducerAndConsumer() {
		testScheduleComposite.addSchedule(dummyDecisionProducer);
		testScheduleComposite.addSchedule(dummyDecisionConsumer);

		assertExecuteWithDecisionProducerAndConsumer(
			Game.Running,
			Game.Running,
			-1,
			true,
			false);
		assertExecuteWithDecisionProducerAndConsumer(
			Game.Undoing,
			-1,
			Game.Undoing,
			false,
			true);
		assertExecuteWithDecisionProducerAndConsumer(
			Game.Redoing,
			Game.Redoing,
			-1,
			true,
			false);
	}

	private void assertExecuteWithDecisionProducerAndConsumer(
		int gameState,
		int expectedExecuteGameState,
		int expectedUndoGameState,
		boolean expectedIsExecuteVisited,
		boolean expectedIsUndoVisited) {
		//todo implement DecisionProducerAndConsumerTest
	}
}
