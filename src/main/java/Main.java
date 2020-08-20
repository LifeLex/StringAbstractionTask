import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class VersionString<T>{
    private String version;
    public VersionString(String version){
        this.version= version;
    }
    public String getVersion(){
        return version;
    }


    public abstract boolean valid(T str);
    public abstract boolean valid2(String version);
    
}
class ServerString extends VersionString<ServerString>{
    //String address;

    public ServerString(String version) {
        super(version);
        System.out.println("Server String constructor");
    }

    @Override
    public boolean valid(ServerString str) {
        System.out.println("------>"+str.getVersion());
        return false;
    }


    //Call getVersion to get the string and check if its valid or not for a comparaison
    @Override
    public boolean valid2(String version) {
        System.out.println("I'm printing version "+version);
        Pattern pattern = Pattern.compile("((?:[0-9]{1,3}\\.?){0,2}[0-9]{1,3})(?:-\\w*)?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(version);
        if (matcher.matches()){
            System.out.println("True");
            return true;
        }else {
            System.out.println("False");
            return false;
        }

    }
    
    public String compare(String version1, String version2){
        String result = null;
        Pattern patterngroups = Pattern.compile("([0-9]{1,3})", Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = patterngroups.matcher("1.8.8");
        Matcher matcher2 = patterngroups.matcher("1.9.8");
        int nmatchsgroups = matcher1.groupCount();

            while (matcher1.find()){
                //starting on 1 because 0 is the whole string
                for (int i = 1; i <= matcher1.groupCount(); i++) {

                    System.out.println(matcher1.group(i));
                }
            }


        return String.valueOf(nmatchsgroups);
    }

    /*
    @Override
    public String toString() {
        return address;
    }

     */

    /*@Override
    public boolean valid(VersionString str) {
        System.out.println("------>"+str.getVersion());
        return false;
    }*/
}
// https://regex101.com/r/vR7yO4/15 capture the whole string to get if valid
//capture groups for comparing
public class Main {
    public static void main(String[] args) {

        ServerString a = new ServerString("abc");
        ServerString b = new ServerString("1.9.2");
        //a.valid(a);
        //b.valid(b);
        b.valid2(b.getVersion());
        System.out.println(a.compare(a.getVersion(), b.getVersion()));
        /*System.out.println(a.getVersion());

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
*/

    }
}
