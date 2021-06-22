package com.example.spring02.controller.chart;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.chart.GoogleChartService;

@RestController 
@RequestMapping("/chart/*") //공통적인 url pattern
public class GoogleChartController {
	@Inject //의존관계 주입
	GoogleChartService googleChartService;
	
	@RequestMapping("chart1.do")
	public ModelAndView chart1() {
		return new ModelAndView("chart/chart01"); //view로 이동
	}
	
	@RequestMapping("chart2.do")
	public ModelAndView chart2() {
		return new ModelAndView("chart/chart02");
	}
	
	@RequestMapping("cart_money_list.do")
	public JSONObject cart_money_list() { // view가 아닌 실제 데이터가 리턴
		return googleChartService.getChartData();
	}
}
