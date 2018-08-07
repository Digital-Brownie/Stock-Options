/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockoptions;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 *
 * @author Alex
 */
public class FXMLDocumentFXML_StockOptions_MainController implements Initializable
{
    
    public FXMLDocumentFXML_StockOptions_MainController() throws Exception
    {
        this.custlist = Customer.getCustomers();
    }
    //Add Customer Fields and Labels
    @FXML
    private Label custNamelbl;
    @FXML
    private Label custCompanylbl;
    @FXML
    private TextField custNametxt;
    @FXML
    private TextField custCompanytxt;
    @FXML
    private Button addCustBtn;    

    //Customer TableView Components
    @FXML
    private TableView<Customer> custTable;
    @FXML
    private TableColumn<Customer, Integer> custIDColumn;
    @FXML
    private TableColumn<Customer, String> custNameColumn;
    @FXML
    private TableColumn<Customer, String> custCompanyColumn;
    public ObservableList<Customer> custlist;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //STOCK ON HAND TAB
    @FXML
    private TableView<Stock_On_Hand> stockOnHandTable;
    @FXML
    private TableColumn<Stock_On_Hand, Integer> stockOnHandIDColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, String> stockOnHandNameColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, Integer> stockOnHandQuantityColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, String> stockOnHandCustomerColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, LocalDate> stockOnHandTransactioDateColoumn;
    ObservableList<Stock_On_Hand> stockOnHandList = FXCollections.observableArrayList();
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //RECEIPTS TAB
    
    //Buttons
    
    @FXML
    private ComboBox customersCB;
    @FXML
    private VBox receiveNewStockOptions;
    @FXML
    private TextField newStockItemTxt;
    @FXML
    private HBox stockReceiptTables;
    
    //Receipts Table Views
    
    @FXML
    private TextField newItemQuantityTxt;
    
    //All Stock Items
    @FXML
    private TableView<Stock_Item> receiptsSelectTable;
    @FXML
    private TableColumn<Stock_Item, Integer> receiptsStockIDColoumn;
    @FXML
    private TableColumn<Stock_Item, String> receiptsStockNameColoumn;        
    
    //New Receipt Table
    @FXML
    private TableView<Stock_Available> newReceiptTable;
    @FXML
    private TableColumn<Stock_Available, Integer> newReceiptStockIDColoumn;
    @FXML
    private TableColumn<Stock_Available, String> newReceiptStockNameColoumn;
    @FXML
    private TableColumn<Stock_Available, Integer> newReceiptStockQuantityColoumn;
    @FXML
    private TableColumn<Stock_Available, String> newReceiptStockCustomerColoumn;
    @FXML
    private TableColumn<Stock_Available, LocalDate> newReceiptStockTransactioDateColoumn;
    ObservableList<Stock_Available> newReceiptList = FXCollections.observableArrayList();
        
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //CUSTOMER TAB EVENTS
    @FXML
    public void changeCustNameCellEvent(CellEditEvent edittedCell) throws Exception
    {
        Customer selectedCustomer = custTable.getSelectionModel().getSelectedItem();
        Customer.alterCustomerName(selectedCustomer.getCustName(), (String) edittedCell.getNewValue());

        custlist = Customer.getCustomers();
        custTable.setItems(custlist);
    }

    @FXML
    public void changeCustCompanyCellEvent(CellEditEvent edittedCell) throws Exception
    {
        Customer selectedCustomer = custTable.getSelectionModel().getSelectedItem();
        Customer.alterCompanyName(selectedCustomer.getCustCompany(), (String) edittedCell.getNewValue());
        //selectedCustomer.setCustCompany((String) edittedCell.getNewValue());
        custlist = Customer.getCustomers();
        custTable.setItems(custlist);
    }

    @FXML
    public void NewCustomerButtonPushed()
    {
        custNamelbl.setVisible(true);
        custCompanylbl.setVisible(true);
        custNametxt.setVisible(true);
        custCompanytxt.setVisible(true);
        addCustBtn.setVisible(true);
    }

    @FXML
    public void addCustomerButtonPushed() throws Exception
    {
        Customer newCustomer = new Customer(custNametxt.getText(), custCompanytxt.getText());
        Customer.addCustomer(newCustomer.getCustName(), newCustomer.getCustCompany());
        this.custlist = Customer.getCustomers();
        custTable.setItems(custlist);

        custNamelbl.setVisible(false);
        custCompanylbl.setVisible(false);
        custNametxt.setVisible(false);
        custCompanytxt.setVisible(false);
        addCustBtn.setVisible(false);
    }

