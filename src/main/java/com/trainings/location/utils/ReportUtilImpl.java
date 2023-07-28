package com.trainings.location.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ReportUtilImpl implements ReportUtil {

	@SuppressWarnings("deprecation")
	@Override
	public void generatePieChart(String path, List<Object[]> data) {
		// TODO Auto-generated method stub
		DefaultPieDataset dataset=new DefaultPieDataset();
		
		for(Object[] objects:data)
		{
			dataset.setValue(objects[0].toString(), new Double(objects[1].toString()));
		}
		
		JFreeChart chart = ChartFactory.createPieChart3D("Location Type Report", dataset);
		
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/pieChart.jpeg"), chart, 300, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
