/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

/**
 *
 * @author ROOT
 */
public class XmlException extends Exception{
    String message;

    public XmlException(String message) {
        this.message=message;
    }

    @Override
    public String toString() {
        return message;
    }
    
    
    
}
