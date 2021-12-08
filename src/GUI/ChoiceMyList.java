package GUI;

import Console.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChoiceMyList extends SwingManager implements SwingManageable{
    User user;
    Order order;
    JButton b = new JButton("돌아가기");
    Color c = new Color(8, 161, 64);
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> orderstr = new ArrayList<>();

    Map<String, ImageIcon> imageMap;
    JList list;
    JScrollPane scroll;

    public ChoiceMyList(JFrame f, ClassControl data, Manageable user, Order order) {
        super(f, data);

        this.user = (User) user;
        this.order = order;

        create();
        setPos();
        addAction();
    }


    @Override
    public void create() {
        listInit();

        b.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        b.setBackground(c);
        b.setForeground(Color.white);
    }


    void listInit(){
        for(MyList m : user.getMyList()) {
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
        b.setBounds(40, 500, 300, 50);
        scroll.setBounds(40, 10, 300, 400);
    }


    @Override
    public void addAction() {
        add(scroll);
        add(b);
        b.addActionListener(this);
        list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String str = "" + list.getSelectedValuesList().toString().replace("[", "").replace("]", "");
                String[] get = str.split(" ");
                MyList list = new MyList();

                list.setChoice(get[0], get[1], get[2], get[3]);
                order.addList(list, data.getMenuList(), data.getToppingList(), data.getVegetableList());
                JOptionPane.showMessageDialog(null, "장바구니에 추가되었습니다",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
                changerPanel(new OrderMain(frame, data, user, order));

            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            changerPanel(new OrderMain(frame,data, user,order));
        }
    }
}
