/*
 * Created on Jun 16, 2004
 *
 */
package de.berlios.lummerland.gui.stock;

import org.eclipse.jface.action.Action;

import de.berlios.lummerland.gui.StockAdapter;

/**
 * @author Gregor
 *  
 */
public class BuyInitAction extends Action {

    private StockAdapter adapter;

    public BuyInitAction(StockAdapter adapter) {
        this.adapter = adapter;
        setText("Buy &Init");
        setToolTipText("Buy one share from the IPO");
    }

    public void run() {
        adapter.buyInit ();
    }
}