    @FXML
    public void removeCustomerButtonPushed() throws Exception
    {
        Customer selectedCustomer = custTable.getSelectionModel().getSelectedItem();
        Customer.deleteCustomer(selectedCustomer.custCode);
        this.custlist = Customer.getCustomers();
        custTable.setItems(custlist);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Receipts Tab Events
    @FXML
    public void recieveNewStockButtonPushed() throws Exception
    {
        receiveNewStockOptions.setVisible(true);
        stockReceiptTables.setVisible(true);
        customersCB.setVisible(true);
        //StockList - Receipts
        receiptsStockIDColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Item, Integer>("stockID"));
        receiptsStockNameColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Item, String>("stockName"));
        //receiptsStockCustomerColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Item, String> (""));
        
        ObservableList<Stock_Item> stockItemByCustomer = Stock_Item.getStockItems();
        receiptsSelectTable.setItems(stockItemByCustomer); 
    }
    
    @FXML
    public Integer comboBoxSelection() throws Exception
    {
        String selectedCust;
        Integer custID;
        selectedCust = customersCB.getValue().toString();        
        custID = Integer.parseInt(selectedCust.substring(0, selectedCust.indexOf((" "))));
        
        return custID;
    }
    
    @FXML
    public void addNewStockButtonPushed() throws Exception
    {
        String newItemName = newStockItemTxt.getText();
        Stock_Item.addNewStockItem(newItemName);
        receiptsSelectTable.setItems(Stock_Item.getStockItems()); 
    }
    
    @FXML
    public void addStockToReceiptButtonPushed() throws Exception
    {
        Stock_Item selectedStockItem = receiptsSelectTable.getSelectionModel().getSelectedItem();                               
        
        newReceiptList.add(new Stock_Available(selectedStockItem.stockID, comboBoxSelection(), Integer.parseInt(newItemQuantityTxt.getText()), selectedStockItem.getStockName(), Customer.getCustomer(comboBoxSelection()).custCompany.get(),LocalDate.now()));                       
        
        newReceiptStockIDColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Available, Integer>("stockID"));
        newReceiptStockNameColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Available, String>("stockName"));
        newReceiptStockQuantityColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Available, Integer>("stockAvailableQuantity"));
        newReceiptStockCustomerColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Available, String>("customerCompanyName"));
        newReceiptStockTransactioDateColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Available, LocalDate>("transactionDate"));
        
        newReceiptTable.setItems(newReceiptList);
    }
    
    @FXML
    public void removeStockFromReceiptButtonPushed()
    {
        Stock_Available selectedStockItem = newReceiptTable.getSelectionModel().getSelectedItem();
        newReceiptList.remove(selectedStockItem);
        newReceiptTable.setItems(newReceiptList);
    }
    
    @FXML
    public void clearStock()
    {
        newReceiptList.clear();
        newReceiptTable.setItems(newReceiptList);
    }    
    
    @FXML
    public void CompleteReceiptButtonPushed()
    {
        Stock_On_Hand.insertStockOnHandTable(newReceiptList);
    }
            
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
         //Add Customer Names and Fields
        custNamelbl.setVisible(false);
        custCompanylbl.setVisible(false);
        custNametxt.setVisible(false);
        custCompanytxt.setVisible(false);
        addCustBtn.setVisible(false);

        //Customer Table 
        custIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("custCode"));
        custNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("custName"));
        custCompanyColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("custCompany"));
        //Get Data From DB
        custTable.setItems(custlist);
        //Allow Table Editing
        custTable.setEditable(true);
        //custIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        custNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        custCompanyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //Receipts Tab
        receiveNewStockOptions.setVisible(false);
        stockReceiptTables.setVisible(false);
        customersCB.setVisible(false);
        
        //Populate Combobox
        for (Customer cust : custlist)
        {
            customersCB.getItems().add(cust.getCustCode() + " " + cust.getCustCompany());
        }
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Stock On Hand Table
        
        stockOnHandIDColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, Integer>("StockID"));
        stockOnHandNameColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("StockName"));
        stockOnHandQuantityColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, Integer>("stockAvailableQuantity"));
        stockOnHandCustomerColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("customerCompanyName"));
        stockOnHandTransactioDateColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, LocalDate>("transactionDate"));
        stockOnHandTable.setItems(stockOnHandList);
        
    }    
    
}
