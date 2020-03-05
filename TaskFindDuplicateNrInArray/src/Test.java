import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
    final private int[] exampleArray;
    public Test(){
        this.exampleArray = new int[100_000_000];
        fillArray();
    }

    final private void fillArray(){
        for(int i = 0; i<this.exampleArray.length; i++){
            this.exampleArray[i] = i;
        }
    }
    private void setDuplicationNumber(int index, int nr){
        if(index> this.exampleArray.length){
            System.out.println("your index : "+ index +" is to high array size:" + this.exampleArray.length);
            return;
        }
        this.exampleArray[index] = nr;
    }

    public int[] getExampleArray() {
        return exampleArray;
    }
    private void viewFirsSolution(){
        Date starTime = new Date();
        Date endTime;
        int result = 0;

        byte[] arrayPom = new byte[this.exampleArray.length];
        for(int i = 0; i<this.exampleArray.length; i++)
        {
            if(i>= arrayPom.length-1){
                System.out.println("cos sie stalosie nie tak patrz inplementacja tablicyPOm");
                break;
            }
            if(arrayPom[this.exampleArray[i]]>=1){
                result = this.exampleArray[i];
                break;
            }
            arrayPom[this.exampleArray[i]]++;
        }
        endTime = new Date();
        System.out.println("Pierwsza : czas wykoniania: " + (endTime.getTime()-starTime.getTime())+ " | wynik :" + result);
    }
    private void viewSecondSolution(){
        Date starTime = new Date();
        Date endTime;
        int result = 0;
        int lengtth = (int) this.exampleArray.length / 8;
        byte[] arrayPom = new byte[lengtth];
        for(int i = 0; i<this.exampleArray.length; i++)
        {
            int length = (int)this.exampleArray[i]/8;
            if((arrayPom[length] & 1<<(this.exampleArray[i] - (length)*8)) == 1<<(this.exampleArray[i] - (length)*8)) {
                result = this.exampleArray[i];
                break;
            }
            arrayPom[length] = (byte) (arrayPom[length] | 1<<(this.exampleArray[i] - (length)*8));
        }
        endTime = new Date();
        System.out.println("Druga : czas wykoniania: " + (endTime.getTime()-starTime.getTime())+ " | wynik :" + result);
    }
    private void viewThirdSolution(){
        Date starTime = new Date();
        Date endTime;
        int result = 0;


       searchLoop: for(int i = 0; i<this.exampleArray.length; i++)
        {
            for(int j = i+1; j<this.exampleArray.length; j++)
            {
                if(this.exampleArray[i] == this.exampleArray[j]) {
                    result = this.exampleArray[i];
                    break searchLoop;
                }
            }
        }
        endTime = new Date();
        System.out.println("Trzecia : czas wykoniania: " + (endTime.getTime()-starTime.getTime())+ " | wynik :" + result);
    }
    private void viewTForthSolution(){
        Date starTime = new Date();
        Date endTime;
        int result = 0;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int key;
        for(int i = 0; i<this.exampleArray.length; i++)
        {
            key = this.exampleArray[i];
            if(map.containsKey(key)){
                map.put(key,map.get(key)+1);
                System.out.println("yestem");
            } else map.put(key,0);
            if(map.get(key) >= 1){
                result = this.exampleArray[i];
                break;
            }

        }
        endTime = new Date();
        System.out.println("czwarty : czas wykoniania: " + (endTime.getTime()-starTime.getTime())+ " | wynik :" + result);
    }

    public static void main(String[] args){
        System.out.println("test 1: nr 2 on index 2 is duplicated in position [n-2]");
        Test test = new Test();
        test.setDuplicationNumber(test.exampleArray.length-2,2);
        test.viewFirsSolution();
        test.viewSecondSolution();
        test.viewThirdSolution();
        test.viewTForthSolution();
        System.out.println("test 1: nr n-3 on index n-3 is duplicated in position [n-2]");
        test.fillArray();
        test.setDuplicationNumber(test.exampleArray.length-2,test.exampleArray.length-3);
        test.viewFirsSolution();
        test.viewSecondSolution();
        test.viewTForthSolution();
        System.out.println("One hour later: that is meme xD");
        test.viewThirdSolution();

    }
}

