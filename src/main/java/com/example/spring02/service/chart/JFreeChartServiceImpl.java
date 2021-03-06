package com.example.spring02.service.chart;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.inject.Inject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dao.CartDAO;
import com.example.spring02.model.shop.dto.CartDTO;

@Service
public class JFreeChartServiceImpl implements JFreeChartService {
	@Inject
	CartDAO cartDao;
	
	@Override
	public JFreeChart createChart() {
		List<CartDTO> list=cartDao.cartMoney();
		//차트 데이터셋 구성
		DefaultPieDataset dataset=new DefaultPieDataset();
		for(CartDTO dto : list) {
			dataset.setValue(dto.getProduct_name(), dto.getMoney());
		}
		//차트 객체
		JFreeChart chart=null;
		String title="장바구니 통계";
		try {
			//차트 생성
			chart=ChartFactory.createPieChart(title, dataset, true, 
					true, false);
			//차트 제목
			chart.getTitle().setFont(new Font("돋움",Font.BOLD,15));
			//범례
			chart.getLegend().setItemFont(
					new Font("돋움",Font.PLAIN,10));
			//폰트, 색상, 테마 설정
			Font font=new Font("돋움",Font.PLAIN,12);
			Color color=new Color(0,0,0);
			StandardChartTheme chartTheme=
(StandardChartTheme)StandardChartTheme.createJFreeTheme();
			chartTheme.setExtraLargeFont(font);
			chartTheme.setLargeFont(font);
			chartTheme.setRegularFont(font);
			chartTheme.setSmallFont(font);
			chartTheme.setAxisLabelPaint(color);
			chartTheme.setLegendItemPaint(color);
			chartTheme.setItemLabelPaint(color);
			chartTheme.apply(chart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart; //차트 리턴
	}

}
