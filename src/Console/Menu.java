package Console;

import java.util.Scanner;

public class Menu implements Manageable{
	private int idx;
	private String name;
	private int price;

	@Override
	public void read(Scanner scan) {
		idx = scan.nextInt();
		name = scan.next();
		price = scan.nextInt();
	}

	@Override
	public void print() {
		System.out.printf("(%d) %s\n", idx, name);
	}

	@Override
	public boolean matches(String kwd) {
		if(kwd.equals(""+idx)) return true;
		if(name.equals(kwd)) return true;
		return false;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

}
