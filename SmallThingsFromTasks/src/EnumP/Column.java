package EnumP;

import java.util.LinkedList;
import java.util.List;

public enum Column implements Columnable {
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Specifies a new column display order, which is saved in the realOrder array.
     * realOrder[enum index] = display order; -1, if the column is not displayed.
     *
     * @param newOrder new order for displaying the columns in the table
     * @throws IllegalArgumentException if a column is repeated
     */
    // dont change
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values())
        {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }
    //////////////////////

    /**
     * Calculates and returns the list of columns to display in a configurable order (see the configureColumns method)
     * Uses the realOrder.
     *
     * @return list of columns
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();
        int index = 0;

        for (int tabForOF : realOrder)
            if(tabForOF != -1) index++;
        int pomValue = index;

        while(index > 0)
        {

            for (int i = 0; i< realOrder.length; i++){
                if (realOrder[i] == -1) continue;
                if(realOrder[i] == pomValue-index){
                  //  System.out.println(index + " pom2 " + pomValue + "result "+ (pomValue-index));
                    result.add(Column.values()[i]);
                    index--;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public String getColumnName() {
        return this.columnName;
    }

    @Override
    public boolean isShown() {
        if(realOrder[this.ordinal()] == -1) return false;
        else return true;
    }

    @Override
    public void hide() {
        int value = realOrder[this.ordinal()];
        realOrder[this.ordinal()] = -1;
        for(int i = 0; i< realOrder.length; i++){
            if(realOrder[i] > value) realOrder[i] += -1;
        }
        getVisibleColumns();

    }
}
