/*
 * Created on Jun 18, 2004
 *
 */
package de.berlios.lummerland.stock;

import de.berlios.lummerland.Engine;
import de.berlios.lummerland.company.ICompany;
import de.berlios.lummerland.player.Player;
import de.berlios.lummerland.schedule.ScheduleComposite;
import de.berlios.lummerland.schedule.ScheduleItem;

/**
 * @author Gregor
 *  
 */
public class BuyInitAction extends ScheduleItem {

    /**
     * @param game
     * @param name
     * @param parent
     */
    public BuyInitAction(Engine game, ScheduleComposite parent, Player p,
            ICompany c) {
        super(game, "Buy " + c.getName() + " (init)", parent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.schedule.ScheduleItem#executeBody()
     */
    protected void run() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.schedule.ScheduleItem#undoBody()
     */
    protected void undo() {
        // TODO Auto-generated method stub

    }

}