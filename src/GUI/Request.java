package GUI;

import Console.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Request extends SwingManager implements SwingManageable {
    User user;
    Order order;
    boolean usePoint;
    JButton[] b = new JButton[2];
    Color c = new Color(8, 161, 64);

    JTextArea text = new JTextArea();
    JLabel label = new JLabel("요청사항", JLabel.CENTER);
    JLabel label2 = new JLabel("default", JLabel.RIGHT);

    public Request(JFrame f, ClassControl data, Manageable user, Order order, Boolean usePoint) {
        super(f, data);
        this.user = (User) user;
        this.order = order;
        this.usePoint = usePoint;

        create();
        setPos();
        addAction();
    }

    @Override
    public void create() {
        createButton();

        label.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        if (usePoint)
            label2.setText("총 " + (order.getPrice() - user.getPoint()) + "원");
        else
            label2.setText("총 " + order.getPrice() + "원");
        label2.setFont(new Font("맑은 고딕", Font.BOLD, 24));

        text.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        text.setForeground(Color.white);
        text.setBackground(Color.darkGray);
        text.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY,Color.GRAY));
        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (e.isControlDown()) { // Ctrl + Enter = 줄 바꿈
                        text.append(System.lineSeparator());
                    } else { // Enter = 전송

                        // stop event
                        e.consume();
                    }

                }
            }
            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
    }

    @Override
    public void setPos() {
        setButtonPos();
        label.setBounds(100, 10, 200, 50);
        label2.setBounds(150, 430, 200, 50);

        text.setBounds(50,80,300,300);
    }

    @Override
    public void addAction() {
        addButtonAction();
        add(label);
        add(label2);
        add(text);
    }

    void createButton() {
        String[] kwd = {"돌아가기", "결제하기"};
        for (int i = 0; i < 2; ++i) {
            b[i] = new JButton(kwd[i]);
            b[i].setBackground(c);
            b[i].setForeground(Color.white);
            b[i].setFont(new Font("맑은 고딕", Font.BOLD, 24));
        }

    }

    void setButtonPos() {
        b[0].setBounds(30, 500, 150, 50);
        b[1].setBounds(210, 500, 150, 50);
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
            changerPanel(new Pay(frame, data, user, order));
        } else if (e.getSource() == b[1]) {
            order.sendRequest(text.getText());
            JOptionPane.showMessageDialog(null, "총" + order.getTotal(usePoint, user) + "원 결제되었습니다",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
            changerPanel(new Title(frame, data));
        }
    }
}

