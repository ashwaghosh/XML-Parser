/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author ROOT
 */
public class Xmlnode {

    public static int tabindex = 0;
    String key;
    String value;
    HashMap<String, String> attribute;
    ArrayList<Xmlnode> items = new ArrayList<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HashMap<String, String> getAttribute() {
        return attribute;
    }

    public void setAttribute(HashMap<String, String> attribute) {
        this.attribute = attribute;
    }

    public ArrayList<Xmlnode> getItems() {
        return items;
    }

    public void setItems(ArrayList<Xmlnode> items) {
        this.items = items;
    }

    public void addLeaf(Xmlnode node) {
        items.add(node);
    }
    //    public void printNode(){
    //        System.out.println("key ="+key);
    //        System.out.println("value ="+value);
    //        for(Xmlnode node : items){
    //            System.out.println("\t "+ key+ " = " +node.key );
    //        }
    //    }

    @Override
    public String toString() {
        return "tagname : " + key + " , attributes: " + attribute.toString() + " value : " + value;
    }

    public void print() {
        System.out.print("<" + key);
        if (attribute != null) {
            System.out.print(attribute);
        }
        System.out.println(">");
        if (value == null) {
            for (Xmlnode x : items) {
                x.print();
            }
        } else {
            System.out.println(value);
        }
        System.out.println("</" + key + ">");
    }

    public void formatPrint() {
        for (int index = 0; index < Xmlnode.tabindex; index++) {
            System.out.print("  ");
        }
        Xmlnode.tabindex++;
        System.out.print("<" + key);
        if (attribute != null) {
            Set<String> keys = attribute.keySet();
            for(String key: keys){
                System.out.print( " "+key +"="+attribute.get(key) );
            }
            //            System.out.print(attribute);
        }
        System.out.println(">");
        if (value == null) {

            for (Xmlnode x : items) {
                x.formatPrint();
            }
        } else {
            for (int index = 0; index < Xmlnode.tabindex; index++) {
                System.out.print("  ");
            }
            System.out.println(value);
        }
        Xmlnode.tabindex--;
        for (int index = 0; index < Xmlnode.tabindex; index++) {
            System.out.print("  ");
        }
        System.out.println("</" + key + ">");
    }
}
