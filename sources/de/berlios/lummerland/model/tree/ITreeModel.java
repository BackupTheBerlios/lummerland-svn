/*
 * Created on Jun 18, 2004
 *
 */
package de.berlios.lummerland.model.tree;

import java.util.List;

/**
 * @author Gregor
 *
 */
public interface ITreeModel {

    /**
     * @return
     */
    public List getChildren();

    /**
     * @return
     */
    public ITreeViewable getParent();

    /**
     * @return
     */
    public boolean hasChildren();
    
    public void addUpdateListener (ITreeUpdateListener l);
    public void removeUpdateListener (ITreeUpdateListener l);
    
    public void fireAddChild(ITreeViewable child);
    public void fireRemove ();

    /**
     * @return
     */
    public String getLabel();

}
