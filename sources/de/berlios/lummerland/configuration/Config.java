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
 * Created on Jun 21, 2004
 *
 */
package de.berlios.lummerland.configuration;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author Gregor
 *  
 */
public class Config {

    private Document document;

    public Config(String name) {
        document = parseFile(name);
    }
    
    Document parseFile (String name) {
        
        File xmlFile = new File(name);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(xmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Document getDocument() {
        return document;
    }
}