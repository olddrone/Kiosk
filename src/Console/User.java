package Console;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Manageable{
	String name;
	String phone;
	int point;
	ArrayList<MyList> myList = new ArrayList<>();
	File f = new File("txtfile//user.txt");
	FileWriter fw = null;


	@Override
	public void read(Scanner scan) {
		name = scan.next();
		phone = scan.next();
		point = scan.nextInt();
	}

	@Override
	public void print() {
		System.out.printf("이름 : %s, 전화번호 : %s, 보유 포인트 : %d\n", name, phone, point);
	}

	@Override
	public boolean matches(String kwd) {
		if(name.equals(kwd)) return true;
		if(phone.equals(kwd)) return true;
		return false;
	}

	void filewrite(String name, String phone) {
		try {
			fw = new FileWriter(f, true);
			fw.write("\n");
			fw.write(name);
			fw.write(" ");
			fw.write(phone);
			fw.write(" ");
			fw.write("0");
			fw.flush();
		}
		catch(IOException e){
			System.out.println("파일 입력 오류");
			System.exit(0);
		}
	}

	public String getName() {
		return name;
	}

	public int getPoint() {
		return point;
	}

	public void setData(String name, String phone) {
		this.name = name;
		this.phone = phone;
		point = 0;
	}

	public void setMyList(Manager<MyList> list) {
		if(myList.isEmpty()) {
			for (MyList m : list.mList) {
				if (m.matches(name))
					myList.add(m);
			}
		}
	}

	public void addList(MyList list) {
		myList.add(list);
	}
	void usePoint(int addPoint){
		String dummy="";
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							new FileInputStream("txtfile//user.txt"),"UTF8"));
			String line;

			while((line=br.readLine())!=null) {
				if (line.indexOf(name) < 0)
					dummy += (line + "\t\n");
				else
					dummy += (name + " " + phone + " " + addPoint + "\n");
			}
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream("txtfile//user.txt"),"UTF8"));
			bw.write(dummy);
			bw.close();
			br.close();
		}
		catch(IOException e){
			System.out.println("파일 입력 오류");
			System.exit(0);
		}
	}

	public ArrayList<MyList> getMyList() {
		return myList;
	}


}