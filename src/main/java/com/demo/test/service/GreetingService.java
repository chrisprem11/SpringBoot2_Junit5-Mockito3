package com.demo.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.test.model.Greeting;
import com.demo.test.repository.GreetingRepository;

@Service
public class GreetingService {

	@Autowired
	private GreetingRepository greetingRepository;

	public Greeting saveGreeting(Greeting greeting) {
		return greetingRepository.save(greeting);
	}

	public Optional<Greeting> findById(int id) {
		return greetingRepository.findById(id);
	}

	public List<Greeting> findAllGreetings(Integer pageNo, Integer pageSize, String sortProperty,
			boolean isSortAscending) {
		Pageable paging = null;
		if (isSortAscending) {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortProperty).ascending());
		} else {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortProperty).descending());
		}
		return greetingRepository.findAll(paging).getContent();
	}

	public void deleteGreetingById(int id) {
		greetingRepository.deleteById(id);
	}
}
