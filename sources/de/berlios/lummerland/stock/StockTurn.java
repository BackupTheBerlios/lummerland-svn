package de.berlios.lummerland.stock;

import de.berlios.lummerland.Game;
import de.berlios.lummerland.company.ICompany;
import de.berlios.lummerland.player.Player;
import de.berlios.lummerland.schedule.Schedule;
import de.berlios.lummerland.schedule.ScheduleComposite;

/**
 * @author Joerg Zuther
 */

public class StockTurn extends ScheduleComposite implements
        IStockTradingController {

    private Player player;

    private Schedule next = null;
    
    /**
     * Constructor for AmoebaMove.
     * 
     * @param Game
     *            game
     * @param ScheduleComposite
     *            parent
     * @param final
     *            Amoeba a
     */
    public StockTurn(Game game, ScheduleComposite parent, final Player player) {
        super(game, "StockTurn of " + player, parent);

        this.player = player;
    }

    protected void preAllChildrenRun() {
        player.activateStockTrading(this);
    }

    protected void postAllChildrenRun() {
        player.deactivateStockTrading();
    }
    
    protected Schedule createNextSchedule() {
        game.suspend();

        return next;
    }

    public void pass() {
        next = null;
        game.suspend ();
    }

    public void buyInit(ICompany c) {
        next = new BuyInitAction(game, this, player, c);
        game.resume ();
    }

    public void buyPool(ICompany c) {
        next = null;
        game.resume ();
    }

    public void sell(ICompany c) {
        next = null;
        game.resume ();
    }

}