/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockoptions;

import java.time.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Alex
 */
public class Stock_Available
{
    Integer stockID,customerID,stockAvailableQuantity;
    SimpleStringProperty stockName,customerCompanyName;
    LocalDate transactionDate;

    public Stock_Available(Integer stockID, Integer customerID, Integer stockAvailableQuantity, String stockName, String customerCompanyName, LocalDate transactionDate)
    {
        this.stockID = stockID;
        this.customerID = customerID;
        this.stockAvailableQuantity = stockAvailableQuantity;
        this.stockName =  new SimpleStringProperty (stockName);
        this.customerCompanyName = new SimpleStringProperty (customerCompanyName);
        this.transactionDate = transactionDate;
    }

    public LocalDate getTransactionDate()
    {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate)
    {
        this.transactionDate = transactionDate;
    }    

    public Integer getStockID()
    {
        return stockID;
    }

    public void setStockID(Integer stockID)
    {
        this.stockID = stockID;
    }

    public Integer getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(Integer customerID)
    {
        this.customerID = customerID;
    }

    public Integer getStockAvailableQuantity()
    {
        return stockAvailableQuantity;
    }

    public void setStockAvailableQuantity(Integer stockAvailableQuantity)
    {
        this.stockAvailableQuantity = stockAvailableQuantity;
    }

    public String getStockName()
    {
        return stockName.get();
    }

    public void setStockName(String stockName)
    {
        this.stockName = new SimpleStringProperty (stockName);
    }

    public String getCustomerCompanyName()
    {
        return customerCompanyName.get();
    }

    public void setCustomerCompanyName(String customerCompanyName)
    {
        this.customerCompanyName = new SimpleStringProperty (customerCompanyName);
    }
    
//    public static ObservableList<Stock_Available> getStockItems(int custID) throws Exception
//    {
//        ResultSet result;
//        ObservableList<Stock_Available> stock_items = FXCollections.observableArrayList();
//        
//        try
//        {
//            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM stock_available WHERE CustomerID = " + custID + ";");
//            result = SELECT.executeQuery();
//            
//            while (result.next())
//            {                               
//                stock_items.add(new Stock_Item(Integer.parseInt(result.getString("StockID")), result.getString("ItemName")));                
//            }
//        } catch (SQLException ex)
//        {
//            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return stock_items;
//    }
//    
//    public static ObservableList<Stock_Item> getStockItems() throws Exception
//    {
//        ResultSet result;
//        ObservableList<Stock_Item> stock_items = FXCollections.observableArrayList();
//        
//        try
//        {
//            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM stock_available;");
//            result = SELECT.executeQuery();
//            
//            while (result.next())
//            {               
//               // stock_items.add(new Stock_Item(Integer.parseInt(result.getString("StockID")), result.getString("ItemName")));                
//            }
//        } catch (SQLException ex)
//        {
//            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return stock_items;
//    }
}

