import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class InvertWords {
    public static List<Pair> result = new LinkedList<>();
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder();
        try {
            //BufferedReader fileIN = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine()),"UTF8"));
            BufferedReader fileIN = new BufferedReader(new FileReader(reader.readLine()));
            reader.close();
            while(fileIN.ready()){
                str.append(fileIN.readLine());
                str.append(" ");
            }
            fileIN.close();
        } catch(IOException err){ System.out.println(err); }
        finally{
            try {
                reader.close();
            }catch (IOException err){ System.out.println(err); }
        }
        String strToSplit = new String(str);
        String[] tabString = strToSplit.split(" ");
        // for(String tab: tabString) System.out.println(tab);
        strToSplit = null;
        str = null;
        tabString = deletEmptyString(tabString);
        for(String listForOf : tabString)
            System.out.println(listForOf);
        System.out.println("*******************");
        for(int i = 0; i<tabString.length;i++)
        {
            if(tabString[i] == null) continue;
            for(int j = i+1; j<tabString.length; j++)
            {
                if(tabString[j] == null) continue;
                if(tabString[i].equals(revers(tabString[j]))) {
                    Pair par = new Pair();
                    par.first = tabString[i];
                    par.second = tabString[j];
                    tabString[j] = null;
                    result.add(par);
                    break;
                }
            }
        }
        for(Pair listForOf : result)
            System.out.println(listForOf);
    }
    public static String revers(String str){
        String s = new StringBuilder(str).reverse().toString();
        return s;
    }
    public static String[] deletEmptyString(String[] tab){
        int empty = 0;
        for(int i = 0; i<tab.length; i++){
            if(tab[i].isEmpty()) empty++;
        }
        String[] newTab = new String[tab.length - empty];
        int j = 0;
        for(int i = 0; i<tab.length; i++){
            if(tab[i].isEmpty()) continue;
            else {
                newTab[j] = tab[i];
                j++;
            }
        }
        return newTab;
    }

    public static class Pair {
        String first;
        String second;

        Pair(){}
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;
        }
    }
}
