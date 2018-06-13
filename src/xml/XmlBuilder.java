/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author ROOT
 */
public class XmlBuilder {

    String xmlheader;
    String xmlstring;
    Xmlnode root;
    Stack<Xmlnode> keydata = new Stack<>();

    public XmlBuilder(String xmlstring) {
        this.xmlstring = xmlstring;
    }
    //main engine to create object from the xml string
    public Xmlnode parseXML()throws Exception{
        xmlstring = xmlstring.trim();
        if (xmlstring.startsWith("<?")) {
            xmlheader = xmlstring.substring(0, xmlstring.indexOf("?>") + 2);
            xmlstring = xmlstring.substring(xmlstring.indexOf("?>") + 2, xmlstring.length());
        }
        xmlstring = xmlstring.trim();


        while (true) {
            Xmlnode node = null;
            if (xmlstring.substring(0, 1).equals("<") && !xmlstring.substring(0, 2).equals("</")) {
                String data = "";
                String tagname = "";
                HashMap<String, String> hm = new HashMap<>();
                node = new Xmlnode();

                data = xmlstring.substring(1, xmlstring.indexOf(">")).trim();
                xmlstring = xmlstring.substring(xmlstring.indexOf(">") + 1, xmlstring.length()).trim();
                try{
                    
                if (data.trim().contains(" ")) { //contains attributes
                    String[] contains = data.split(" ");
                    tagname = contains[0];
                    for (int index = 1; index < contains.length; index++) {
                        String[] split = contains[index].split("=");
                        String key = split[0];
                        String value = split[1];
                        hm.put(key, value);
                    }
                    node.setKey(data.substring(0, data.indexOf(" ")));
                    node.setAttribute(hm);
                } else {//contains only tagname
                    node.setKey(data);
                    node.setAttribute(null);
                }
                }catch(Exception ex){
                    throw new XmlException(data+" attributes syntax error occured (remove spaces between attributes eg: id=1 ) ");
                }

                if (keydata.isEmpty()) {
                    keydata.push(node);
                    root = node;
                } else {
                    keydata.peek().addLeaf(node);
                    keydata.push(node);
                }

            }
            if (xmlstring.substring(0, 2).equals("</")) {
                xmlstring = xmlstring.substring(2, xmlstring.length()).trim();
                if (xmlstring.startsWith(keydata.peek().getKey())) {
                    xmlstring = xmlstring.substring(xmlstring.indexOf('>') + 1, xmlstring.length()).trim();
                    keydata.pop();
                    if (keydata.isEmpty()) {
                        return root;
                    }
                }
            } else if (xmlstring.substring(0, 1).equals("<")) {
                //has inner tag
                continue;
            } else {
                node.setValue(xmlstring.substring(0, xmlstring.indexOf('<')).trim());
                xmlstring = xmlstring.substring(xmlstring.indexOf('<'), xmlstring.length()).trim();
                if (xmlstring.startsWith("</")) {
                    xmlstring = xmlstring.substring(2, xmlstring.length()).trim();
//                    if (xmlstring.startsWith(keydata.peek().getKey())) {
                    if (xmlstring.substring(0,xmlstring.indexOf(">")).equals(keydata.peek().getKey())) {
                        xmlstring = xmlstring.substring(xmlstring.indexOf('>') + 1, xmlstring.length()).trim();
                        Xmlnode rooter = keydata.pop();
                        if (keydata.isEmpty()) {
                            return rooter;
                        }
                    }
                }
            }
        }
    }
}
