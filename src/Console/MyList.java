package Console;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MyList implements Manageable{
    private String name;
    final int idx = 4;
    private String[] choice = new String[idx];
    File f = new File("txtfile//myList.txt");
    FileWriter fw = null;

    @Override
    public void read(Scanner scan) {
        name = scan.next();
        for (int i = 0; i < idx; ++i) {
            choice[i] = scan.next();
        }
    }

    public void filewrite(User user, Menu menu, Option option) {
        try {
            fw = new FileWriter(f, true);
            fw.write("\n");
            fw.write(user.name);
            fw.write(" ");
            fw.write(menu.getName());
            fw.write(" ");
            fw.write(option.getTopping().getName());
            fw.write(" ");
            fw.write(option.getVegetable().getName());
            fw.write(" ");
            fw.write(option.getSource().getName());
            fw.flush();
        }
        catch(IOException e){
            System.out.println("파일 입력 오류");
            System.exit(0);
        }
    }

    @Override
    public void print() {
        for(int i=0;i<4;++i){
            System.out.printf("%-10s ", choice[i]);
        }
        System.out.printf("\n");
    }

    @Override
    public boolean matches(String kwd) {
        if(name.equals(kwd)) return true;
        return false;
    }


    public void setChoice(Menu menu, Option option) {
        choice[0] = menu.getName();
        choice[1] = option.getTopping().getName();
        choice[2] = option.getVegetable().getName();
        choice[3] = option.getSource().getName();
    }

    public void setChoice(String menu, String topping, String vegetable, String source) {
        choice[0] = menu;
        choice[1] = topping;
        choice[2] = vegetable;
        choice[3] = source;
    }

    public int getPrice(Manager<Menu> menu, Manager<Topping> topping, Manager<Vegetable> vegetable){
        int price = 0;
        for (Menu m : menu.mList) {
            if (m.matches(choice[0]))
                price += m.getPrice();
        }
        for(Topping t : topping.mList){
            if(t.matches(choice[1]))
                price+=t.getPrice();
        }
        for(Vegetable v : vegetable.mList) {
            if (v.matches(choice[2]))
                price += v.getPrice();
        }
        return price;
    }

    public String getName() {
        return name;
    }

    public String getChoice(int idx) {
        return choice[idx];
    }

}

