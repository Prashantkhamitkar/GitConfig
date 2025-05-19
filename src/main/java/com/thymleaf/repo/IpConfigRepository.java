package com.thymleaf.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thymleaf.model.IpConfig;

public interface IpConfigRepository extends JpaRepository<IpConfig, Long> {

}
