package portfolio.entitybean.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import orca.entitybeans.ejb.AnalysisSessionBeanLocal;
import orca.entitybeans.jpa.Trade;

@Path("/orca/analysis")
public class AnalysisPage {

	@EJB
	private AnalysisSessionBeanLocal analysisManager;
	private List<Double> currentStockPrices;
	private Map<String, Double> mostRecentPrices;
	private List<Trade> trades;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataForOverallPortfolio (
			@PathParam("id") int id) {
		mostRecentPrices =  analysisManager.getMostRecentPrices(id);
		trades = analysisManager.getAllTrades(id);
		
		if ((mostRecentPrices == null) || (trades == null)) {
			return Response.status(Status.fromStatusCode(500)).build();
		}
		
		double totalROI = calculateTotalROI();
		double totalProfit = calculateTotalProfit();
		double[] overallPortfolioData = {totalROI, totalProfit};
		
		return Response.ok(overallPortfolioData).build();
	}
	
	@GET
	@Path("/{id}/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataForSpecificStock (
			@PathParam("id") int id,
			@PathParam("code") String code) {
		currentStockPrices = analysisManager.getPrices(code);
		
		if (currentStockPrices == null) {
			return Response.status(Status.fromStatusCode(500)).build();
		}
		
		double selectedROI = calculateSelectedROI(code);
		double selectedProfit = calculateSelectedProfit(code);
		
		ArrayList<Double> selectedStockData = new ArrayList<Double>();
		selectedStockData.add(selectedROI); selectedStockData.add(selectedProfit);
		for (double price : currentStockPrices) {
			selectedStockData.add(price);
		}
		
		return Response.ok(selectedStockData).build();
	}
	
	private double calculateTotalROI() {
		double totalRoi = 0.0;
		int count = 0;
		for (Trade trade : trades) {
			double currentPrice = mostRecentPrices.get(trade.getStockCode());
			double tempRoi = (currentPrice - trade.getPrice()) / trade.getPrice();
			count++;
			totalRoi = (totalRoi + tempRoi) / count;
		}
		return totalRoi;
	}
	
	private double calculateSelectedROI(String code) {
		double selectedROI = 0.0;
		double currentPrice = mostRecentPrices.get(code);
		int count = 0;
		for (Trade trade : trades) {
			if (trade.getStockCode().equals((code))) {
				double tempROI = (currentPrice - trade.getPrice()) / trade.getPrice();
				count++;
				selectedROI = (selectedROI + tempROI) / count;
			}
		}
		
		return selectedROI;
	}
	
	private double calculateTotalProfit() {
		double totalProfit = 0.0;
		for (Trade trade : trades) {
			double currentPrice = mostRecentPrices.get(trade.getStockCode());
			totalProfit = totalProfit + (currentPrice - trade.getPrice());
		}
		return totalProfit;
	}
	
	private double calculateSelectedProfit(String code) {
		double selectedProfit = 0.0;
		double currentPrice = mostRecentPrices.get(code);
		for (Trade trade : trades) {
			if (trade.getStockCode().equals(code)) {
				selectedProfit = selectedProfit + (currentPrice - trade.getPrice());
			}
		}
		return selectedProfit;
	}
	
}
