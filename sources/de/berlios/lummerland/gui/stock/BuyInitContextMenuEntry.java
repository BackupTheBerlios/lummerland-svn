/*
 * Created on Jun 16, 2004
 *
 */
package de.berlios.lummerland.gui.stock;

import org.eclipse.jface.action.Action;


/**
 * @author Gregor
 *  
 */
public class BuyInitContextMenuEntry extends Action {

    private StockAdapter adapter;

    public BuyInitContextMenuEntry(StockAdapter adapter) {
        this.adapter = adapter;
        setText("Buy &Init");
        setToolTipText("Buy one share from the IPO");
    }

    public void run() {
        adapter.buyInit ();
    }
}