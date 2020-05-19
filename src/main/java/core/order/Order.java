package core.order;

import java.util.HashMap;


public class Order {
    private int user_id;
    private HashMap<String, String> orderMap;

    public Order(int user_id){
        orderMap = new HashMap();
        orderMap.put("user_id", String.valueOf(user_id));
        orderMap.put("name", "");
        orderMap.put("order", "");
        orderMap.put("phone", "");
        orderMap.put("street", "");
        orderMap.put("house", "");
        orderMap.put("apartment", "");
    }
     public void initorder(){

     }
}
