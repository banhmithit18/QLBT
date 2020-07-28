package forms;

import models.entities.export;
import models.entities.inventory;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;


public class ExportForm extends JDialog {
    String quantityLeft = "";
    int Quantity;
    public ExportForm(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 382, 424);
        setModal(true);
        setResizable(false);
        setUIFont.Font(new FontUIResource("Arial", Font.PLAIN,12));
        setTitle("Export");
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Store");
        lblNewLabel.setBounds(10, 30, 80, 25);
        add(lblNewLabel);

        DBConnection db = new DBConnection();
        String[] strStore = db.getComboboxString("Select storeid from store").split(",");
        JComboBox comboBox = new JComboBox(strStore);
        ComboboxToolTipRender render = new ComboboxToolTipRender();
        comboBox.setRenderer(render);
        String [] storeToolTipArr = db.getStoreAddress().split(",");
        List<String> storeTooltip = Arrays.asList(storeToolTipArr);
        render.setTooltips(storeTooltip);
        ComboboxDecorator.decorate(comboBox,true);
        comboBox.setBounds(89, 30, 200, 25);
        add(comboBox);

        JLabel lblNewLabel_2 = new JLabel("ImportID");
        lblNewLabel_2.setBounds(10, 130, 80, 25);
        add(lblNewLabel_2);

        JLabel lblNewLabel_5 = new JLabel();
        lblNewLabel_5.setBounds(95, 210, 80, 12);
        lblNewLabel_5.setForeground(Color.green);
        add(lblNewLabel_5);

