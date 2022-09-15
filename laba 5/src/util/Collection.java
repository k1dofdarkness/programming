package util;

import classes.Organization;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * a class containing a collection and all methods for working with it
 */
public class Collection {
    public Collection(PriorityQueue<Organization> collection) {
        this.collection = collection;
        sortCollection();
    }
    private PriorityQueue<Organization> collection;
    private long[] idArray = new long[100];
    private final java.util.Date creationTime = new Date();
    private Scanner console = new Scanner(System.in);

    Saver saver = new Saver();

    public void help() {
        System.out.println("��������� �������:\n" +
                "help: ������� ������� �� ��������� ��������\n" +
                "info: ������� � ����������� ����� ������ ���������� � ��������� (���, ���� �������������, ���������� ��������� � �.�.)\n" +
                "show: ������� � ����������� ����� ������ ��� �������� ��������� � ��������� �������������\n" +
                "add {element} : �������� ����� ������� � ���������\n" +
                "update id {element} : �������� �������� �������� ���������, id �������� ����� ���������\n" +
                "remove_by_id id : ������� ������� �� ��������� �� ��� id\n" +
                "clear: �������� ���������\n" +
                "save: ��������� ��������� � ����\n" +
                "execute_script file_name: ������� � ��������� ������ �� ���������� �����. � ������� ���������� ������� � ����� �� ����, � ������� �� ������ ������������ � ������������� ������\n" +
                "exit: ��������� ��������� (��� ���������� � ����)\n" +
                "add_if_max {element} : �������� ����� ������� � ���������, ���� ��� �������� ��������� �������� ����������� �������� ���� ���������\n" +
                "add_if_min {element} : �������� ����� ������� � ���������, ���� ��� �������� ��������� �������� ����������� �������� ���� ���������\n" +
                "remove_head  : ������� ������ ������� ��������� � ������� ���\n" +
                "sum_of_annual_turnover : ������� ����� �������� ���� annual_turnover ��� ���� ��������� ���������\n" +
                "average_of_annual_turnover : ������� ������� �������� ���� annual_turnover\n" +
                "filter_starts_with_name name : ������� ��������, �������� ���� name ������� ���������� � �������� ���������");
    }
    public void info() {
        System.out.println("��� ���������: " + this.collection.getClass());
        System.out.println("������ ���������: " + this.collection.size());
        System.out.println("���� �������������: " + this.creationTime + "\n");
    }

    public void show() {
        if (collection.size() != 0) {
            for (Organization organization : collection) {
                System.out.println(organization.toString());
            }
        } else {
            System.out.println("��������� �����.");
        }
    }

    public void add(Scanner sc) {
        Organization organization = new Organization();
        organization.addParameters(sc);
        checkId(organization);
        collection.add(organization);
        System.out.println("������� ������� �������� � ���������. ");
        sortCollection();
    }

    public void update(int id, Scanner in) {
        boolean found = false;
        for (Organization organization: collection) {
            if (organization.getId() == id) {
                found = true;
                organization.addParameters(in);
                System.out.println("������� ������� ��������");
                sortCollection();
            }
        }
        if (!found) {
                System.out.println("������� � ������ id �� ������");
        }
    }

    public void remove_by_id(int id) {
        boolean found = false;
        for (Organization organization : collection) {
            if (organization.getId() == id) {
                found = true;
                collection.remove(organization);
                System.out.println("������� ������� �����. ");
                sortCollection();
                }
            }
        if (!found) {
            System.out.println("������� � ������ id �� ������.");
        }
    }

    public void clear() {
        collection.clear();
        System.out.println("��������� ������� �������. ");
        sortCollection();
    }

    public void exit() {
        System.out.println("��������� ���������. ");
        System.exit(0);
    }

    public void sum_of_annual_turnover() {
        float sum = 0;
        if (collection.size() != 0) {
            for (Organization organization : collection) {
                sum += organization.getAnnualTurnover();
            }
            System.out.println("����� ������� �������� ���� �����������: " + sum);
        } else {
            System.out.println("��������� �����.");
        }
    }

