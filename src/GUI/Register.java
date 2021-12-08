package GUI;

import Console.ClassControl;
import Console.Manageable;
import Console.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Register extends SwingManager implements SwingManageable{
    JButton[] b = new JButton[2];
    JTextField[] info = new JTextField[2];
    JLabel[] label = new JLabel[2];
    String name = null;
    String phone = null;
    Color c=new Color(8,161,64);

    public Register(JFrame f, ClassControl data) {
        super(f, data);
        create();
        setPos();
        addAction();
    }

    @Override
    public void create() {
        createButton();
        String[] text = {"이름", "전화번호 (-없이)"};
        for (int i = 0; i < 2; ++i) {
            info[i] = new JTextField(20);
            info[i].setFont(new Font("맑은 고딕", Font.BOLD, 24));


            label[i] = new JLabel("default", JLabel.CENTER);
            label[i].setText(text[i]);
            label[i].setFont(new Font("맑은 고딕", Font.BOLD, 24));
        }

    }

    @Override
    public void setPos() {
        setButtonPos();
        for (int i = 0; i < 2; ++i) {
            info[i].setBounds(40, 100 + (150 * i), 300, 50);
            label[i].setBounds(40, 40 + (i * 160), 300, 50);
        }
    }

    @Override
    public void addAction() {
        addButtonAction();
    }


    public void createButton() {
        String[] kwd = {"이전", "가입"};
        for (int i = 0; i < 2; ++i) {
            b[i] = new JButton(kwd[i]);
            b[i].setFont(new Font("맑은 고딕", Font.BOLD, 24));
            b[i].setBackground(c);
            b[i].setForeground(Color.white);
        }
    }

    public void setButtonPos() {
        for (int i = 0; i < 2; ++i)
            b[i].setBounds(30 + (i * 180), 500, 150, 50);
    }

    public void addButtonAction() {
        for (int i = 0; i < 2; ++i) {
            add(info[i]);
            add(label[i]);
            add(b[i]);
            b[i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b[0]) {
            changerPanel(new Title(frame, data));
        } else if (e.getSource() == b[1]) {
            name = info[0].getText();
            phone = info[1].getText();
            if (data.getUserList().register(name, phone)) {
                Manageable user = data.getUserList().find(phone);
                Order order = new Order();
                changerPanel(new OrderMain(frame, data, user,order));
            } else {
                JOptionPane.showMessageDialog(null, "이미 존재하는 계정입니다",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
                changerPanel(new Title(frame, data));
            }
        }
    }
}
