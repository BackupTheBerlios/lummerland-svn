package de.berlios.lummerland.stock;

import de.berlios.lummerland.Game;
import de.berlios.lummerland.company.ICompany;
import de.berlios.lummerland.player.Player;
import de.berlios.lummerland.schedule.ScheduleComposite;
import de.berlios.lummerland.schedule.ScheduleItem;

/**
 * @author Joerg Zuther
 */

public class StockTurnSchedule extends ScheduleItem implements
        IStockTradingController {

    private Player player;

    private boolean isDone;

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
    public StockTurnSchedule(Game game, ScheduleComposite parent,
            final Player player) {
        super(game, "StockTrading", parent);

        this.player = player;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.stock.IStockTradingController#pass()
     */
    public void pass() {
        isDone = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.stock.IStockTradingController#buyInit(de.berlios.lummerland.company.Company)
     */
    public void buyInit(ICompany c) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.stock.IStockTradingController#buyPool(de.berlios.lummerland.company.Company)
     */
    public void buyPool(ICompany c) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.stock.IStockTradingController#sell(de.berlios.lummerland.company.Company)
     */
    public void sell(ICompany c) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.schedule.ScheduleItem#executeBody()
     */
    protected void executeBody() {

        isDone = false;

        player.activateStockTrading(this);

        while (!isDone) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        player.deactivateStockTrading();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.schedule.ScheduleItem#undoBody()
     */
    protected void undoBody() {
        // TODO Auto-generated method stub

    }
}