/*
 * Created on Jun 15, 2004
 *
 */
package de.berlios.lummerland.gui;

import de.berlios.lummerland.company.ICompany;

/**
 * @author Gregor
 *
 */
public interface ISpecificStockListener {
    public ICompany getInterestedCompany ();
    
    public void updatePercentage (int percentage);
}
