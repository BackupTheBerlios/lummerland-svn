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

package de.berlios.lummerland.graphics;

import org.eclipse.swt.graphics.Device;

/**
 * @author Zuther
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Color {
	private int red;
	private int green;
	private int blue;
	private String description;
	public static final Color Red = new Color("red", 255, 0, 0);
	public static final Color Green = new Color("green", 0, 255, 0);
	public static final Color Blue = new Color("blue", 0, 0, 255);
	public static final Color Yellow = new Color("yellow", 255, 255, 0);

	private Color(String description, int red, int green, int blue) {
		this.description = description;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * Returns the blue.
	 * @return int
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * Returns the description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the green.
	 * @return int
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * Returns the red.
	 * @return int
	 */
	public int getRed() {
		return red;
	}

	public org.eclipse.swt.graphics.Color getSwtColor(Device d) {
		return new org.eclipse.swt.graphics.Color(d, getRed(), getGreen(), getBlue());
	}
}