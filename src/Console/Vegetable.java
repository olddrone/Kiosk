package Console;

import java.util.Scanner;

public class Vegetable implements Manageable{
	private int code;
	private String name;
	private int price;

	@Override
	public void read(Scanner scan) {
		code = scan.nextInt();
		name = scan.next();
		price = scan.nextInt();
	}

	@Override
	public void print() {
		System.out.printf("(%d) %s \n",code, name);
	}

	@Override
	public boolean matches(String kwd) {
		if(kwd.equals(""+code)) return true;
		if(kwd.equals(name)) return true;
		return false;
	}

	int getPrice() { return price; }

	public String getName() {
		return name;
	}

}