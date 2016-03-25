package org.kwong.bishe.common.util;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ChartUtil {
	
	/**
	  * 生成曲线图片
	  * @param title 图片名
	  * @param fileName 文件名
	  * @param dates 数据
	  */
	 public static void getLineChart(String title,String fileName, DefaultCategoryDataset dataset){
		 JFreeChart chart = ChartFactory.createLineChart(title, "考试", "分数", dataset, PlotOrientation.VERTICAL, true, false, false);
		 
		 // 写图表对象到文件
        
        FileOutputStream fos_jpg = null; 
        try {
       	 
            fos_jpg = new FileOutputStream(fileName); 
            
            //设置字体
    		 TextTitle textTitle = chart.getTitle();
    		 textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
    		 
    		 LegendTitle legend = chart.getLegend();
    		 legend.setItemFont(new Font("微软雅黑",Font.BOLD,18));
            
    		// 设置柱图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）  
    		 CategoryPlot plot = (CategoryPlot) chart.getPlot();
    	        plot.setBackgroundPaint(new Color(255, 255, 204));  
    	        plot.setForegroundAlpha(0.65F); // 设置前景色透明度  
    	  
    	        // 设置横虚线可见  
    	        plot.setRangeGridlinesVisible(true);  
    	        // 虚线色彩  
    	        plot.setRangeGridlinePaint(Color.gray);  
    	  
    	        CategoryAxis h = plot.getDomainAxis(); // 获取x轴  
    	        h.setMaximumCategoryLabelWidthRatio(1.0f);// 横轴上的 Lable 是否完整显示  
    	        h.setLabelFont(new Font("宋体", Font.BOLD, 12));// 设置字体，防止中文乱码  
    	        h.setTickLabelFont(new Font("宋体", Font.BOLD, 12));// 轴数值  
    	        // h.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//45度倾斜  
    	  
    	        plot.getRangeAxis().setLabelFont(new Font("宋体", Font.BOLD, 12)); // Y轴设置字体，防止中文乱码  
    		 
            ChartUtilities.writeChartAsJPEG(fos_jpg,1.0f,chart,700,650,null); 
        } catch(Exception e){
       	 e.printStackTrace();
        }finally { 
            try { 
                fos_jpg.close(); 
            } catch (Exception e) {} 
        } 
	 }
	 
	 
	 /**
	  * 生成图片
	  * @param title 图片名
	  * @param fileName 文件名
	  * @param dates 数据
	  */
	 public static void getChart(String title,String fileName, Map<String,Integer> dates){
		 DefaultPieDataset data = getDataSet(dates); 
		 JFreeChart chart = ChartFactory.createPieChart(title,  // 图表标题
		 data, 
		 true, //是否显示图例
		 false, 
		 false 
		 ); 
		 // 写图表对象到文件，参照柱状图生成源码
         
         FileOutputStream fos_jpg = null; 
         try {
        	 
             fos_jpg = new FileOutputStream(fileName); 
             
             //设置字体
     		 TextTitle textTitle = chart.getTitle();
     		 textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
     		 PiePlot plot = (PiePlot) chart.getPlot();
     		 plot.setLabelFont(new Font("SimSun",5,12));
     		 LegendTitle legend = chart.getLegend();
     		 legend.setItemFont(new Font("微软雅黑",Font.BOLD,18));
             
             ChartUtilities.writeChartAsJPEG(fos_jpg,1.0f,chart,400,300,null); 
         } catch(Exception e){
        	 e.printStackTrace();
         }finally { 
             try { 
                 fos_jpg.close(); 
             } catch (Exception e) {} 
         } 
	 }



	/** 
	 * @author 苏俊
	 * 获取一个数据集对象
	 * @return 
	 */ 
	 private static DefaultPieDataset getDataSet(Map<String,Integer> dates) { 
		 DefaultPieDataset dataset = new DefaultPieDataset(); 
		 if(dates != null && !dates.isEmpty()){
			 for(String s:dates.keySet()){
				 dataset.setValue(s,dates.get(s)); 
			 }
		 }
		 return dataset; 
	 } 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
//	 public static void main(String[] args) throws IOException{ 
//		 getChart3D("考试曲线图","c:\\fruit.jpg",null);
//		 
//	 } 
//	 



	/** 
	 * @author 苏俊
	 * 获取一个演示用的简单数据集对象
	 * @return 
	 */ 
//	 private static DefaultCategoryDataset  getDataSet(Map<String,Integer> dates) { 
//		 DefaultCategoryDataset  dataset = new DefaultCategoryDataset (); 
//				 dataset.addValue(257, "总分", "考试1");
//				 dataset.addValue(88, "平均分", "考试1");
//				 //dataset.addValue(3, "考试3", "考试1");
//				 
//				 dataset.addValue(300, "总分", "考试2");
//				 dataset.addValue(90, "平均分", "考试2");
//				// dataset.addValue(2, "x3", "考试2");
//				 
//				 dataset.addValue(269, "总分", "考试3");
//				 dataset.addValue(90, "平均分", "考试3");
//				 //dataset.addValue(1, "x3", "考试3");
//				 
//		 return dataset; 
//	 } 
}