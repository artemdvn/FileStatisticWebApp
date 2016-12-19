package com.luxoft.filestatistic.controllers.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.model.LineOfFile;
import com.luxoft.filestatistic.services.FileStatisticService;

/**
* RestController to get information about handled files
* @author Artem Dvornichenko artem.dvornichenko@gmail.com
*
*	GET		/api/v.0.1/files			get list of handled files
*	GET		/api/v.0.1/file/id			get handled file by id
*	GET		/api/v.0.1/lines/id			get list of lines of file
*
*/

@RestController
@RequestMapping("/api/v.0.1")
public class FileStatisticRestController {

	private Logger logger = Logger.getLogger(FileStatisticRestController.class);
	@Autowired
	private FileStatisticService fileStatisticService;
	
	@GetMapping(value = "/files", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FileStatistic>> getfiles() {
        List<FileStatistic> files = fileStatisticService.getAllFileStatistic();

        if (files.isEmpty()) {
            logger.error("HttpStatus: NO_CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
        }
        logger.info("HttpStatus: OK");
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

	@GetMapping(value = "/file/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FileStatistic> getFile(@PathVariable("id") Long fileId) {
		FileStatistic fs = fileStatisticService.getFileStatistic(fileId);

		if (fs == null) {
			logger.error("HttpStatus: NO_CONTENT");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(fs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/lines/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LineOfFile>> getLines(@PathVariable("id") Long fileId) {
		FileStatistic fs = fileStatisticService.getFileStatistic(fileId);

		if (fs == null) {
			logger.error("HttpStatus: NO_CONTENT");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		List<LineOfFile> lines = fileStatisticService.getAllLinesOfFile(fs);

		if (lines.isEmpty()) {
			logger.error("HttpStatus: NO_CONTENT");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lines, HttpStatus.OK);
	}

}
