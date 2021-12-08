package GUI;

import Console.*;
import Console.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ChoiceVegetable extends SwingManager implements SwingManageable {
    User user;
    Order order;
    Menu menu;
    Topping topping;
    ArrayList<JButton> vegetableList = new ArrayList<>();
    JButton b = new JButton("돌아가기");
    JLabel label = new JLabel("추가 야채선택", JLabel.LEFT);
    Color c=new Color(8,161,64);

    public ChoiceVegetable(JFrame f, ClassControl data, Manageable user, Order order,Menu menu, Topping topping) {
        super(f, data);
        this.user = (User) user;
        this.order = order;
        this.menu = menu;
        this.topping = topping;
        create();
        setPos();
        addAction();
    }

    @Override
    public void create() {
        createButton();
        label.setFont(new Font("맑은 고딕", Font.BOLD, 24));
    }


    @Override
    public void setPos() {
        setButtonPos();
        b.setBounds(40, 500, 300, 50);
        label.setBounds(10, 10, 200, 50);
    }

    @Override
    public void addAction() {
        addButtonAction();
        add(label);
        add(b);
        b.addActionListener(this);
    }

    void createButton() {
        for (Vegetable m : data.getVegetableList().getmList()) {
            String str = m.getName();
            JButton b = new JButton(str);
            if (str.length() > 5)
                splitText(b,str);
            b.setFont(new Font("맑은 고딕", Font.BOLD, 16));
            b.setBackground(c);
            b.setForeground(Color.white);
            vegetableList.add(b);
        }
        b.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        b.setBackground(c);
        b.setForeground(Color.white);
    }

    void splitText(JButton b, String str){
        int index = str.indexOf(".");
        String subStr1 = str.substring(0,index);
        String subStr2 = str.substring(index+1,str.length());
        b.setText("<html>" + "<center>" +
                subStr1 + "<br>" + subStr2 +
                "</center>" + "</html>");
    }

    void setButtonPos() {
        int idx = 0;
        int xpos = 120;
        int ypos = 100;
        for(JButton m : vegetableList){
            if(idx == 3) { idx = 0; ypos += 100; }
            m.setBounds(25 + (idx * xpos),10 + (ypos),100,80);
            idx++;
        }
    }

    void addButtonAction() {
        for (JButton m : vegetableList) {
            add(m);
            m.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            changerPanel(new OrderMain(frame,data, user,order));
        } else {
            int idx = vegetableList.indexOf(e.getSource());
            Vegetable vegetable = data.getVegetableList().getList(idx);
            changerPanel(new ChoiceSource(frame, data, user,order, menu, topping, vegetable));
        }
    }
}