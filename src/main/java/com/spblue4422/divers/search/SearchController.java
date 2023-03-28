package com.spblue4422.divers.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
	private final SearchService searchService;

	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping("/spots")
	public int searchSpotByName(@RequestParam("name") String name) {
		return 0;
	}

	@GetMapping("/spots/advanced")
	public int advancedSearchSpot(@RequestParam("nation") String nation, @RequestParam("location") String location,
								  @RequestParam String name) {
		return 0;
	}

	//userSearch 기능
}
