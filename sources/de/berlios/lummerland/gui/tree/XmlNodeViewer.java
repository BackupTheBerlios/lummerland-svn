/*
 * Created on Jun 17, 2004
 *
 */
package de.berlios.lummerland.gui.tree;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Attr;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Gregor
 *
 */
public class XmlNodeViewer extends TreeViewer {

    /**
     * @param parent
     * @param gameSchedule
     */
    public XmlNodeViewer(Composite parent, Node root) {
        super(parent);
        
        setContentProvider(new XmlNodeContentProvider ());
        
        setLabelProvider(new LabelProvider () {
            public String getText(Object obj) {
                
                if (obj instanceof Element) {
                    Element element = (Element) obj;
                    return element.getTagName();
                } 
                if (obj instanceof Attr) {
                    Attr attribute = (Attr) obj;
                    return "Attribute: " + attribute.getName() + " = " + attribute.getValue();
                }
                if (obj instanceof CharacterData) {
                    CharacterData text = (CharacterData) obj;
                    return text.getData().trim();
                }
                
                return ((Node)obj).getNodeValue();
                
            }   
        });
        
        setInput(root);
        expandAll();
        
		RowData layoutData = new RowData(200,500);
		getControl().setLayoutData(layoutData);
    }
    

}
