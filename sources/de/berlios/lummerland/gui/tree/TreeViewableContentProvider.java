/*
 * Created on Jun 17, 2004
 *
 */
package de.berlios.lummerland.gui.tree;

import java.util.Iterator;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

import de.berlios.lummerland.model.tree.ITreeModel;
import de.berlios.lummerland.model.tree.ITreeUpdateListener;
import de.berlios.lummerland.model.tree.ITreeViewable;

/**
 * @author Gregor
 *  
 */
public class TreeViewableContentProvider implements ITreeContentProvider,
        ITreeUpdateListener {

    private TreeViewer viewer;

    public TreeViewableContentProvider() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement) {
        if (!hasChildren(parentElement))
            return null;

        return getTreeModel(parentElement).getChildren().toArray();
    }

    private ITreeModel getTreeModel(Object element) {
        return ((ITreeViewable) element).getTreeModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
        return getTreeModel(element).getParent();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {

        return getTreeModel(element).hasChildren();
    }

    public Object[] getElements(Object inputElement) {

        return getChildren(inputElement);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
    }

    /*
     * @see IContentProvider#inputChanged(Viewer, Object, Object)
     */
    /**
     * Notifies this content provider that the given viewer's input has been
     * switched to a different element.
     * <p>
     * A typical use for this method is registering the content provider as a
     * listener to changes on the new input (using model-specific means), and
     * deregistering the viewer from the old input. In response to these change
     * notifications, the content provider propagates the changes to the viewer.
     * </p>
     * 
     * @param viewer
     *            the viewer
     * @param oldInput
     *            the old input element, or <code>null</code> if the viewer
     *            did not previously have an input
     * @param newInput
     *            the new input element, or <code>null</code> if the viewer
     *            does not have an input
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        this.viewer = (TreeViewer) viewer;

        if (oldInput != null) {
            removeListenerFrom((ITreeViewable) oldInput);
        }
        if (newInput != null) {
            addListenerTo((ITreeViewable) newInput);
        }
    }

    /**
     * Because the domain model does not have a richer listener model,
     * recursively remove this listener from each child box of the given box.
     */
    protected void removeListenerFrom(ITreeViewable viewable) {
        ITreeModel model = viewable.getTreeModel();

        model.removeUpdateListener(this);

        if (model.hasChildren()) {
            for (Iterator iter = model.getChildren().iterator(); iter.hasNext();) {
                ITreeViewable v = (ITreeViewable) iter.next();
                removeListenerFrom(v);
            }
        }

    }

    /**
     * Because the domain model does not have a richer listener model,
     * recursively add this listener to each child box of the given box.
     */
    protected void addListenerTo(ITreeViewable viewable) {
        ITreeModel model = viewable.getTreeModel();

        model.addUpdateListener(this);

        if (model.hasChildren()) {
            for (Iterator iter = model.getChildren().iterator(); iter.hasNext();) {
                ITreeViewable v = (ITreeViewable) iter.next();
                addListenerTo(v);
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.model.ITreeUpdateListener#childAdded(de.berlios.lummerland.model.ITreeViewable)
     */
    public void add(final ITreeViewable parent, ITreeViewable child) {
        addListenerTo(child);
        refresh(parent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.berlios.lummerland.model.ITreeUpdateListener#remove(de.berlios.lummerland.model.ITreeViewable)
     */
    public void remove(ITreeViewable viewable) {
        removeListenerFrom(viewable);
        refresh(viewable);
    }

    /**
     * @param parent
     */
    private void refresh(final ITreeViewable parent) {
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                viewer.refresh(parent, false);
            }
        });
    }

}