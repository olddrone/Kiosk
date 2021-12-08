import Console.*;
import GUI.Title;

import javax.swing.*;
import java.util.Scanner;


public class Main {
	ClassControl data = new ClassControl();
	Scanner scan = new Scanner(System.in);
	JFrame frame = new JFrame();
	Admin admin = new Admin();

	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

	void run() {
		menu();
	}

	void menu() {
		frame.setTitle("키오스크");
		frame.setSize(400, 600);
		frame.setLocationRelativeTo(null);
		JPanel title = new Title(frame, data);
		frame.add(title);
		runMenu();
	}

	void runMenu() {
		int num = 0;
		System.out.printf("[1] 메뉴 데이터 수정 [2] 토핑 데이터 수정 [3] 소스 데이터 수정 [4] 야채 데이터 수정 [기타] 종료 >> ");
		num = scan.nextInt();
		switch (num) {
			case 1: editMenu(); break;
			case 2: editTopping(); break;
			case 3: editSource(); break;
			case 4: editVegetable(); break;
			default: return;
		}
	}

	void editMenu() {
		int num = 0;
		System.out.printf("(1) 메뉴 데이터 추가 (2) 메뉴 데이터 삭제 (기타) 이전 >> ");
		num = scan.nextInt();
		switch (num) {
			case 1: admin.addMenu(); break;
			case 2: admin.delMenu(); break;
			default: runMenu(); break;
		}
	}

	void editTopping() {
		int num = 0;
		System.out.printf("(1) 토핑 데이터 추가 (2) 토핑 데이터 삭제 (기타) 이전 >> ");
		num = scan.nextInt();
		switch (num) {
			case 1: admin.addTopping(); break;
			case 2: admin.delTopping(); break;
			default: runMenu(); break;
		}
	}

	void editSource() {
		int num = 0;
		System.out.printf("(1) 소스 데이터 추가 (2) 소스 데이터 삭제 (기타) 이전 >> ");
		num = scan.nextInt();
		switch (num) {
			case 1:  admin.addSource(); break;
			case 2: admin.delSource(); break;
			default: runMenu(); break;
		}
	}

	void editVegetable() {
		int num = 0;
		System.out.printf("(1) 야채 데이터 추가 (2) 야채 데이터 삭제 (기타) 이전 >>");
		num = scan.nextInt();
		switch (num) {
			case 1: admin.addVegetable(); break;
			case 2: admin.delVegetable(); break;
			default: runMenu(); break;
		}
	}

}
