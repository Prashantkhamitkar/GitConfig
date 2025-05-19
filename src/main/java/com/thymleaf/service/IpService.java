package com.thymleaf.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymleaf.model.IpConfig;
import com.thymleaf.repo.IpConfigRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class IpService {
	
	 private static final List<String> IP_HEADERS = Arrays.asList(
		        "X-Forwarded-For",
		        "Proxy-Client-IP",
		        "WL-Proxy-Client-IP",
		        "HTTP_X_FORWARDED_FOR",
		        "HTTP_X_FORWARDED",
		        "HTTP_X_CLUSTER_CLIENT_IP",
		        "HTTP_CLIENT_IP",
		        "HTTP_FORWARDED_FOR",
		        "HTTP_FORWARDED",
		        "HTTP_VIA",
		        "REMOTE_ADDR"
		    );
	
	@Autowired
private IpConfigRepository ipconfigrepo;
	
	   public String getClientIp(HttpServletRequest request) {
	        return IP_HEADERS.stream()
	                .map(request::getHeader)
	                .filter(ip -> ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip))
	                .findFirst()
	                .map(this::extractClientIp)
	                .orElseGet(request::getRemoteAddr);
	    }
	   private String extractClientIp(String ipHeader) {
	        // Handle multiple IPs in header (first one is client IP)
	        return ipHeader.split(",")[0].trim();
	    }
	    
	   public IpConfig saveIpInfo(HttpServletRequest request) {
	        String ip = getClientIp(request);
	        // Convert IPv6 loopback to IPv4 for consistency
	        if ("0:0:0:0:0:0:0:1".equals(ip)) {
	            ip = "127.0.0.1";
	        }
	        String userAgent = request.getHeader("User-Agent");
	        IpConfig ipInfo = new IpConfig(ip, userAgent);
	        return ipconfigrepo.save(ipInfo);
	    }
}
