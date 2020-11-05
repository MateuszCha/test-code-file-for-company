package sourceTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import source.Main;



public class MainTest {

    static Main main;
    public static int i = 0;

    @BeforeClass
    public static void setUP(){
        MainTest.main = new Main();
    }
    @Before
    public void setUp(){
        System.out.println(" lubie placki " + i);
        i++;
    }

    @Test
    public void add_twoPlusTwo_returnFour(){
        final int expect = 4;
        final int actual =  main.add(2,2);
        assertEquals(expect,actual, "2 + 2 is 4");
    }
    @Test
    public void add_twoPlusOppositeTwo_returnZero(){
        final int expect = 0;
        final int actual =  main.add(2,-2);
        assertEquals(expect,actual, "2 + (-2) is 0");
    }
    @Test
    public void subtract_FourMinusTwo_returnTwo(){
        final int expect = 2;
        final int actual = main.subtract(4,2);
        assertEquals(expect,actual," 4 - 2 is 2");
    }
    @Test
    public void subtract_fourMinusOppositeDour_returnZero(){
        final int expect = 8;
        final int actual = main.subtract(4,-4);
        assertEquals(expect,actual, "4 - (-4) is 8" );
    }
    @Test
    public void multiplication_twoMultiplicationFour_returnEight(){
        final int expect = 8;
        final int actual = main.multiplication(2,4);
        assertEquals(expect,actual,"2 * 4 is 8");
    }
    @Test
    public void multiplication_twoMultiplicationOppositeFour_returnOppositeEight(){
        final int exepect = -8;
        final int actual = main.multiplication(2,-4);
        assertEquals(exepect,actual, " 2 * (-4) is (-8)" );
    }
    @Test
    public void multiplication_OppositeTwoMultiplicationZero_returnOppositeZero(){
        final int exepect = 0;
        final int actual = main.multiplication(-2,0);
        assertEquals(exepect,actual, " (-2) * 0 is (0)" );
    }



}
