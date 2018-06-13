/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raykor;

import xml.XmlBuilder;
import xml.XmlException;
import xml.Xmlnode;

/**
 *
 * @author ROOT
 */
public class Raykor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        XmlBuilder xmlobj= new XmlBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<web-app >\n" +
"    <session-config>\n" +
"        <session-timeout>\n" +
"            30\n" +
"        </session-timeout>\n" +
"    </session-config>\n" +
"    <welcome-file-list>\n" +
"        <welcome-file>index.jsp</welcome-file>\n" +
"    </welcome-file-list>\n" +
"</web-app>\n" +
"");
        try{
            
        Xmlnode root = xmlobj.parseXML();
        root.formatPrint();
        }catch(XmlException e){
            System.out.println("syntax error:" + e.toString());
        }catch(Exception e){
            System.out.println("syntax error occured: tags mismatched or not closed properly");
        }
    }
}
