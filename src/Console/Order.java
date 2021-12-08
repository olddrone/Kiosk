package Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    private String userName;
    private ArrayList<MyList> list = new ArrayList<MyList>();
    private String request;
    private int total = 0;

    public void addList(MyList list,Manager<Menu> menuList ,Manager<Topping> toppingList, Manager<Vegetable> vegetableList ) {

        this.list.add(list);
        total += list.getPrice(menuList, toppingList,vegetableList);
    }

    public void setUser(User u) {
        userName = u.getName();
        list = new ArrayList<>();
        total = 0;
    }

    public int getPrice(){
        return total;
    }

    public int getTotal(Boolean usePoint,User user) {
        int addPoint = total/100;
        int p = user.getPoint();
        if(usePoint) {
            total -= user.getPoint();
            p=0;
        }
        user.usePoint(p+addPoint);
        return total;
    }



    public void sendRequest(String str){
        request = str;
    }

    public List<MyList> getList() { return list;}

    public Boolean getListEmpty() {
        if (list == null)
            return true;
        else
            return list.isEmpty();
    }

}
