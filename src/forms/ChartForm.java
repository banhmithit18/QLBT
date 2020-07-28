package forms;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ChartForm extends JFrame {
    int month = 0;
    public ChartForm() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setting form
        setBounds(100, 100, 1000, 600);
        setUIFont f = new setUIFont();
        f.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        //add root panel
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        //create box
        Box[] boxes = new Box[2];
        boxes[0] = Box.createHorizontalBox();
        boxes[1] = Box.createHorizontalBox();

        boxes[0].createGlue();
        boxes[1].createGlue();

        contentPane.add(boxes[0]);
        contentPane.add(boxes[1]);

        JPanel pnlBody = new JPanel();
        JPanel pnlHead = new JPanel();
        pnlHead.setLayout(null);
        DBConnection db = new DBConnection();
        String strStoreList = "All," + db.getComboboxString("Select storeid from store");
        String[] storeList = strStoreList.split(",");
        JComboBox comboBox = new JComboBox(storeList);
        comboBox.setBounds(90, 10, 180, 25);
        pnlHead.add(comboBox);
        String strStoreInfo = " ," + db.getStoreAddress();
        String[] storeInfoArr = strStoreInfo.split(",");
        java.util.List<String> storeInfoList = Arrays.asList(storeInfoArr);
        ComboboxToolTipRender render = new ComboboxToolTipRender();
        render.setTooltips(storeInfoList);
        comboBox.setRenderer(render);


        JLabel lblNewLabel = new JLabel("Store");
        lblNewLabel.setBounds(10, 10, 45, 25);
        pnlHead.add(lblNewLabel);

        String[] sortBy = {"Day", "Month"};
        JComboBox comboBox_1 = new JComboBox(sortBy);
        comboBox_1.setBounds(90, 45, 180, 25);
        pnlHead.add(comboBox_1);

        JLabel lblNewLabel_1 = new JLabel("By");
        lblNewLabel_1.setBounds(10, 43, 45, 25);
        pnlHead.add(lblNewLabel_1);


        JButton btnNewButton = new JButton("Create");
        btnNewButton.setBounds(573, 45, 132, 25);
        pnlHead.add(btnNewButton);

        JLabel lblNewLabel_2 = new JLabel("Date");
        lblNewLabel_2.setBounds(306, 51, 45, 13);
        pnlHead.add(lblNewLabel_2);

        String[] arrMonth = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        JComboBox comboBox_3 = new JComboBox(arrMonth);
        comboBox_3.setBounds(344, 47, 81, 21);
        pnlHead.add(comboBox_3);

        JComboBox comboBox_2 = new JComboBox();

        for (int i = 2000; i <= 2100; i++) {
            comboBox_2.addItem(i);
        }

        comboBox_2.setBounds(435, 47, 59, 21);
        pnlHead.add(comboBox_2);

        pnlBody.setPreferredSize(new Dimension(1300, 500));
        pnlHead.setPreferredSize(new Dimension(1300, 100));
        boxes[0].add(pnlHead);
        boxes[1].add(pnlBody);

        btnNewButton.addActionListener(e -> {
            pnlBody.removeAll();
            String storeid = (String) comboBox.getSelectedItem();
            String sort = (String) comboBox_1.getSelectedItem();
            String strMonth = comboBox_3.getSelectedItem().toString();
            switch (strMonth) {
                case "January":
                    month = 1;
                    break;
                case "February":
                    month = 2;
                    break;
                case "March":
                    month = 3;
                    break;
                case "April":
                    month = 4;
                    break;
                case "May":
                    month = 5;
                    break;
                case "June":
                    month = 6;
                    break;
                case "July":
                    month = 7;
                    break;
                case "August":
                    month = 8;
                    break;
                case "September":
                    month = 9;
                    break;
                case "October":
                    month = 10;
                    break;
                case "November":
                    month = 11;
                    break;
                case "December":
                    month = 12;
            }
            int year = (int) comboBox_2.getSelectedItem();
            int date = getDateOf.Month(month);


            ArrayList<Float> data = db.callProd(month, year, date,storeid,sort);
            // day la ham de lay dc du lieu
            DefaultCategoryDataset dataset = createDataset(sort, data);
            // Create chart
            JFreeChart chart = ChartFactory.createLineChart(

                    "Sale Volume", // Chart title - tieu de bieu do
                    "", // X-Axis Label - ten cot doc
                    "", // Y-Axis Label - ten cot ngang
                    dataset // data tao tu phia tren

            );
            CategoryPlot plot = chart.getCategoryPlot();
            ValueAxis rangeAxis = plot.getRangeAxis();
            rangeAxis.setAutoRangeMinimumSize(1);
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());


            pnlBody.setLayout(new FlowLayout());// setlayout cho panel chinh chua bieu do
            ChartPanel panel = new ChartPanel(chart); // tao ra 1 chartpanel de chua bieu do
            panel.setPreferredSize(new Dimension(970, 400)); // set size cho bieu do
            pnlBody.add(panel); // ađ bieu do vao panel chinh
            pnlBody.repaint();
            pnlBody.revalidate();
        });


        setVisible(true);
    }

    private DefaultCategoryDataset createDataset(String type, ArrayList<Float> data) {

        String series1 = "Money";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (type.equals("Month")) {
            for (int i = 0; i < 12; i++) {
                int key = i + 1;
                dataset.addValue(data.get(i), series1, "" + key);
            }
        } else if (type.equals("Day")) {
            int date = getDateOf.Month(month);
            for (int i = 0; i < date; i++) {
                int key = i + 1;
                dataset.addValue(data.get(i), series1, "" + key);
                /// data la du lieu 1 ngay
                // series1 o day la ten dong bieu do
                // columnKey la gia tri nam ngang ,vd o day la tu 1 - 31 ( tuong duong 1 thang)
            }
        } else if (type.equals("Year")) {
            for (int i = 0; i < 8; i++) {
                int key = i + 1;
                dataset.addValue(data.get(i), series1, "" + key);

            }
        }
        return dataset;
    }

}
