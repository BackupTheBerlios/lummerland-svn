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
package de.berlios.lummerland.util;

public final class Assert {

    private static class AssertionFailedException extends RuntimeException {

        /**
         * Constructs a new exception.
         */
        public AssertionFailedException() {
        }

        /**
         * Constructs a new exception with the given message.
         */
        public AssertionFailedException(String detail) {
            super(detail);
        }
    }

    /* This class is not intended to be instantiated. */
    private Assert() {
    }

    public static boolean isLegal(boolean expression) {
        // succeed as quickly as possible
        if (expression) {
            return true;
        }
        return isLegal(expression, "");//$NON-NLS-1$
    }

    public static boolean isLegal(boolean expression, String message) {
        if (!expression)
            throw new IllegalArgumentException("assertion failed; " + message); //$NON-NLS-1$
        return expression;
    }

    public static void isNotNull(Object object) {
        // succeed as quickly as possible
        if (object != null) {
            return;
        }
        isNotNull(object, "");//$NON-NLS-1$
    }

    public static void isNotNull(Object object, String message) {
        if (object == null)
            throw new AssertionFailedException("null argument;" + message);//$NON-NLS-1$
    }

    public static void isTrue(boolean expression) {
        // succeed as quickly as possible
        if (expression) {
            return;
        }
        isTrue(expression, "");//$NON-NLS-1$
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression)
            throw new AssertionFailedException("Assertion failed: " + message);//$NON-NLS-1$
    }
    
    public static void equals (Object left, Object right) {
        equals(left, right, "");
    }
    
    public static void equals (Object left, Object right, String message) {
        if (left != right)
            throw new AssertionFailedException("Assertion failed: " + message);//$NON-NLS-1$
    }
    

    public static void fail(String message) {
        throw new AssertionFailedException("Assertion failed: " + message);//$NON-NLS-1$
    }

}

