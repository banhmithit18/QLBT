package forms;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class CreateChart extends JFrame {
    CreateChart(DefaultCategoryDataset dataset){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel contentPane = (JPanel) getContentPane();
        setBounds(100,100,1000,500);
        JFreeChart chart = ChartFactory.createBarChart(

                "Sale Volume", // Chart title - tieu de bieu do
                "", // X-Axis Label - ten cot doc
                "", // Y-Axis Label - ten cot ngang
                dataset // data tao tu phia tren

        );
        CategoryPlot plot = chart.getCategoryPlot();
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeMinimumSize(1);
        contentPane.setLayout(new FlowLayout());// setlayout cho panel chinh chua bieu do
        ChartPanel panel = new ChartPanel(chart); // tao ra 1 chartpanel de chua bieu do
        panel.setPreferredSize(new Dimension(970, 400)); // set size cho bieu do
        contentPane.add(panel); // ađ bieu do vao panel chinh
        setVisible(true);
    }
}
