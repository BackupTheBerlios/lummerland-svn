/*
 * Created on Jun 15, 2004
 *
 */
package de.berlios.lummerland.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import de.berlios.lummerland.player.MoneyListener;
import de.berlios.lummerland.player.Player;


/**
 * @author Gregor
 *  
 */
public class MoneyAdapter implements MoneyListener {

    private Text money;
    private Player player;

    /**
     * @param adapter
     * @param player
     */
    public MoneyAdapter(Composite parent, Player player) {
        
        this.player = player;
        
        money = new Text (parent, SWT.PUSH);
        money.setText("" + player.getMoney());

        player.addMoneyListener(this);

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.player.MoneyListener#updateMoney()
     */
    public void updateMoney() {

        Display d = Display.getCurrent();
        d.asyncExec(new Runnable() {
            public void run() {
                money.setText("Money: " + player.getMoney());
                money.pack();
            }
        });

    }

}