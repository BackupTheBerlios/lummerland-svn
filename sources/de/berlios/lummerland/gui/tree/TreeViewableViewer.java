/*
 * Created on Jun 17, 2004
 *
 */
package de.berlios.lummerland.gui.tree;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;

import de.berlios.lummerland.model.tree.ITreeViewable;

/**
 * @author Gregor
 *  
 */
public class TreeViewableViewer extends TreeViewer {

    /**
     * @param parent
     * @param gameSchedule
     */
    public TreeViewableViewer(Composite parent, ITreeViewable root) {
        super(parent);

        setContentProvider(new TreeViewableContentProvider());

        setLabelProvider(new LabelProvider() {
            public String getText(Object element) {

                return ((ITreeViewable) element).getTreeModel().getLabel();

            }
        });

        setInput(root);
        expandAll();

        RowData layoutData = new RowData(200, 500);
        getControl().setLayoutData(layoutData);
    }

}