/*
 * Created on Jun 18, 2004
 *
 */
package de.berlios.lummerland.model.tree;


/**
 * @author Gregor
 *
 */
/**
 * @author Gregor
 *
 */
public interface ITreeUpdateListener {
    
    /**
     * @param child
     * @param parent
     */
    public void add(ITreeViewable parent, ITreeViewable child);

    /**
     * @param viewable
     * 
     */
    public void remove(ITreeViewable viewable);
}
