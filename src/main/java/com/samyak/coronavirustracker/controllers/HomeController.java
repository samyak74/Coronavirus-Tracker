package com.samyak.coronavirustracker.controllers;

import com.samyak.coronavirustracker.models.LocationStats;
import com.samyak.coronavirustracker.services.CoronavirusTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CoronavirusTrackerService coronavirusTrackerService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronavirusTrackerService.getAllStats();
        int totalCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        allStats.sort((t2, t1) -> t1.getLatestTotalCases() - t2.getLatestTotalCases());
        model.addAttribute("totalReportedCases", totalCases);
        model.addAttribute("locationStats", allStats);

        return "home";
    }

}
