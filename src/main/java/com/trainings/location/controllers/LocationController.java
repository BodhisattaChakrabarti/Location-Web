package com.trainings.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trainings.location.entities.Location;
import com.trainings.location.repos.LocationRepository;
import com.trainings.location.services.LocationService;
import com.trainings.location.utils.EmailUtil;
import com.trainings.location.utils.ReportUtil;

import jakarta.servlet.ServletContext;

@Controller
public class LocationController {

	@Autowired
	LocationService service;
	
	//@Autowired
	//LocationRepository repository;
	
	@Autowired
	EmailUtil emailUtil;
	
	/*@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	ServletContext sc;*/
	
	@RequestMapping("/showCreate")
	public String showCreate()
	{
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap)
	{
		Location locationSaved = service.saveLocation(location);
		String msg="Location saved with ID: " + locationSaved.getId();
		modelMap.addAttribute("msg", msg);
		emailUtil.sendEmail("bschakra666@gmail", "Location Saved", "Location saved successfully, will send response now...");
		return "createLocation";
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap)
	{
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap)
	{
		//Location location = service.getLocationById(id);
		Location location = new Location();
		location.setId(id);
		service.deleteLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap)
	{
		Location location = service.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "editLocation";
	}
	
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap)
	{
		service.updateLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	/*@RequestMapping("/generateReport")
	public String generateReport()
	{
		String path = sc.getRealPath("/");
		List<Object[]> data = repository.findTypeAndTypeCount();
		reportUtil.generatePieChart(path, data);
		return "report";
	}*/
}
