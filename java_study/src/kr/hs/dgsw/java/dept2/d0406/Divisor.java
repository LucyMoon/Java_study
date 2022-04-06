package kr.hs.dgsw.java.dept2.d0406;

import java.util.Scanner;

public class Divisor {

   private Scanner scanner;
   private int base;
   private int a;

   
   
   
   public void excute() {
      this.scanner = new Scanner(System.in);
      System.out.println("정수를 입력하세요. ");
   }

   
   public void exit() {
      this.scanner.close();
   }
   
   
   public void finddivisor() {
      
      
      base = scanner.nextInt();
      
      for (a = 1; a <= base; a++) {
         if ((base % a) == 0) {
            System.out.printf("%d", a);
         }
      }
   }
   
   
   public void print() {
      System.out.printf("%d의 약수는 %d입니다.", base, a);
   }
   
   
   
   public static void main(String[] args) {
      
      Divisor divisor = new Divisor();
      divisor.excute();
      divisor.finddivisor();
      divisor.print();
      divisor.exit();

   }

}