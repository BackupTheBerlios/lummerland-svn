/*
 * Created on Jun 16, 2004
 *
 */
package de.berlios.lummerland.stock;

/**
 * @author Gregor
 *  
 */
public interface IStockTradeListener {
    public void activateStockTrading(IStockTradingController controller);

    public void deactivateStockTrading();
}