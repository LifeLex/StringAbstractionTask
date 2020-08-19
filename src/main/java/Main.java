import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class VersionString{
    public abstract boolean valid();
}
class ServerString extends VersionString{
    String address;

    public ServerString(String address) {
        super();
        this.address= address;
        System.out.println("Server String constructor");
    }

    @Override
    public String toString() {
        return address;
    }

    @Override
    public boolean valid() {

        return false;
    }
}
// https://regex101.com/r/vR7yO4/15 capture the whole string to get if valid
//capture groups for comparing
public class Main {
    public static void main(String[] args) {
        ServerString a = new ServerString("abc");
        System.out.println(a.toString());

       // https://regex101.com/r/YLFEdl/1
        Pattern pattern = Pattern.compile("((?:[0-9]{1,3}\\.?){0,2}[0-9]{1,3})(?:-\\w*)?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("1.9.8-alpha");
        boolean matchFound = matcher.matches();
        int nmatchs = matcher.groupCount();
        if(matchFound) {
            System.out.println("Match found");
        } else {
            System.out.println("Match not found");
        }

        if(nmatchs==1) {
            System.out.println(matcher.group(nmatchs));
        } else {
            for (int i = 0; i < nmatchs; i++) {
                System.out.println(matcher.group(i));

            }
        }

        //https://regex101.com/r/vR7yO4/15
        Pattern patterngroups = Pattern.compile("(([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})|([0-9]{1,3})\\.([0-9]{1,3})|([0-9]{1,3}))(?:-\\w*)?", Pattern.CASE_INSENSITIVE);
        Matcher matchergroups = patterngroups.matcher("1.9.8");
        boolean matchFoundgroups = matchergroups.matches();
        int nmatchsgroups = matchergroups.groupCount();
        if(matchFoundgroups) {
            System.out.println("Match found groups");
        } else {
            System.out.println("Match not found");
        }

        if(nmatchsgroups==1) {
            System.out.println(matchergroups.group(nmatchsgroups));
        } else {
            for (int i = 0; i < nmatchsgroups; i++) {
                System.out.println(matchergroups.group(i));

            }
        }


    }
}
