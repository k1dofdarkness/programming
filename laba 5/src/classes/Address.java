package classes;

import java.util.Scanner;

public class Address {
    private String zipCode; //���� �� ����� ���� null

    public void setZipCode(Scanner console) {
        System.out.println("������� �������� ������: ");
        try{
            String strZipCode = console.nextLine();
            this.zipCode = strZipCode;
        } catch (NullPointerException e) {
            System.out.println("������������ ����. ������� ������ ��������. ���������� �����.");
            setZipCode(console);
        }
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode +
                "'}";
    }
}
