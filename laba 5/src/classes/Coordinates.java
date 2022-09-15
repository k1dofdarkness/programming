package classes;

import java.util.Scanner;

public class Coordinates {
    private Long x; //������������ �������� ����: 630, ���� �� ����� ���� null
    private double y; //����������� �������� ���� -202, ���� �� ����� ���� null

    public void setX(Scanner console) {
        try{
            System.out.print("������� ���������� X =< 630: ");
            String strX = console.nextLine();
            x = Long.parseLong(strX);
            if (x>630){
                System.out.println("������������ ����. ���� �� ����� ���� ������ 630. ���������� �����.");
                this.setX(console);
            }
        } catch (NullPointerException|NumberFormatException e){
            System.out.println("������������ ����. ������� ������ ��������. ���������� �����.");
            this.setX(console);
        }}
    public void setY(Scanner console) {
        try{
            System.out.print("������� ���������� Y > -203: ");
            String strY = console.nextLine();
            this.y = Double.parseDouble(strY);
            if (y<-203){
                System.out.println("������������ ����. ���� �� ����� ���� ������ ��� ����� -203. ���������� �����.");
                this.setY(console);
            }
        } catch (NullPointerException|NumberFormatException e){
            System.out.println("������������ ����. ������� ������ ��������. ���������� �����.");
            this.setY(console);
        }}

    public Long getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
