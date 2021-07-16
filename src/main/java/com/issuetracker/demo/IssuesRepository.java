package com.issuetracker.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuesRepository extends JpaRepository<Issues, Long> {

}
