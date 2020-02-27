package FileOperation;
/*
Encryption
Come up with an encryption/decryption mechanism.

The program runs with one of the following sets of arguments:
-e fileName fileOutputName
-d fileName fileOutputName

where:
fileName is the name of a file to be encrypted/decrypted.
fileOutputName is the name of the file where you need to write the result of the encryption/decryption process.
-e indicates that you need to encrypt the data.
-d indicates that you need to decrypt the data.

*/

import java.io.*;

public class De_En_Cryption {
    public static void main(String[] args) throws IOException {
        if(args[0].equals("-d"))
            dencryption(args);
        if(args[0].equals("-e"))
            encryption(args);
    }
    public static void dencryption(String[] args) throws IOException {
        OutputStream outFile;
        InputStream inFile;
        int value = 0;
        try {
            inFile = new FileInputStream(args[1]);
            value = inFile.available();
            outFile = new FileOutputStream(args[2]);
        } catch (FileNotFoundException err){
            err.printStackTrace();
            return;
        }
        catch (IOException err){
            err.printStackTrace();
            return;
        }

        try{
            byte[] buffer = new byte[value];
            inFile.read(buffer);
            int count = 1;
            for(int i = 0; i< buffer.length; i++) {
                outFile.write(buffer[i]+count);
                if(count == 100)
                    count = 1;
            }
        } catch (IOException err) {}
        finally {
            inFile.close();
            outFile.close();
        }
    }
    public static void encryption(String[] args) throws IOException {
        OutputStream outFile;
        InputStream inFile;
        int value = 0;
        try {
            inFile = new FileInputStream(args[1]);
            value = inFile.available();
            outFile = new FileOutputStream(args[2]);
        } catch (FileNotFoundException err){
            err.printStackTrace();
            return;
        }
        catch (IOException err){
            err.printStackTrace();
            return;
        }

        try{
            byte[] buffer = new byte[value];
            inFile.read(buffer);
            int count = 1;
            for(int i = 0; i< buffer.length; i++) {
                outFile.write(buffer[i]-count);
                if(count == 100)
                    count = 1;
            }
        } catch (IOException err) {}
        finally {
            inFile.close();
            outFile.close();
        }
    }
}