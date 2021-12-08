package GUI;

import Console.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pay extends SwingManager implements SwingManageable {
    User user;
    Order order;
    Map<String, ImageIcon> imageMap;
    JList list;
    JScrollPane scroll;

    JLabel total = new JLabel("default", JLabel.RIGHT);
    JButton[] b = new JButton[3];
    Color c = new Color(8, 161, 64);
    boolean usePoint = false;
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> orderstr = new ArrayList<>();

    public Pay(JFrame f, ClassControl data, Manageable user, Order order) {
        super(f, data);
        this.user = (User) user;
        this.order = order;

        create();
        setPos();
        addAction();
    }


    @Override
    public void create() {
        createButton();
        listInit();
        total.setText("총 " + order.getPrice() + "원");
        total.setFont(new Font("맑은 고딕", Font.BOLD, 24));
    }

    void createButton() {
        String[] kwd = {"포인트사용", "돌아가기", "다음"};
        for (int i = 0; i < 3; ++i) {
            b[i] = new JButton(kwd[i]);
            b[i].setBackground(c);
            b[i].setForeground(Color.white);
        }
        b[0].setBackground(Color.darkGray);
        b[0].setFont(new Font("맑은 고딕", Font.BOLD, 22));
        for (int i = 1; i < 3; ++i)
            b[i].setFont(new Font("맑은 고딕", Font.BOLD, 24));

    }

    void listInit() {
        for (MyList m : order.getList()) {
            nameList.add(m.getChoice(0));
            orderstr.add(m.getChoice(0) + " " + m.getChoice(1)
                    + " " + m.getChoice(2) + " " + m.getChoice(3));
        }
        imageMap = createImageMap(nameList, orderstr);
        list = new JList(orderstr.toArray());
        list.setCellRenderer(new ListRenderer());
        scroll = new JScrollPane(list);
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

    @Override
    public void setPos() {
        setButtonPos();
        total.setBounds(150, 430, 200, 50);
        scroll.setBounds(40, 10, 300, 400);
    }

    void setButtonPos() {
        b[0].setBounds(30, 430, 150, 50);
        b[1].setBounds(30, 500, 150, 50);
        b[2].setBounds(210, 500, 150, 50);
    }

    @Override
    public void addAction() {
        addButtonAction();
        add(total);
        add(scroll);
    }

    void addButtonAction() {
        for (JButton button : b) {
            add(button);
            button.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b[0]) {
            int ret = JOptionPane.showConfirmDialog(null, "현재 보유 포인트 " + user.getPoint(),
                    "Message", JOptionPane.YES_NO_OPTION);
            if (ret == JOptionPane.YES_OPTION) {
                total.setText("총 " + (order.getPrice() - user.getPoint()) + "원");
                b[0].setBackground(c);
                usePoint = true;
            } else {
                total.setText("총 " + order.getPrice() + "원");
                b[0].setBackground(Color.darkGray);
                usePoint = false;
            }
        } else if (e.getSource() == b[1]) {
            changerPanel(new OrderMain(frame, data, user, order));
        } else if (e.getSource() == b[2]) {
            changerPanel(new Request(frame, data, user, order, usePoint));
        }
    }
}

