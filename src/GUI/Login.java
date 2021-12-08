package GUI;

import Console.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Login extends SwingManager implements SwingManageable {
    JLabel label = new JLabel("전화번호", JLabel.CENTER);
    String phone = new String();
    ArrayList<JButton> key = new ArrayList<>();
    Color c=new Color(8,161,64);

    public Login(JFrame f, ClassControl data) {
        super(f, data);
        create();
        setPos();
        addAction();
    }

    @Override
    public void create() {
        createButton();
        label.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        label.setBorder(new BevelBorder(BevelBorder.RAISED));
        label.setOpaque(true);
        label.setBackground(c);
        label.setForeground(Color.white);
    }

    @Override
    public void setPos() {
        setButtonPos();
        label.setBounds(40, 40, 300, 100);

    }

    @Override
    public void addAction() {
        addButtonAction();
        add(label);
    }

    public void createButton() {
        for (int i = 1; i <= 9; ++i)
            key.add(new JButton("" + (i)));
        String[] str = {"*", "0", "#", "이전", "다음", "<x]"};
        for (int i = 0; i < 6; ++i)
            key.add(new JButton(str[i]));
    }

    void setButtonPos() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 3; ++j) {
                key.get((i * 3) + j).setBounds(25 + (j * 120), 200 + (i * 60), 100, 50);
                key.get((i * 3) + j).setBackground(c);
                key.get((i * 3) + j).setForeground(Color.white);
            }
        }
        key.get(12).setBounds(30, 500, 150, 50);
        key.get(12).setBackground(c);
        key.get(12).setForeground(Color.white);
        key.get(13).setBounds(210, 500, 150, 50);
        key.get(13).setBackground(c);
        key.get(13).setForeground(Color.white);
        key.get(14).setBounds(210, 440, 150, 50);
        key.get(14).setBackground(c);
        key.get(14).setForeground(Color.white);
    }


    void addButtonAction() {
        for (int i = 0; i < 15; ++i) {
            key.get(i).setFont(new Font("맑은 고딕", Font.BOLD, 24));
            add(key.get(i));
            key.get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == key.get(12)) {
            changerPanel(new Title(frame, data));

        } else if (e.getSource() == key.get(13)) {
            // phone으로 로그인
            if (data.getUserList().login(phone)) {
                Manageable user = data.getUserList().find(phone);
                Order order = new Order();
                order.setUser((User)user);
                changerPanel(new OrderMain(frame, data,user,order));
            } else {
                JOptionPane.showMessageDialog(null, "존재하지 않는 회원입니다",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
                changerPanel(new Register(frame, data));
            }
        } else if (e.getSource() == key.get(14)) {
            if (!phone.isEmpty())
                phone = phone.substring(0, phone.length() - 1);
        } else if (phone.isEmpty())
            phone = e.getActionCommand();
        else
            phone += e.getActionCommand();
        label.setText(phone);
    }
}