    public void average_of_annual_turnover() {
        float sum = 0;
        int count = 0;
        if (collection.size() != 0) {
            for (Organization organization : collection) {
                sum += organization.getAnnualTurnover();
                count += 1;
            }
            System.out.println("������� ������� ������: " + (sum / count));
        } else {
            System.out.println("��������� �����.");
        }
    }

    public void save(String filename) throws IOException{
        FileWriter fileWriter = new FileWriter(filename + ".json");
        fileWriter.write("");
        fileWriter.flush();
        fileWriter.close();
        if (collection.size() != 0) {
            for (Organization organization: collection) {
                saver.serializeOrganization(organization, filename);
            }
        }  else {
            System.out.println("��������� �����.");
        }
        System.out.println("��������� ���������.");
    }

    public void execute_script(String filename) {
        try {
            String path = filename + ".txt";
            Scanner file = new Scanner(new FileReader(path));
            Invoker user = new Invoker(filename);
            user.setCommands(this, file);
            CommandValidator validator = new CommandValidator(user);
            String line;
            while ((line = file.nextLine()) != null) {
                validator.use(line, file);
            }
        } catch (FileNotFoundException e) {
            System.out.println("���� �� ������.");
        } catch (NoSuchElementException e) {
                System.out.println("������ ����������.");
        } catch (StackOverflowError e) {
            System.out.println("������ ����������. �������� �������������� ����� �� ���������.");
            System.exit(1);
        }
    }

    public void filter_starts_with_name(String name) {
        ArrayList <Organization> list = new ArrayList();
        for (Organization organization : collection) {
            if (organization.getName().startsWith(name)) {
                list.add(organization);
                }
            }
        if (!list.isEmpty()) {
            System.out.println(list.stream().map(Organization::toString).collect(Collectors.joining("\n")));
        } else {
            System.out.println("��������, ������������ � �������� ��������� �� �������.");
        }
    }


    public void add_if_max(Scanner sc) {
        Organization organization = new Organization();
        organization.addParameters(sc);
        checkId(organization);
        try {
            Organization max = collection.peek();
            for (Organization org : collection) {
                if (org.compareTo(max) > 0) {
                    max = org;
                }
            }
            if (organization.compareTo(max) > 0) {
                collection.add(organization);
                System.out.println("����� ������� ������� �������� � ���������. ");
                sortCollection();
            } else {
                System.out.println("����������� ������� ������ �������������. ");
            }
        } catch (NoSuchElementException e) {
            collection.add(organization);
        }
    }

    public void add_if_min(Scanner sc) {
        Organization organization = new Organization();
        organization.addParameters(sc);
        checkId(organization);
        try {
            Organization min = collection.peek();
            for (Organization org : collection) {
                if (org.compareTo(min) < 0) {
                    min = org;
                }
            }
            if (organization.compareTo(min) < 0) {
                collection.add(organization);
                System.out.println("����� ������� ������� �������� � ���������. ");
                sortCollection();
            } else {
                System.out.println("����������� ������� ������ ������������");
            }
        } catch (NoSuchElementException e) {
            collection.add(organization);
        }
    }

    public void remove_head() {
        if (collection.size() !=0 ) {
            System.out.println(collection.poll());
            System.out.println("������ ������� ��������� �����.");
        }  else {
            System.out.println("��������� �����.");
        }
    }

    /**
     * method for sorting the collection
     */
    public void sortCollection() {
        ArrayList<Organization> ArrayCollection = new ArrayList<>(collection);
        Collections.sort(ArrayCollection);
        collection.clear();
        int i = 0;
        for (Organization organization: ArrayCollection) {
            collection.add(organization);
            idArray[i] = organization.getId();
            i++;
        }
    }

    public void checkId(Organization organization) {
        for (int i = 0; i<idArray.length; i++) {
            if (organization.getId()==idArray[i]) {
                System.out.println("id ����������� ��� ���� � ���������. �������� ����� id");
                organization.setId();
                checkId(organization);
            }
        }
    }
}
