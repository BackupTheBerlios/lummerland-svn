/*
 * Created on Jun 16, 2004
 *
 */
package de.berlios.lummerland.gui.action;

import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;

/**
 * @author Gregor
 *  
 */
public class ExitAction extends Action {
    ApplicationWindow window;

    public ExitAction(ApplicationWindow w) {
        window = w;
        setText("E&xit@Ctrl+W");
        setToolTipText("Exit the application");
        try {
            setImageDescriptor(ImageDescriptor.createFromURL(new URL(
                    "file:icons/close.gif")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        window.close();
    }
}