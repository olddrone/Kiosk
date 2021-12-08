package Console;

import java.io.*;
import java.util.Scanner;

public class Admin {
    Scanner scan= new Scanner(System.in);
    File menuFile = new File("txtfile//menu.txt");
    FileWriter fw = null;
    File topFile = new File("txtfile//topping.txt");
    FileWriter fw0 = null;
    File vegFile = new File("txtfile//vegetable.txt");
    FileWriter fw1 = null;
    File srcFile = new File("txtfile//source.txt");
    FileWriter fw2 = null;

    public void addMenu() {
        try {
            fw = new FileWriter(menuFile, true);
            fw.write("\n");
            System.out.printf("코드번호: ");
            String code = scan.next();
            fw.write(code);
            fw.write(" ");
            System.out.printf("메뉴이름: ");
            String name = scan.next();
            fw.write(name);
            fw.write(" ");
            System.out.printf("가격: ");
            String pr = scan.next();
            fw.write(pr);
            fw.flush();
        }
        catch(IOException e){
            System.out.println("파일 입력 오류");
            System.exit(0);
        }
    }

    public void delMenu() {
        System.out.printf("메뉴이름: ");
        String name = scan.next();
        delTxtFile(name,"txtfile//menu.txt");

        delTxtFile(name,"txtfile//myList.txt");
    }

    public void addTopping() {
        try {
            fw0 = new FileWriter(topFile, true);
            fw0.write("\n");
            System.out.printf("코드번호: ");
            String code = scan.next();
            fw0.write(code);
            fw0.write(" ");
            System.out.printf("토핑이름: ");
            String name = scan.next();
            fw0.write(name);
            fw0.write(" ");
            System.out.printf("가격: ");
            String pr = scan.next();
            fw0.write(pr);
            fw0.flush();
        }
        catch(IOException e){
            System.out.println("파일 입력 오류");
            System.exit(0);
        }
    }

    public void delTopping() {
        System.out.printf("토핑이름: ");
        String name = scan.next();
        delTxtFile(name,"txtfile//topping.txt");
        delTxtFile(name,"txtfile//myList.txt");
    }

    public void addVegetable() {
        try {
            fw1 = new FileWriter(vegFile, true);
            fw1.write("\n");
            System.out.printf("코드번호: ");
            String code = scan.next();
            fw1.write(code);
            fw1.write(" ");
            System.out.printf("야채이름: ");
            String text = scan.next();
            fw1.write(text);
            fw1.write(" ");
            System.out.printf("가격: ");
            String price = scan.next();
            fw1.write(price);
            fw1.flush();
        }
        catch(IOException e){
            System.out.println("파일 입력 오류");
            System.exit(0);
        }
    }

    public void delVegetable() {
        System.out.printf("야채이름: ");
        String name = scan.next();
        delTxtFile(name,"txtfile//vegetable.txt");
        delTxtFile(name,"txtfile//myList.txt");
    }

    public void addSource() {
        try {
            fw2 = new FileWriter(srcFile, true);
            fw2.write("\n");
            System.out.printf("코드번호: ");
            String code = scan.next();
            fw2.write(code);
            fw2.write(" ");
            System.out.printf("소스이름: ");
            fw2.write(scan.next());
            fw2.flush();
        }
        catch(IOException e){
            System.out.println("파일 입력 오류");
            System.exit(0);
        }
    }

    public void delSource() {
        System.out.printf("소스이름: ");
        String name = scan.next();
        delTxtFile(name,"txtfile//source.txt");
        delTxtFile(name,"txtfile//myList.txt");
    }

    void delTxtFile(String name,String path){
        String dummy="";
        try {

            // IntelliJ 환경에선 다음
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF8"));

            // Eclipse 환경에선 다음
           // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"EUC-KR"));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.indexOf(name) < 0)
                    dummy += (line + "\r\n");
            }


            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF8"));
            // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"EUC-KR"));
            bw.write(dummy);
            bw.close();
            br.close();

        }
        catch(IOException e){
            System.out.println("파일 입력 오류");
            System.exit(0);
        }
    }
}
