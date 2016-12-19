package com.luxoft.filestatistic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.services.FileStatisticService;

@Controller
public class IndexController {
	
	@Autowired
	private FileStatisticService fileStatisticService;
	
	@RequestMapping("/")
    public String startPage() {
        return "index";
    }

    @RequestMapping(value = "/index")
    public ModelAndView getFiles() {
    	List<FileStatistic> listFileStatistic = fileStatisticService.getAllFileStatistic();        
        return new ModelAndView("index", "files", listFileStatistic);        
    }
}
