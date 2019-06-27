/*

 */
package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

public class Chart extends ApplicationFrame implements ActionListener {

    private TimeSeries series;
    private double lastValue = 1.0;
    final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
    public final JButton button = new JButton();
    String type;
    private boolean typ1 = false;
    private boolean typ2 = false;

    public Chart(final String title, String Type) {

        super(title);
        this.type = Type;
        this.series = new TimeSeries("Data", Millisecond.class);
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);
        button.addActionListener(this);

        final JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
        content.add(button, BorderLayout.SOUTH);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        setContentPane(content);

    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "Resources",
                "Time",
                "Value",
                dataset,
                true,
                true,
                false
        );
        final XYPlot plot = result.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(20000.0);  // 60 seconds
        axis = plot.getRangeAxis();
        if (this.type.equals("cpu")) {
            axis.setRange(0.0, 100.0);
        } else if (this.type.equals("ram")) {
            axis.setRange(0.0, 8200);
        }
        return result;
    }

    public void actionPerformed(final ActionEvent e) {
        if (this.type.equals("cpu")) {
            try {
                if(typ1 == false){
                    Thread.sleep(100);
                    typ1 = true;
                }
                else
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
            }
            StringBuilder text = null;
            if(gui.GraphicalUI.label2b.getText().toString().equals("label2b")==true){
                text = new StringBuilder("1.0%");
            }else{
                text = new StringBuilder(gui.GraphicalUI.label2b.getText().toString());
            }
            text.deleteCharAt(text.lastIndexOf("%"));
            this.lastValue = Double.parseDouble(text.toString());
        } else if (this.type.equals("ram")) {
            try {
                if(typ2 == false){
                    Thread.sleep(100);
                    typ2 = true;
                }
                else
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
            }
            StringBuilder text = null;          
             if(gui.GraphicalUI.label2b.getText().toString().equals("label2b")==true){
                text = new StringBuilder("1.0 MB");
            }else{
                text = new StringBuilder(gui.GraphicalUI.label2c.getText().toString());
            }
            text.deleteCharAt(text.lastIndexOf("B"));
            text.deleteCharAt(text.lastIndexOf("M"));
            this.lastValue = Double.parseDouble(text.toString());
        }

        final Millisecond now = new Millisecond();
        this.series.add(new Millisecond(), this.lastValue);

    }

    public void windowClosing(WindowEvent event) {
        if (this.type.equals("cpu")) {
            gui.GraphicalUI.cpuGraph.setEnabled(true);
        } else if (this.type.equals("ram")) {
            gui.GraphicalUI.ramGraph.setEnabled(true);
        }
    }
}
