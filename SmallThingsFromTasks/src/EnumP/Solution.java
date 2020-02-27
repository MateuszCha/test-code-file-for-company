package EnumP;

/* 
Make Column implement the Columnable interface. See the Javadocs for a description of its methods.
Implement the logic of the Column.getVisibleColumns() method.
The Column.configureColumns() method is already implemented. Don't change it.
Don't create additional fields.
The main() method is not tested.

*/
public class Solution {
    /**
     * Output:
     * <p/>
     * Available Amount
     * Account Number
     * Bank Name
     * --------------------
     * Available Amount
     * Bank Name
     */
    public static void main(String[] args) {

        Column.configureColumns(Column.Amount, Column.AccountNumber, Column.BankName);

        for (Columnable columnable : Column.getVisibleColumns()) { // ok
            System.out.println(columnable.getColumnName());
        }

        System.out.println("--------------------");
        Column.Amount.hide();

        for (Columnable columnable : Column.getVisibleColumns()) {
            System.out.println(columnable.getColumnName());
        }
    }
}
