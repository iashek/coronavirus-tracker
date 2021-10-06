package io.iashek.coronavirustracker.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.iashek.coronavirustracker.models.LocationStats;
import io.iashek.coronavirustracker.services.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		
		long totalReportedCases = allStats.stream().mapToLong(stat -> stat.getLatestTotalCases()).sum();
		long totalNewCases = allStats.stream().mapToLong(stat -> stat.getDiffFromPrevDay()).sum();
		
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		
		System.out.println(totalReportedCases);
		
		return "home";
	}
	
}
