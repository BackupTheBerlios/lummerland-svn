/*
 * Created on Jun 15, 2004
 *
 */
package de.berlios.lummerland.gui;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

import de.berlios.lummerland.company.ICompany;
import de.berlios.lummerland.gui.stock.BuyInitAction;
import de.berlios.lummerland.gui.stock.PassAction;
import de.berlios.lummerland.player.Player;
import de.berlios.lummerland.stock.IStockTradeListener;
import de.berlios.lummerland.stock.IStockTradingController;

/**
 * @author Gregor
 *  
 */
public class StockAdapter implements ISpecificStockListener,
        IStockTradeListener {

    private Text stock;

    private ICompany company;

    private IStockTradingController controller;

    /**
     * @param adapter
     * @param player
     */
    public StockAdapter(Composite parent, Player player, ICompany company) {

        player.addSpecificStockListener(this);

        player.addStockTradeListener(this);

        stock = new Text(parent, SWT.PUSH);
        setText(0);

        MenuManager manager = new MenuManager();

        manager.add(new BuyInitAction(this));
        manager.add(new PassAction(this));

        Menu contextMenu = manager.createContextMenu(stock);

        stock.setMenu(contextMenu);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.player.MoneyListener#updateMoney()
     */
    public void updatePercentage(final int percentage) {

        Display.getCurrent().asyncExec(new Runnable() {
            public void run() {
                setText(percentage);
            }
        });
    }

    /**
     * @param percentage
     */
    private void setText(final int percentage) {
        stock.setText("" + percentage);
        stock.pack();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.gui.ISpecificStockListener#getInterestedCompany()
     */
    public ICompany getInterestedCompany() {
        return company;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.stock.IStockTradeListener#activateStockTrading(de.berlios.lummerland.stock.IStockTradingController)
     */
    public void activateStockTrading(IStockTradingController c) {
        controller = c;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.stock.IStockTradeListener#deactivateStockTrading()
     */
    public void deactivateStockTrading() {
        controller = null;
    }

    /**
     *  
     */
    public void buyInit() {
        if (controller != null) {
            controller.buyInit(company);
        }
    }

    /**
     * 
     */
    public void pass() {
        if (controller != null) {
            controller.pass ();
        }
    }
}