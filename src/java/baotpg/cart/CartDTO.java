/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class CartDTO implements Serializable {

    private Map<String, Integer> list;

    public Map<String, Integer> getList() {
        return list;
    }

    public void setList(Map<String, Integer> list) {
        this.list = list;
    }

    public void addItemCart(String idBook) {
        if (this.list == null) {
            list = new HashMap<>();
        }
        int quantity = 1;
        Set<String> listKeys = list.keySet();
        for (String key : listKeys) {
            if (key.equals(idBook)) {
                quantity = this.list.get(key) + 1;
                break;
            }
        }
        this.list.put(idBook, quantity);
    }

    public void removeItemCart(String idBook) {
        int quanlity;
        Iterator keys = this.list.entrySet().iterator();
        while (keys.hasNext()) {
            Entry item = (Entry) keys.next();
            if (item.getKey().equals(idBook)) {
                if ((Integer) item.getValue() > 1) {
                    quanlity = (Integer) item.getValue() - 1;
                    item.setValue(quanlity);
                } else {
                    keys.remove();
                }
            }
        }
    }

}
