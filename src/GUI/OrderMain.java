package GUI;

import Console.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderMain extends SwingManager implements SwingManageable {
    JButton[] b = new JButton[4];
    JLabel label = new JLabel("default", JLabel.LEFT);
    User user;
    Order order;
    Color c=new Color(8,161,64);

    Map<String, ImageIcon> imageMap;
    JList list;
    JScrollPane scroll;
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> orderstr = new ArrayList<>();

    public OrderMain(JFrame f, ClassControl data,Manageable user, Order order) {
        super(f, data);
        this.user = (User) user;
        this.order = order;
        this.user.setMyList(data.getMyOrderedList());

        create();
        setPos();
        addAction();

    }

    @Override
    public void create() {
        createButton();
        listInit();
        label.setText(user.getName() + "님 환영합니다");
        label.setFont(new Font("맑은 고딕", Font.BOLD, 24));
    }

    @Override
    public void setPos() {
        setButtonPos();
        label.setBounds(10, 10, 300, 50);
        scroll.setBounds(40, 70, 300, 350);
    }

    @Override
    public void addAction() {
        addButtonAction();
        add(scroll);
    }

    // 나만의 메뉴 주문하기, 메뉴 선택, 결제
    public void createButton() {
        String[] kwd = {"마이 메뉴", "메뉴 선택", "결제", "시작화면"};
        for (int i = 0; i < 4; ++i) {
            b[i] = new JButton(kwd[i]);
            b[i].setFont(new Font("맑은 고딕", Font.BOLD, 24));
            b[i].setBackground(c);
            b[i].setForeground(Color.white);
        }
    }

    void listInit() {
        if(!order.getListEmpty()) {
            for (MyList m : order.getList()) {
                nameList.add(m.getChoice(0));
                orderstr.add(m.getChoice(0) + " " + m.getChoice(1)
                        + " " + m.getChoice(2) + " " + m.getChoice(3));
            }
        }
        imageMap = createImageMap(nameList, orderstr);
        list = new JList(orderstr.toArray());
        list.setCellRenderer(new OrderMain.ListRenderer());
        scroll = new JScrollPane(list);
    }


    public class ListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setIcon(imageMap.get(value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
            return label;
        }
    }

    Map<String, ImageIcon> createImageMap(ArrayList<String> list, ArrayList<String> name) {
        Map<String, ImageIcon> map = new HashMap<>();
        try {
            int len=-1;
            if(list.size() == name.size())
                len = list.size();
            for(int i=0;i<len;++i){
                map.put(name.get(i),new ImageIcon("resource//"+list.get(i)+".jpg"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }



    void setButtonPos() {
        for (int i = 0; i < 2; ++i)
            b[i].setBounds(10+(i*190), 440, 170, 50);
        b[2].setBounds(250, 10, 100, 50);
        b[3].setBounds(40, 500, 300, 50);
    }


    void addButtonAction() {
        for (int i = 0; i < 4; ++i) {
            add(b[i]);
            b[i].addActionListener(this);
        }
        add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b[0]) {
            if (user.getMyList().isEmpty()) {
                JOptionPane.showMessageDialog(null, "나만의 메뉴 항목이 없습니다",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
            } else
                changerPanel(new ChoiceMyList(frame, data, user, order));
               // changerPanel(new ChoiceMyList(frame, data, user, order));
        }
        else if (e.getSource() == b[1]) {
            changerPanel(new ChoiceMenu(frame,data, user, order));
        }
        else if (e.getSource() == b[2]) {
            if (order.getListEmpty()) {
                JOptionPane.showMessageDialog(null, "주문하신 상품이 없습니다",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
            } else
                changerPanel(new Pay(frame, data, user, order));
        }
        else if (e.getSource() == b[3]) {
            changerPanel(new Title(frame, data));
        }
    }
}
