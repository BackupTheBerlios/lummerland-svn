/*
 * Created on Jun 16, 2004
 *
 */
package de.berlios.lummerland.gui.action;

import org.eclipse.jface.action.Action;

import de.berlios.lummerland.Game;
import de.berlios.lummerland.schedule.model.IControlFlowModel;

/**
 * @author Gregor
 *  
 */
public class RedoAction extends Action {

    private IControlFlowModel controlFlowModel;

    public RedoAction(Game game) {
        
        controlFlowModel = game.getControlFlowModel();
        
        setText("&Redo@Ctrl+Y");
        setToolTipText("Redo the next action");
        
//        try {
//            setImageDescriptor(ImageDescriptor.createFromURL(new URL(
//                    "file:icons/close.gif")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void run() {
        controlFlowModel.redoAction ();
    }
}