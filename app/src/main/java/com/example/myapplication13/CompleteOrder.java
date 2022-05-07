package com.example.myapplication13;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("CompleteOrder")
public class CompleteOrder extends ParseObject {
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_Price = "itemPrice";
    public static final String KEY_ITEMNAME = "itemName";
    public static final String KEY_ADDRESS = "address";

    public String getKeyQuantityuay(){
        return getString(KEY_QUANTITY);
    }
    public void setKeyQuantity(String quantity){
        put(KEY_QUANTITY, quantity);
    }

    public String getKEY_Price(){
        return getString(KEY_Price);
    }
    public void setKEY_Price(String Price){
        put(KEY_Price, Price);
    }

    public String getKeyItemname(){
        return getString(KEY_ITEMNAME);
    }
    public void setKeyItemname(String itemName){
        put(KEY_ITEMNAME, itemName);
    }

    public String getKeyAddress(){
        return getString(KEY_ADDRESS);
    }
    public void setKeyAddress(String address){
        put(KEY_ADDRESS, address);
    }
}
