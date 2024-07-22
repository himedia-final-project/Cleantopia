package aircleanprojectback.restapi.util;

public class MakeMemberId {

    public static String incrementString(String memberId){
        String prefix = memberId.replaceAll("[0-9]", "");
        String numberStr = memberId.replaceAll("[^0-9]", "");

        int number = Integer.parseInt(numberStr);
        number++;

        String incrementNumber = String.format("%03d",number);

        return prefix+incrementNumber;
    }
}
