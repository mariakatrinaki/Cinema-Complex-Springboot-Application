package gr.mariakapa.cinema.RequestResponse;

import gr.mariakapa.cinema.Entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderResponse {




    private String name;

    private List<Product> products = new ArrayList<>();

   private int quantity;


   private boolean check;


   public boolean isCheckTrue(){
      return this.check = true;

    }


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {

        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
