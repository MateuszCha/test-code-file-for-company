package FileOperation;
import java.io.*;

//***********************************
//          IN PROGGRES
//***********************************
/*
CrUD for a table inside a file.
Read a file name for CrUD operations from the console.

The program is started with the following arguments:
-c productName price quantity
-u id productName price quantity
-d id

Argument values:
where id is 8 characters.
productName is 30 characters.
price is 8 characters.
quantity is 4 characters.
-c adds the product with the specified parameters to the end of the file, and generates an id by incrementing the maximum id found in the file.
 */

public class CrUD {
    public static void main(String[] args) throws Exception {
        BufferedReader reder = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reder.readLine();
        reder.close();
        try {

            switch (args[0]) {
                case "-c":
                    addElemnt(args, fileName);
                case "-u":
                    updateElemnet(args, fileName);
                    break;
                case "-d":
                    deletElemnet(args, fileName);
                    break;
                default:
                    return;
            }
        }catch (Exception err){}
    }
    /*
    id is 8 characters.
    productName is 30 characters.
    price is 8 characters.
    quantity is 4 characters.
     */
    public static void addElemnt(String[] args, String fileName) throws IOException, InterruptedException {
        byte[] buffer = readFile(fileName);
        int id = findeId(buffer);
        id ++;
        String newArgs = "";
        newArgs += replaceText(String.valueOf(id),8);
        newArgs += replaceText((args[1]),30);
        newArgs += replaceText((args[2]),8);
        newArgs += replaceText((args[3]),4);
        replaceFile(buffer,newArgs,fileName);
    }
    public static void replaceFile(byte[] buffer,String args,String fileName) throws IOException {
        OutputStream outFile = new FileOutputStream(fileName);
        outFile.write(buffer);
        outFile.write(13);
        outFile.write(10);
        outFile.write(args.getBytes());
        outFile.flush();
        outFile.close();
    }
    public static void replaceFile(byte[] buffer,byte[] buffer2,String args,String fileName) throws IOException {
        OutputStream outFile = null;
        try {
            outFile = new FileOutputStream(fileName);
            outFile.write(buffer);
            if (args != null) {
                outFile.write(args.getBytes());
            }
            if (buffer2 != null && args != null) {
                //outFile.write(13);
                //outFile.write(32);
                //outFile.write(10);
            }
            if (buffer2 != null)
                outFile.write(buffer2);
            outFile.flush();
            outFile.close();
        }catch (Exception err){
            outFile.close();
        }
    }
    public static byte[] readFile(String fileName) throws IOException {
        InputStream inFile = new FileInputStream(fileName);
        byte[] buffer = new byte[inFile.available()];
        int count = inFile.read(buffer);
        inFile.close();
        return buffer;
    }
    public static int findeId(byte[] buffer)
    {
        int id = 1;
        String s = "";
        for(int i = buffer.length - 50; i< buffer.length; i++)
        {
            int value = buffer[i];
            if(value >= 48 && value <=57)
                s += (char)buffer[i];
            else
                break;
        }
        try{
            id = Integer.parseInt(s);
        } catch (NumberFormatException err){
            System.out.println(err);
        }
        return id;
    }
    public static int findeId(byte[] buffor, String id){
        int count = 0;
        int size = buffor.length;
        while(size > 0)  // 52 or 50
        {
            String s = "";
            for (int i = 0; i < 8; i++) {
                int value = buffor[i+50*count]; // 52 or 50
                if (value >= 48 && value <= 57)
                    s += (char) buffor[i+count*50]; /// 52 or 50
                else break;
            }
            if(s.equals(id))
                return count;
            count++;
            size -= 50; // 52
        }
        return -1;
    }
    public static String replaceText(String arg, int size){
        String newText = "";
        for(int i = 0; i<size; i++)
        {
            if(arg.length() <= i)
                newText += " ";
            else
                newText += arg.charAt(i);
        }
        return newText;
    }
    public static void updateElemnet(String[] args, String fileName) throws IOException {
        byte[] buffor = readFile(fileName);
        int count = findeId(buffor,args[1]);
        if(count == -1 ) return;

        String newArgs = "";
        newArgs += replaceText((args[1]),8);
        newArgs += replaceText((args[2]),30);
        newArgs += replaceText((args[3]),8);
        newArgs += replaceText((args[4]),4);

        byte[] buffor1 = new byte[50 * count];  // 52 or 50
        byte[] buffor2 = new byte[buffor.length - 50 * (count+1)]; // 52 or 50
        try {
            for (int i = 0; i < buffor.length-50; i++) { // 52 or 50
                if (i >= 50 * count) //52
                    buffor2[i-50*count] = buffor[i+50]; // 52 or 50
                else
                    buffor1[i] = buffor[i];
            }
            buffor = null;
        }catch (Exception er){ System.out.println(er);}
        replaceFile(buffor1,buffor2,newArgs,fileName);
    }
    public static void deletElemnet(String[] args, String fileName) throws IOException {
        byte[] buffor = readFile(fileName);
        int count = findeId(buffor,args[1]);
        if(count == -1 ) return;
        byte[] buffor1 = new byte[50 * count]; // 52 or 50
        byte[] buffor2 = new byte[buffor.length - 50 * (count+1)]; // 52 or 50
        try {
            for (int i = 0; i < buffor.length-50; i++) { // 52 or 50
                if (i >= 50 * count)                 // 52 or 50
                    buffor2[i-50*count] = buffor[i+50]; // 52 or 50
                else
                    buffor1[i] = buffor[i];
            }
            buffor = null;
        }catch (Exception er){ System.out.println(er);}
        replaceFile(buffor1,buffor2,null,fileName);
    }
}
