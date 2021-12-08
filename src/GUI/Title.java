package GUI;

import Console.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Title extends SwingManager implements SwingManageable {
    JButton[] b = new JButton[4];
    Color c=new Color(8,161,64);

    public Title(JFrame f, ClassControl data) {
        super(f, data);
        create();
        setPos();
        addAction();
    }

    @Override
    public void create() {
        createButton();
    }

    @Override
    public void setPos() {
        setButtonPos();
    }

    @Override
    public void addAction() {
        addButtonAction();
    }

    void createButton() {
        String[] kwd = {"로그인", "회원가입", "관리자", "종료"};
        for (int i = 0; i < 4; ++i) {
            b[i] = new JButton(kwd[i]);
            b[i].setBackground(c);
            b[i].setForeground(Color.white);
        }
        for (int i = 0; i < 2; ++i) //b[0]~b[1] 글씨 크기
            b[i].setFont(new Font("맑은 고딕", Font.BOLD, 36));

        for (int i = 2; i < 4; ++i)  //b[2]~b[3] 글씨 크기
            b[i].setFont(new Font("맑은 고딕", Font.BOLD, 24));

    }

    void setButtonPos() {
        b[0].setBounds(50, 20, 300, 200);
        b[1].setBounds(50, 240, 300, 200);
        b[2].setBounds(30, 500, 150, 50);
        b[3].setBounds(210, 500, 150, 50);
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
            changerPanel(new Login(frame, data));
        } else if (e.getSource() == b[1]) {
            changerPanel(new Register(frame, data));
        } else if (e.getSource() == b[2]) {
            JOptionPane.showMessageDialog(null, "관리자 모드로 실행합니다",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();

        } else if (e.getSource() == b[3]) {
            System.exit(0);
        }
    }



}

