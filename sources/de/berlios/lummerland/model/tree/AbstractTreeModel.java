/*
 * Created on Jun 18, 2004
 *
 */
package de.berlios.lummerland.model.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import de.berlios.lummerland.Lummerland;

/**
 * @author Gregor
 *
 */
public abstract class AbstractTreeModel implements ITreeModel {

    private Collection updateListeners = new ArrayList();
    
    private ITreeViewable viewable;
    
    public AbstractTreeModel(ITreeViewable viewable) {
        this.viewable = viewable;
    }
    public void addUpdateListener(ITreeUpdateListener l) {
        updateListeners.add(l);
        Lummerland.getLogger().debug(
                "ITreeUpdateListerner added to " );
    }

    public void removeUpdateListener(ITreeUpdateListener l) {
        updateListeners.remove(l);
    }

    public void fireAddChild(ITreeViewable child) {
        for (Iterator iter = updateListeners.iterator(); iter.hasNext();) {
            ITreeUpdateListener l = (ITreeUpdateListener) iter.next();
            l.add(viewable, child);
        }
    }

    public void fireRemove() {
        for (Iterator iter = updateListeners.iterator(); iter.hasNext();) {
            ITreeUpdateListener l = (ITreeUpdateListener) iter.next();
            l.remove(viewable);
        }
    }

}
