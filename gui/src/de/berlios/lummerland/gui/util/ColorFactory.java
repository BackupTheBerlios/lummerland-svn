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
 * Created on Jun 24, 2004
 *
 */
package de.berlios.lummerland.gui.util;

import org.eclipse.swt.widgets.Display;

import de.berlios.lummerland.graphics.Color;

/**
 * @author Gregor
 *  
 */
public class ColorFactory {
    public static org.eclipse.swt.graphics.Color getSwtColor(Display d, Color c) {
        return new org.eclipse.swt.graphics.Color(d, c.getRed(), c.getGreen(),
                c.getBlue());
    }
}