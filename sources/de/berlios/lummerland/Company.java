/*
 * Created on Jun 15, 2004
 *
 */
package de.berlios.lummerland;

import de.berlios.lummerland.company.ICompany;

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
    private Game game;
    private String name;

    /**
     * @param game
     * @param string
     */
    public Company(Game game, String name) {
        
        this.game = game;
        this.name = name;
        game.addCompany (this);
    }



}
