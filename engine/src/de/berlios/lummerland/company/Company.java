/*
 * Created on Jun 15, 2004
 *
 */
package de.berlios.lummerland.company;

import de.berlios.lummerland.Engine;

/**
 * @author Gregor
 *  
 */
public class Company implements ICompany {

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    //    private Engine game;
    private String name;

    /**
     * @param game
     * @param string
     */
    public Company(Engine game, String name) {

        //        this.game = game;
        this.name = name;
        game.addCompany(this);
    }

}