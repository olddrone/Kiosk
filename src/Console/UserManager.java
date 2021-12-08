package Console;

import java.util.Scanner;

public class UserManager <T extends Manageable> extends Manager{
    User u = null;

    public Boolean login(String phone) {
        u = (User) find(phone);
        return (u == null) ? false : true;
    }

    public boolean register(String name, String phone) {
        u = (User) find(phone);
        if (u == null) {
            u = new User();
            u.setData(name, phone);
            add(u);
            u.filewrite(name, phone);
            return true;
        }
        return false;
    }

}