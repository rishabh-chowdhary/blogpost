package com.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blog.model.Blogs;

@Repository
public interface BlogRepository extends MongoRepository<Blogs, String>{
	
	 	

}
