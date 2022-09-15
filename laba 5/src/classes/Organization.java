package classes;

import util.AdditionFunctions;

import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 */
public class Organization implements Comparable <Organization> {
    public Organization() {
        this.id = (int) (Math.random()*10000+1);
    }
    private int id; //�������� ���� ������ ���� ������ 0, �������� ����� ���� ������ ���� ����������, �������� ����� ���� ������ �������������� �������������
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private Coordinates coordinates; //���� �� ����� ���� null
    private Date creationDate; //���� �� ����� ���� null, �������� ����� ���� ������ �������������� �������������
    private Float annualTurnover; //�������� ���� ������ ���� ������ 0, ���� �� ����� ���� null
    private OrganizationType type; //���� ����� ���� null
    private Address officialAddress; //���� �� ����� ���� null


    public void setId() {
        this.id = (int)(Math.random()*10000+1);
    }
    public void setName(Scanner console) {
        System.out.println("������� �������� �����������: ");
        String strName = console.nextLine();
        if (strName.isEmpty() | strName == null) {
            System.out.println("������������ ����. ������� ������ ��������. ���������� �����.");
            setName(console);
        }
        this.name = strName;
    }

    public void setCoordinates(Scanner console) {
        System.out.println("������� ���������� ����������� ");
        Coordinates coordinates = new Coordinates();
        coordinates.setX(console);
        coordinates.setY(console);
        this.coordinates = coordinates;
    }

    public void setCreationDate() {this.creationDate = new Date();}

    public void setAnnualTurnover(Scanner console) {
        System.out.println("������� ������� ������ �����������: ");
        try {
            String strAnnualTurnover = console.nextLine();
            Float floatAnnualTurnover = Float.parseFloat(strAnnualTurnover);
            if (floatAnnualTurnover <= 0) {
                System.out.println("������������  ����. �������� ���� �� ����� ���� ������ 0. ���������� �����.");
                setAnnualTurnover(console);
            }
            this.annualTurnover = floatAnnualTurnover;
        } catch (NullPointerException|NumberFormatException e){
            System.out.println("������������ ����. ������� ������ ��������. ���������� �����");
            setAnnualTurnover(console);
        }
    }

    public void setType(Scanner console) {
        System.out.println("������� ��� �����������: ");
        System.out.println("�������� ���� ����� ����: \n"+ OrganizationType.nameList());
        String strType = console.nextLine();
        try{
            this.type = AdditionFunctions.compareType(strType.toLowerCase(Locale.ROOT));
        }  catch (Exception e) {
            System.out.println(e.getMessage());
            setType(console);
        }
    }
    public void setAddress(Scanner console) {
        System.out.println("������� ����� �����������: ");
        Address address = new Address();
        address.setZipCode(console);
        this.officialAddress = address;
    }

    public void addParameters(Scanner console) {
        setName(console);
        setCoordinates(console);
        setCreationDate();
        setAnnualTurnover(console);
        setType(console);
        setAddress(console);
    }

    public boolean checkParameters() {
        if ((getName() == null) | (getName().equals("")) | (getCoordinates() == null) | (getCreationDate() == null)
        | (getAnnualTurnover() == null) | (getAddress() == null)) {
            return false;
        } else if ((getCoordinates().getX() == null)) {
            return false;
        } else if (getCoordinates().getX() > 630 | getCoordinates().getY() <= -203)  {
            return false;
        }
        if (getAddress() != null) {
            if ((getAddress().getZipCode() == null)) {
                return false;
                }
            }
        return true;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Float getAnnualTurnover() {
        return annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getAddress() {
        return officialAddress;
    }

    @Override
    public int compareTo(Organization organization) {
        int result = this.name.compareTo(organization.getName());
        if (result == 0 ) {
            result = this.annualTurnover.compareTo(organization.getAnnualTurnover());
            }
        return result;
    }

    @Override
    public String toString() {
        if (getType()!=null) {
            return "Organization{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coordinates=" + coordinates.toString() +
                    ", creationDate=" + creationDate +
                    ", annualTurnover=" + annualTurnover +
                    ", type=" + type +
                    ", address=" + officialAddress.toString() + "}";
        } else {
            return "Organization{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coordinates=" + coordinates.toString() +
                    ", creationDate=" + creationDate +
                    ", annualTurnover=" + annualTurnover +
                    ", type=" + null +
                    ", address=" + officialAddress.toString() + "}";
        }
    }
}
