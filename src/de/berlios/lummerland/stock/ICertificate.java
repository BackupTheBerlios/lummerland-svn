/*
 * Created on Jun 15, 2004
 *
 */
package de.berlios.lummerland.stock;

/**
 * @author Gregor
 *  
 */
public interface ICertificate {
    public int getPercentage();

    boolean isPresidentsCertificate();

    public ICertificateHolder getOwnwer();

}