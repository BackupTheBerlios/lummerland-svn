/*
 * Created on Jun 17, 2004
 *
 */
package de.berlios.lummerland.gui.tree;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.w3c.dom.CharacterData;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Gregor
 *  
 */
public class XmlNodeContentProvider implements ITreeContentProvider {

    public XmlNodeContentProvider() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement) {
        if (!hasChildren(parentElement))
            return null;

        Node node = ((Node) parentElement);

        NodeList nodeList = node.getChildNodes();

        NamedNodeMap attributes = node.getAttributes();

        ArrayList children = new ArrayList ();
        
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                children.add (attributes.item(i));
            }
        }

        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node child = nodeList.item(i);
                
                if (child instanceof CharacterData) {
                    CharacterData data = (CharacterData) child;
                    if (data.getData().trim ().length() == 0) continue;
                }
                
                children.add (child);
            }
        }

        return children.toArray ();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
        return ((Node) element).getParentNode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
        Node node = ((Node) element); 
        
        return node.hasAttributes() || node.hasChildNodes();
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
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

}