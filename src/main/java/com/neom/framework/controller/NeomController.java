package com.neom.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class NeomController {

	@GetMapping("/")
	public String health() {

		log.info("Application is up and running !!! ");
		return "ok";
	}

}
