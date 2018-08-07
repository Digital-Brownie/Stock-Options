/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockoptions;
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
public class Stock_Item
{
    Integer stockID;
    SimpleStringProperty stockName, customerName;
    

    public Stock_Item(Integer stockID, String stockName)
    {
        this.stockID = stockID;
        this.stockName = new SimpleStringProperty (stockName);
        
                
    }

    public Integer getStockID()
    {
        return stockID;
    }

//    public void setStockID(Integer stockID)
//    {
//        this.stockID = stockID;
//    }

    public String getStockName()
    {
        return stockName.get();
    }

    public void setStockName(String stockName)
    {
        this.stockName = new SimpleStringProperty (stockName);
    }           
                
    public static ObservableList<Stock_Item> getStockItems(int custID) throws Exception
    {
        ResultSet result;
        ObservableList<Stock_Item> stock_items = FXCollections.observableArrayList();
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM stock_items WHERE CustomerID = " + custID + ";");
            result = SELECT.executeQuery();
            
            while (result.next())
            {               
                stock_items.add(new Stock_Item(Integer.parseInt(result.getString("StockID")), result.getString("ItemName")));                
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock_items;
    }
    
    public static ObservableList<Stock_Item> getStockItems() throws Exception
    {
        ResultSet result;
        ObservableList<Stock_Item> stock_items = FXCollections.observableArrayList();
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM stock_items;");
            result = SELECT.executeQuery();
            
            while (result.next())
            {               
                stock_items.add(new Stock_Item(Integer.parseInt(result.getString("StockID")), result.getString("ItemName")));  
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock_items;
    }
    
    public static void addNewStockItem(String name)
    {
        try
        {
            PreparedStatement addNewItem = DatabaseConnection.getConnection().prepareStatement("insert into stock_items(ItemName) VALUES( '"+name+"');");
            addNewItem.executeUpdate();
            System.out.println("Add new Item complete");
            
        } catch (Exception ex)
        {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
                                     
    }
}
