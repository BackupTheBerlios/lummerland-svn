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
public class PassAction extends Action {

    private StockAdapter adapter;

    public PassAction(StockAdapter adapter) {
        this.adapter = adapter;
        setText("&Pass");
        setToolTipText("Pass");
    }

    public void run() {
        adapter.pass ();
    }
}