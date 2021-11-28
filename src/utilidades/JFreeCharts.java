/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author Adonay
 */
public class JFreeCharts {
    
    public void getBarChart(JPanel pChart, int values[]){
        
        String fontName = "Segoe UI";
        String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(int i = 0; i < values.length; i++){
            dataset.addValue(values[i], "", meses[i]);
        }

        JFreeChart jchart = ChartFactory.createBarChart("Cantidad de Facturas Cerradas realizadas por Mes", "Meses", "Cantidad", dataset, PlotOrientation.VERTICAL, false, true, false);

        StandardChartTheme theme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();
        theme.setTitlePaint(Color.decode("#333333"));
        theme.setExtraLargeFont(new Font(fontName,Font.BOLD, 15)); //title
        theme.setLargeFont(new Font(fontName,Font.BOLD, 15)); //axis-title
        theme.setRegularFont(new Font(fontName,Font.PLAIN, 11));
        theme.setRangeGridlinePaint(Color.decode("#e6e6e6"));
        theme.setPlotBackgroundPaint(Color.white);
        theme.setChartBackgroundPaint(Color.white);
        theme.setGridBandPaint(Color.red);
        theme.setAxisOffset(new RectangleInsets(0,0,0,0));
        theme.setBarPainter(new StandardBarPainter());
        theme.setAxisLabelPaint(Color.decode("#666666")); 
        theme.apply(jchart);
        
        jchart.getCategoryPlot().setOutlineVisible(false);
        jchart.getCategoryPlot().getRangeAxis().setAxisLineVisible(true);
        jchart.getCategoryPlot().getRangeAxis().setTickMarksVisible(false);
        jchart.getCategoryPlot().setRangeGridlineStroke( new BasicStroke() );
        jchart.getCategoryPlot().getRangeAxis().setTickLabelPaint( Color.decode("#666666") );
        jchart.getCategoryPlot().getDomainAxis().setTickLabelPaint( Color.decode("#666666") );
        jchart.setTextAntiAlias( true );
        jchart.setAntiAlias( true );
        jchart.getCategoryPlot().getRenderer().setSeriesPaint( 0, Color.decode( "#00A65A")); //Color de la barra
        
        ChartPanel chartPanel = new ChartPanel(jchart);
        pChart.add(chartPanel);
        pChart.updateUI();
        
    }
}
