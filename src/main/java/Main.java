import java.util.ArrayList;
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


    public ServerString(String version) {
        super(version);
        //System.out.println("Server String constructor");
    }

    @Override
    public boolean valid(ServerString str) {
        System.out.println("------>"+str.getVersion());
        return false;
    }


    //Call getVersion to get the string and check if its valid or not for a comparaison
    @Override
    public boolean valid2(String version) {
        //System.out.println("I'm printing version "+version);
        Pattern pattern = Pattern.compile("((?:[0-9]{1,3}\\.?){0,2}[0-9]{1,3})(?:-\\w*)?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(version);
        if (matcher.matches()){
            //System.out.println("True");
            return true;
        }else {
            //System.out.println("False");
            return false;
        }

    }


}
// https://regex101.com/r/vR7yO4/15 capture the whole string to get if valid
//capture groups for comparing
public class Main {
    public static void main(String[] args) {

        ServerString a = new ServerString("1.8.8-alpha");
        ServerString b = new ServerString("2.0.o");
        //a.valid(a);
        //b.valid(b);
        if (b.valid2(b.getVersion()) && a.valid2(a.getVersion())){
            compare(format(a.getVersion()), format(b.getVersion()), a, b);
        }else if (!a.valid2(a.getVersion())){
            System.out.println(a.getVersion()+" is invalid");
        }else if(!b.valid2(b.getVersion())){
            System.out.println(b.getVersion()+" is invalid");
        }

        //compare(format(a.getVersion()), format(b.getVersion()), a, b);
        //System.out.println(formatForCompare(a.getVersion(), b.getVersion()));
       ;

       // https://regex101.com/r/YLFEdl/1

        //https://regex101.com/r/vR7yO4/15


    }
    public static ArrayList<String> format (String version){
        ArrayList<String> ver = new ArrayList<>();
        Pattern patterngroups = Pattern.compile("([0-9]{1,3})", Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = patterngroups.matcher(version);
        while (matcher1.find()){
            //starting on 1 because 0 is the whole string
            for (int i = 1; i <= matcher1.groupCount(); i++) {
                ver.add(matcher1.group(i));
                //System.out.println(matcher1.group(i));
            }
        }
        System.out.println(ver);
        if(ver.size()<3){
            while(ver.size()<3){
                ver.add("0");
            }
        }
        //System.out.println(ver);
        return ver;
    }

    /*
    public static String formatForCompare(String version1, String version2){
        ArrayList<String> ver1= new ArrayList<String>();
        ArrayList<String> ver2= new ArrayList<String>();
        String result = null;
        Pattern patterngroups = Pattern.compile("([0-9]{1,3})", Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = patterngroups.matcher("2");
        Matcher matcher2 = patterngroups.matcher("1.9");
        int nmatchsgroups = matcher1.groupCount();

        while (matcher1.find()){
            //starting on 1 because 0 is the whole string
            for (int i = 1; i <= matcher1.groupCount(); i++) {
                ver1.add(matcher1.group(i));
                //System.out.println(matcher1.group(i));
            }
        }
        System.out.println(ver1);
        if(ver1.size()<3){
            while(ver1.size()<3){
                ver1.add("0");
            }
        }
        System.out.println(ver1);
        while(matcher2.find()){
            for (int i = 1; i <= matcher2.groupCount(); i++) {
                ver2.add(matcher2.group(i));
            }
        }
        System.out.println(ver2);
        if(ver2.size()<3){
            while(ver2.size()<3){
                ver2.add("0");
            }
        }
        System.out.println(ver2);

        return String.valueOf(nmatchsgroups);
    }

     */

    public static void  compare(ArrayList<String> compare1, ArrayList<String> compare2, ServerString a, ServerString b){
        int i= 0;
        while (i<compare1.size()){
            if (Integer.parseInt(compare1.get(i))>Integer.parseInt(compare2.get(i))){
                System.out.println(a.getVersion()+" greater than "+b.getVersion());
                break;

            }else if (Integer.parseInt(compare1.get(i))<Integer.parseInt(compare2.get(i))){
                System.out.println(b.getVersion()+" greater than "+a.getVersion());
                break;
            }else{
                i++;
            }
            if (i==compare1.size()-1){
                System.out.println(a.getVersion()+" is equal to "+b.getVersion());
            }
        }

    }
}
