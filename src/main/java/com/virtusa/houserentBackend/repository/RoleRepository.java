package com.virtusa.houserentBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.houserentBackend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
