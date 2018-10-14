package synom;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import static synom.Synom.cpuLoad;

public class Chart extends ApplicationFrame {

   public Chart( String applicationTitle , String chartTitle ) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Time (s)","CPU Usage (%)",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset( ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      for(int i = 0; i < 30; ++i){
      dataset.addValue( Synom.cpuLoad[i] * 100 , "" , ""+i );
      }
      return dataset;
   }
}
