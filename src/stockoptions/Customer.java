/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockoptions;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
/**
 *
 * @author Alex
 */
public class Customer
{
   DatabaseConnection conn = new DatabaseConnection();
        
    Integer custCode;
    SimpleStringProperty custName;
    SimpleStringProperty custCompany;

    public Customer(Integer custCode, String custName, String custCompany)
    {
        this.custCode = custCode;
        this.custName = new SimpleStringProperty (custName);
        this.custCompany = new SimpleStringProperty (custCompany);
    }
    
    /**
     *
     * @param custName
     * @param custCompany
     */
    public Customer(String custName, String custCompany)
    {
        
        this.custName = new SimpleStringProperty (custName);
        this.custCompany = new SimpleStringProperty (custCompany);
    }

    public Integer getCustCode()
    {
        return custCode;
    }

    public void setCustCode(Integer custCode)
    {
        this.custCode = custCode;
    }

    public String getCustName()
    {
        return custName.get();
    }

    public void setCustName(String custName)
    {
        this.custName = new SimpleStringProperty(custName);
    }

    public String getCustCompany()
    {
        return custCompany.get();
    }

    public void setCustCompany(String custCompany)
    {
        this.custCompany = new SimpleStringProperty(custCompany);
    }
         
    public static Customer getCustomer(int custID) throws SQLException
    {
        ResultSet result;
        Customer cust = null;
        try
        {
            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM customer WHERE customerID = "+custID+";");
             result = SELECT.executeQuery();
             while (result.next())
            {
                cust = new Customer(Integer.parseInt(result.getString("customerID")), result.getString("customerName"), result.getString("customerCompany"));
            }                                     
            
            return cust;
        } catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
        
        
    }
    
    public static ObservableList<Customer> getCustomers() throws Exception
    {
        ResultSet result;
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM customer;");
            result = SELECT.executeQuery();
            
            while (result.next())
            {
                customers.add(new Customer(Integer.parseInt(result.getString("customerID")),result.getString("customerName"),result.getString("customerCompany")));
                
                //System.out.printf("%s %s %s%n", result.getString("custCode"),result.getString("custName"),result.getString("custCompany"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
    
    public static void addCustomer(String name, String company)
    {
        try
        {
            PreparedStatement addCust = DatabaseConnection.getConnection().prepareStatement("insert into customers(custName, custCompany) VALUES( '"+name+"', '"+company+"');");
            addCust.executeUpdate();
            System.out.println("Add customer complete");
            
        } catch (Exception ex)
        {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Customer newCust = new Customer(name, company);                
    }
    
    public static void alterCustomerName(String oldValue, String newValue) 
    {
        try
        {
            PreparedStatement update = DatabaseConnection.getConnection().prepareStatement("update customers set customerName = '"+newValue+"' where customerName = '"+oldValue+"';");
            update.executeUpdate();                                                                                       
            System.out.println("alter customer complete");
        } catch (Exception ex)
        {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("failed to update table");
        }
    }
    
    public static void alterCompanyName(String oldValue, String newValue) 
    {
        try
        {
            PreparedStatement update = DatabaseConnection.getConnection().prepareStatement("update customers set customerCompany = '"+newValue+"' where customerCompany = '"+oldValue+"';");
            update.executeUpdate();                                                                                       
            System.out.println("update complete");
        } catch (Exception ex)
        {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("failed to update table");
        }
    }
    
    public static void deleteCustomer(Integer custCode) throws Exception
    {
        PreparedStatement delete = DatabaseConnection.getConnection().prepareStatement("DELETE from customers WHERE customerID = "+custCode+" ;");
        delete.executeUpdate();                            
    } 
}
