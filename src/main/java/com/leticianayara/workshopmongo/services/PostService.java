package com.leticianayara.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leticianayara.workshopmongo.domain.Post;
import com.leticianayara.workshopmongo.repository.PostRepository;
import com.leticianayara.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
	}
	
	public List<Post> findByTitle(String text){
		//return repository.findByTitleContainingIgnoreCase(text);
		return repository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date min, Date max){
		max = new Date(max.getTime() + (24 * 60 * 60 * 1000));
		return repository.findByString(text, min, max);
	}
}
