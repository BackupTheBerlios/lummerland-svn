/*
 * Created on Jun 16, 2004
 *
 */
package de.berlios.lummerland.stock;

import de.berlios.lummerland.company.ICompany;

/**
 * @author Gregor
 *
 */
public interface IStockTradingController {
    public void pass ();
    public void buyInit (ICompany c);
    public void buyPool (ICompany c);
    public void sell (ICompany c);
}
