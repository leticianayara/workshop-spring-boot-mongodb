package com.leticianayara.workshopmongo.resouces;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leticianayara.workshopmongo.domain.Post;
import com.leticianayara.workshopmongo.resouces.util.URL;
import com.leticianayara.workshopmongo.services.PostService;


@RestController
@RequestMapping(value = "/posts")
public class PostRecouce {

	@Autowired
	private PostService service;
		
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		String textDecode = URL.decodeParam(text);
		List<Post> list = service.findByTitle(textDecode);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> findBySearch(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "min", defaultValue = "") String minDate, 
			@RequestParam(value = "max", defaultValue = "") String maxDate){
		String textDecode = URL.decodeParam(text);
		Date minDateDecode = URL.convertDate(minDate, new Date(0L));
		Date maxDateDecode = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(textDecode, minDateDecode, maxDateDecode);
		return ResponseEntity.ok().body(list);
	}
}
