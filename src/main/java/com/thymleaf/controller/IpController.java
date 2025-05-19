package com.thymleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thymleaf.model.IpConfig;
import com.thymleaf.service.IpService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class IpController {

	@Autowired
	private IpService ipservice;
	
	@GetMapping("/ip")
    public ResponseEntity<IpConfig> getIpInfo(HttpServletRequest request) {
        IpConfig ipconfig= ipservice.saveIpInfo(request);
        return ResponseEntity.ok(ipconfig);
    }
}
