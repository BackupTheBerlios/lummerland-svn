/*
 * Created on Jun 15, 2004
 *
 */
package de.berlios.lummerland.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import de.berlios.lummerland.Company;
import de.berlios.lummerland.Lummerland;
import de.berlios.lummerland.company.ICompany;
import de.berlios.lummerland.player.MoneyListener;
import de.berlios.lummerland.player.Player;


/**
 * @author Gregor
 *  
 */
public class StockAdapter implements ISpecificStockListener {

    private Text stock;
    private Player player;
    private Company company;

    /**
     * @param adapter
     * @param player
     */
    public StockAdapter(Composite parent, Player player, Company company) {
        
        this.player = player;
        
        stock = new Text (parent, SWT.PUSH);


        player.addSpecificStockListener (this);

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.player.MoneyListener#updateMoney()
     */
    public void updatePercentage (final int percentage) {

        Display d = Lummerland.getInstance().getMainWindow().getDisplay();
        d.asyncExec(new Runnable() {
            public void run() {
                stock.setText("" + percentage);
                stock.pack();
            }
        });

    }

    /* (non-Javadoc)
     * @see de.berlios.lummerland.gui.ISpecificStockListener#getInterestedCompany()
     */
    public ICompany getInterestedCompany() {
        return company;
    }

}