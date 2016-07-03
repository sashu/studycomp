package com.study.comp.rest.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NativeAuthController {

	@RequestMapping("/test")
	public Object test() {
		return "Test hello";
	}
}
