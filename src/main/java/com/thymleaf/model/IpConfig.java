package com.thymleaf.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class IpConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ipAddress;
	private LocalDateTime timestamp;
	private String userAgent;
	public IpConfig() {}
	public IpConfig(String ipAddress,String userAgent) {
		 this.ipAddress = ipAddress;
	        this.userAgent = userAgent;
	        this.timestamp = LocalDateTime.now();
	}
}
