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
public class UndoAction extends Action {

    private IControlFlowModel controlFlowModel;

    public UndoAction(Game game) {
        
        controlFlowModel = game.getControlFlowModel();
        
        setText("&Undo@Ctrl+Z");
        setToolTipText("Undo the last action");
        
//        try {
//            setImageDescriptor(ImageDescriptor.createFromURL(new URL(
//                    "file:icons/close.gif")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void run() {
        controlFlowModel.undoAction ();
    }
}