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

package de.berlios.lummerland;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import de.berlios.lummerland.gui.MainWindow;
/**
 * @author Joerg Zuther
 */
public class Lummerland
{
	public static final int MinPlayers = 3;
	public static final int MaxPlayers = 6;

	private MainWindow mainWindow;
	private static Lummerland ursuppe;
	private static Logger logger = null;
	private boolean stopRequest;
	/**
	 * Constructor for Ursuppe.
	 */
	public Lummerland()
	{
//        assert false:new RuntimeException();
		ursuppe = this;
		//Game
		Game game = new Game();
		mainWindow = new MainWindow(game);
		// for testing
		Thread gameThread = new Thread(game, "Ursuppe");
		gameThread.start();
		mainWindow.show();
	}
	public static void main(String[] args)
	{
		new Lummerland();
	}
	public static Lummerland getInstance()
	{
		return ursuppe;
	}
	/**
	 * 
	 */
	public void stopRequest()
	{
		stopRequest = true;
	}
	public boolean isStopRequested()
	{
		return stopRequest;
	}
	/**
	 * Returns the mainWindow.
	 * @return MainWindow
	 */
	public MainWindow getMainWindow()
	{
		return mainWindow;
	}
	/**
	 * Returns the logger.
	 * @return Logger
	 */
	public static Logger getLogger()
	{
		if (logger == null)
		{
			logger = Logger.getRootLogger();
			//Logger settings
			String pattern =
				"--------------------------------------------------------------------- %n";
			pattern += "Message:  %m %n";
			pattern += "Location: %l %n";
			pattern += "Date:     %d{ISO8601} %n";
			PatternLayout layout = new PatternLayout(pattern);
			ConsoleAppender appender = new ConsoleAppender(layout);
			logger.addAppender(appender);
			logger.setLevel(Level.DEBUG);
		}
		return logger;
	}
}
