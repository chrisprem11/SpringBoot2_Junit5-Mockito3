package com.demo.test.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.test.model.Greeting;

@Repository
public interface GreetingRepository extends PagingAndSortingRepository<Greeting, Integer> {

}