        String[] strImport = db.getComboboxString("Select importid from depot").split(",");
        JComboBox comboBox_2 = new JComboBox(strImport);
        comboBox_2.addActionListener(e -> {
            try{
                Quantity = db.getID("Select quantity from depot where importid ='"+comboBox_2.getSelectedItem().toString()+"'");
                quantityLeft = "Remaining: "+Quantity;
                lblNewLabel_5.setText(quantityLeft);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });
        comboBox_2.setBounds(89, 130, 200, 25);
        add(comboBox_2);

        Quantity = db.getID("Select quantity from depot where importid ='"+comboBox_2.getSelectedItem().toString()+"'");
        quantityLeft = "Remaining: "+ Quantity;
        lblNewLabel_5.setText(quantityLeft);

        JLabel lblNewLabel_1 = new JLabel("Product");
        lblNewLabel_1.setBounds(10, 80, 80, 25);
        add(lblNewLabel_1);

        String[] strProduct = db.getComboboxString("Select distinct productid from depot").split(",");
        JComboBox comboBox_1 = new JComboBox(strProduct);
        String [] productToolTipArr = db.getProductInformation("depot").split(",");
        comboBox_1.addActionListener(e -> {
            try{
                String productid = comboBox_1.getSelectedItem().toString();
                String [] newStrImport = db.getComboboxString("Select importid from depot where productid = '"+productid+"'").split(",");
                comboBox_2.setModel(new DefaultComboBoxModel(newStrImport));
                Quantity = db.getID("Select quantity from depot where importid ='"+comboBox_2.getSelectedItem().toString()+"'");
                quantityLeft = "Remaining: "+Quantity;
                lblNewLabel_5.setText(quantityLeft);

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });
        List<String> productToolTip = Arrays.asList(productToolTipArr);
        ComboboxToolTipRender productRender = new ComboboxToolTipRender();
        comboBox_1.setRenderer(productRender);
        productRender.setTooltips(productToolTip);
        ComboboxDecorator.decorate(comboBox_1,true);
        comboBox_1.setBounds(89, 80, 200, 25);
        add(comboBox_1);

        JLabel lblNewLabel_3 = new JLabel("Quantity");
        lblNewLabel_3.setBounds(10, 180, 80, 25);
        add(lblNewLabel_3);

        JTextField textField = new JTextField();
        textField.setBounds(89, 180, 200, 25);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || (int) e.getKeyChar() == 8) {
                }
                else {
                    e.consume();
                    JOptionPane.showMessageDialog(rootPane, "Please enter number only");

                }
            }
        });
        add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Price");
        lblNewLabel_4.setBounds(10, 230, 80, 25);
        add(lblNewLabel_4);

        JTextField textField_1 = new JTextField();
        textField_1.setBounds(89, 230, 200, 25);
        add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("Create");
        btnNewButton.setBounds(109, 292, 132, 25);
        btnNewButton.addActionListener(e -> {
            String storeId = comboBox.getSelectedItem().toString();
            String productId = comboBox_1.getSelectedItem().toString();
            String importId = comboBox_2.getSelectedItem().toString();
            String price = textField_1.getText();
            int quantity = 0;
            if(db.check("select storeid from store where storeid="+storeId))
            {
                if(db.check("select productid from depot where productid ='"+productId+"'"))
                {
                    try{
                        quantity = Integer.parseInt(textField.getText());
                    }catch (Exception ex)
                    {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(rootPane,"Please enter all required number");
                    }
                  if(Quantity >= quantity)
                  {
                      if(price.matches("^([+-]?\\d*\\.?\\d*)$") && !price.equals(""))
                      {
                          export ex = new export();
                          ex.setImportid(importId);
                          ex.setStoreid(Integer.parseInt(storeId));
                          ex.setProductid(productId);
                          ex.setDate(TimeStampConvert.getTimeStamp());
                          ex.setPrice(Integer.parseInt(price));
                          ex.setQuantity(quantity);
                          ex.setEmployeeid(1); // nho doi
                          ex.setExpirationdate(db.getName("Select expirationdate from depot where importid ='"+importId+"'"));
                          if(db.Create(ex))
                          {
                              JOptionPane.showMessageDialog(rootPane,"Create sucessfully");
                              Quantity = db.getID("Select quantity from depot where importid ='"+comboBox_2.getSelectedItem().toString()+"'");
                              quantityLeft = "Remaining: "+Quantity;
                              lblNewLabel_5.setText(quantityLeft);
                              textField.setText(null);
                              textField_1.setText(null);
                                  int column = 0;
                                  int sum = 0;
                                  int row = DepotForm.tp.row;
                                  for (int i = 0; i < row; i++) {
                                      // su kien sua
                                      if (DepotForm.tp.getLabels().get(column).getText().equals(importId)) {
                                          int newQuantity = Integer.parseInt(DepotForm.tp.labels.get(3 + column).getText()) - quantity ;
                                          if(newQuantity >0) {
                                              DepotForm.tp.labels.get(3 + column).setText(String.valueOf(newQuantity));
                                          }
                                          else
                                          {
                                              DepotForm.tp.labels.get(3 + column).setText("0");
                                              DepotForm.tp.getPnlAllData().remove(i);
                                              DepotForm.tp.pnlAllData.repaint();
                                              DepotForm.tp.getPnlAllData().revalidate();
                                          }
                                      }

                                      /// su kien kiem tra
                                      String productName = db.getName("Select productname from product where productid ='"+productId+"'");
                                      // column + 1 = ten product tren table
                                      if(DepotForm.tp.getLabels().get(column+1).getText().equals(productName))
                                      {
                                          // tinh tong bang tong so luong loai thuoc do
                                          sum += Integer.parseInt(DepotForm.tp.labels.get(3+column).getText());
                                      }
                                      column += DepotForm.tp.column;
                                  }
                                  // neu tong < 10 se chay su kien
                                  if ( sum <10)
                                  {
                                      JOptionPane.showMessageDialog(rootPane,"Product :"+productId+" is nearly out of stock. The remaining stock :"+sum);
                                  }
                              if(!db.check("Select importid from inventory where importid ='"+importId+"'"))
                              {
                                  inventory inv = new inventory();
                                  String exportid = db.getName("Select top 1 exportid from export where importid ='" + importId + "' order by exportid desc");
                                  inv.setExportid(exportid);
                                  inv.setImportid(importId);
                                  inv.setProductid(productId);
                                  inv.setExpirationdate(db.getName("Select expirationdate from depot where importid ='" + importId + "'"));
                                  inv.setStoreid(Integer.parseInt(storeId));
                                  inv.setQuantity(quantity);
                                  inv.setPrice(Integer.parseInt(price));
                                  inv.setExportdate(db.getDate("Select date from export where exportid ='" + exportid + "'"));
                                  db.Create(inv);
                              }
                          }
                      }
                      else
                      {
                          JOptionPane.showMessageDialog(rootPane,"Please enter number only");
                      }
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(rootPane,"Product is not enough in stock");
                  }
                }
                else {
                    JOptionPane.showMessageDialog(rootPane,"Could not find product");
                }
            }else {
                JOptionPane.showMessageDialog(rootPane,"Could not find store");
            }

        });
        add(btnNewButton);



        setVisible(true);
    }
}
