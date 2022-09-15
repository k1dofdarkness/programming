package util;

import classes.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a class for additional methods
 */
public class AdditionFunctions {
    public static boolean compare(String arg, Pattern pattern) {
        Matcher matcher = pattern.matcher(arg);
        return matcher.matches();
    }
    public static OrganizationType compareType(String type) throws Exception{
        Pattern COMMERCIAL = Pattern.compile("\\s*commercial\\s*");
        Pattern GOVERNMENT = Pattern.compile("\\s*government\\s*");
        Pattern TRUST = Pattern.compile("\\s*trust\\s*");

        if (compare(type, COMMERCIAL)) {
            return OrganizationType.COMMERCIAL;
        } else if (compare(type, GOVERNMENT)) {
            return OrganizationType.GOVERNMENT;
        } else if (compare(type, TRUST)) {
            return OrganizationType.TRUST;
        } else {
            throw new Exception("������������ ����. ���������� �������� �� ���������������� � ����.");
        }
    }
}
