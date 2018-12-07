package com.blog.service;

import com.blog.dto.request.PostCommentRequest;
import com.blog.dto.request.UserAgent;
import com.blog.model.Comments;
import com.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This manager is responsible for CRUD operations on the comments associated with a Blog.
 * The necessary business logic about comments, regression models etc. should be done in the service layer.
 * TODO - There is scope to lookup from an in-memory DB as the blog's userbase & interaction grows.
 * Ex - Redis or any other equivalent, so that we're not directly looking up from the mongo db.
 */
@Service
public class BlogCommentService {

	@Autowired
	private CommentRepository commentsRepository;

    /**
     *
     * @param postCommentRequest The comment request containing the necessary info - title
     * @param userAgent
     */
	public void save(PostCommentRequest postCommentRequest, UserAgent userAgent) {
		Comments comment = Comments.create(postCommentRequest, userAgent);
		commentsRepository.save(comment);
	}
	

	public List<Comments> find(String blogId) {
        if (StringUtils.isEmpty((blogId))) {
            return null;
        }
		List<Comments> ar= commentsRepository.findByBlogId(blogId);
        if(ar == null) {
            return ar;
        }
        Collections.sort(ar,new SortbyCommentedTime());
        return ar;
	}
}

/**
 * Helper comparator class to sort a List of Comment objects in DESC(createdTime)
 */
class SortbyCommentedTime implements Comparator<Comments>

{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Comments b1, Comments b2)
    {
        java.util.Date t1 = b1.getCreatedTime();
        java.util.Date t2 = b2.getCreatedTime();
        if(t1 == null && t2 == null) {
            return 0;
        }
        if (t1 == null) {
            return 1;
        }
        if (t2 == null) {
            return -1;
        }
        return (int) (t2.getTime() - t1.getTime());
    }
}
