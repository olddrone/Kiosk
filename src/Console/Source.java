package Console;

import java.io.*;
import java.util.Scanner;

public class Source implements Manageable{
	private int code;
	private String name;

	@Override
	public void read(Scanner scan) {
		code = scan.nextInt();
		name = scan.next();
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

	public String getName() {
		return name;
	}


}
