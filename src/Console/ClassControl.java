package Console;

public class ClassControl {
    UserManager<User> userList = new UserManager<>();
    Manager<Menu> menuList = new Manager<>();
    Manager<Topping> toppingList = new Manager<>();
    Manager<Vegetable> vegetableList = new Manager<>();
    Manager<Source> sourceList = new Manager<>();
    Manager<MyList> myOrderedList = new Manager<>();

    public ClassControl() {
        userList.readAll("txtfile//user.txt", new Factory<User>() {
            @Override
            public User create() { return new User(); }
        });
        menuList.readAll("txtfile//menu.txt", new Factory<Menu>() {
            @Override
            public Menu create() {
                return new Menu();
            }
        });
        toppingList.readAll("txtfile//topping.txt", new Factory<Topping>() {
            @Override
            public Topping create() {
                return new Topping();
            }
        });
        vegetableList.readAll("txtfile//vegetable.txt", new Factory<Vegetable>() {
            @Override
            public Vegetable create() {
                return new Vegetable();
            }
        });
        sourceList.readAll("txtfile//source.txt", new Factory<Source>() {
            @Override
            public Source create() {
                return new Source();
            }
        });
        myOrderedList.readAll("txtfile//myList.txt", new Factory<MyList>() {
            @Override
            public MyList create() {
                return new MyList();
            }
        });
    }

    public Manager<Menu> getMenuList() {
        return menuList;
    }

    public Manager<MyList> getMyOrderedList() {
        return myOrderedList;
    }

    public Manager<Source> getSourceList() {
        return sourceList;
    }

    public Manager<Topping> getToppingList() {
        return toppingList;
    }

    public Manager<Vegetable> getVegetableList() {
        return vegetableList;
    }

    public UserManager<User> getUserList() {
        return userList;
    }

}
