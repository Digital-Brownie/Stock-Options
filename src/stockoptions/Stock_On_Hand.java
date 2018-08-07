/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockoptions;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.collections.ObservableList;

/**
 *
 * @author Alex
 */
public class Stock_On_Hand extends Stock_Available
{

    public Stock_On_Hand(Integer stockID, Integer customerID, Integer stockAvailableQuantity, String stockName, String customerCompanyName, LocalDate transactionDate)
    {
        super(stockID, customerID, stockAvailableQuantity, stockName, customerCompanyName, transactionDate);
    }

    public static void insertStockOnHandTable(ObservableList<Stock_Available> newReceiptList)
    {
        try
        {
            for (Stock_Available stock_Available : newReceiptList)
            {
                
                PreparedStatement INSERT = DatabaseConnection.connection.prepareStatement("Insert into stock_on_hand(StockOnHandName, StockOnHandQuantity, ArrivalDate, CustomerID, StockID)"
                        + "                                                                                   VALUES(' " + stock_Available.getStockName() + " ', " + stock_Available.getStockAvailableQuantity() + ", '" + stock_Available.getTransactionDate() + "', " + stock_Available.getCustomerID() + ", " + stock_Available.getStockID() + ")");

                INSERT.executeUpdate();
            }

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
