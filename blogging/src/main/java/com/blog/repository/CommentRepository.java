package com.blog.repository;

import com.blog.model.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comments, String>{

	public List<Comments> findByBlogId(String id);
}
