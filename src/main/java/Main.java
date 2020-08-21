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



    public abstract boolean valid2(String version);

}
class ServerString extends VersionString<ServerString>{


    public ServerString(String version) {
        super(version);

    }


    //Call getVersion to get the string and check if its valid or not for a comparaison
    @Override
    public boolean valid2(String version) {

        Pattern pattern = Pattern.compile("((?:[0-9]{1,3}\\.?){0,2}[0-9]{1,3})(?:-\\w*)?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(version);
        if (matcher.matches()){
            return true;
        }else {
            return false;
        }

    }


}

public class Main {
    public static void main(String[] args) {

        ServerString a = new ServerString("1.8.8-alpha");
        ServerString b = new ServerString("2.0.0");
        String result= null;
        if (b.valid2(b.getVersion()) && a.valid2(a.getVersion())){
           result=  compare(format(a.getVersion()), format(b.getVersion()), a, b);
           System.out.println(result);
        }else if (!a.valid2(a.getVersion())){
            System.out.println(a.getVersion()+" is invalid");
        }else if(!b.valid2(b.getVersion())){
            System.out.println(b.getVersion()+" is invalid");
        }


    }
    public static ArrayList<String> format (String version){
        ArrayList<String> ver = new ArrayList<>();
        Pattern patterngroups = Pattern.compile("([0-9]{1,3})", Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = patterngroups.matcher(version);
        while (matcher1.find()){
            //starting on 1 because 0 is the whole string
            for (int i = 1; i <= matcher1.groupCount(); i++) {
                ver.add(matcher1.group(i));
            }
        }

        if(ver.size()<3){
            while(ver.size()<3){
                ver.add("0");
            }
        }

        return ver;
    }


    public static String  compare(ArrayList<String> compare1, ArrayList<String> compare2, ServerString a, ServerString b){
        int i= 0;
        String result= null;
        while (i<compare1.size()){
            if (Integer.parseInt(compare1.get(i))>Integer.parseInt(compare2.get(i))){
                result=(a.getVersion()+" greater than "+b.getVersion());
                break;

            }else if (Integer.parseInt(compare1.get(i))<Integer.parseInt(compare2.get(i))){
               result=(a.getVersion()+" less than "+b.getVersion());
                break;
            }else{
                i++;
            }
            if (i==compare1.size()-1){
                result=(a.getVersion()+" is equal to "+b.getVersion());
            }
        }
        return result;

    }
}
