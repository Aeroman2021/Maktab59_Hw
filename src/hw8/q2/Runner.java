package hw8.q2;

import java.io.IOException;
import java.util.Scanner;

public class Runner {


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String str = "a";
        numberChecker(str);

    }

    public static void numberChecker(String str) throws Exception {
        try {
            if (str == null)
                throw new NullPointerException("Null input");
            int num = Integer.parseInt(str);
            if(num > 10)
                throw new ExceptionA();
            if(num > 5)
                throw new ExceptionB();
            System.out.println("The input is : " + num);

            throw new IOException("Bad input");

        }catch(IOException e3){
            System.out.println(e3.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (ExceptionB e1){
            System.out.println(e1.getMessage());
        } catch (ExceptionA e2) {
            System.out.println(e2.getMessage());
        }
    }
}
