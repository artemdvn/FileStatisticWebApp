package com.luxoft.filestatistic.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.model.LineOfFile;
import com.luxoft.filestatistic.services.FileStatisticService;

@Controller
public class FileStatisticController {
	
	private Logger logger = Logger.getLogger(FileStatisticController.class);
	
	@Autowired
	private FileStatisticService fileStatisticService;

    @RequestMapping(value = "/uploadNewFile", method = RequestMethod.POST)
    public String uploadNewFile(@RequestParam("file") MultipartFile uploadfile,
            HttpServletRequest request) {

    	String avatarPath;
        try {
            String fileName = uploadfile.getOriginalFilename();
            String directory = request.getServletContext().getRealPath("/resources/files");

            //real path to image storage
            avatarPath = Paths.get(directory, fileName).toString();

            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(avatarPath)));
            stream.write(uploadfile.getBytes());
            stream.close();

            //path with will be stored in DB (related to webapp)
            String relatedPath = request.getRequestURL()
                    .substring(0, request.getRequestURL().indexOf("/uploadNewFile"))
                    + "/resources/files/" + fileName;
            logger.info("====> new file stored on the disk " + relatedPath);

            fileStatisticService.saveFileStatistic(avatarPath);
            logger.info("====> handled new file, which is stored in: " + avatarPath);

        } catch (Exception e) {
            logger.info("file is not uploaded");
            e.printStackTrace();
        }

        return "redirect:/index";

    }
    
    @RequestMapping(value = "/linesStatistic", method = RequestMethod.GET)
    public String getLinesStatistic(@RequestParam("id") Long fileId, ModelMap map) {

    	FileStatistic fs = fileStatisticService.getFileStatistic(fileId);
    	List<LineOfFile> lines = fileStatisticService.getAllLinesOfFile(fs);

        map.put("file", fs);
        map.put("lines", lines);

        return "linesStatistic";
    }

}
