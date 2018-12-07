package com.blog.service;

import com.blog.dto.request.PostBlogRequest;
import com.blog.dto.request.UserAgent;
import com.blog.model.Blogs;
import com.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

    /**
     *
     * @param fromPage pagination index
     * @param size size of list to return
     * @return List of Blog objects
     * TODO - Enable pagination & put limit on size.
     */
	public List<Blogs> getBlogs(int fromPage, int size) {
		//return blogRepository.findAllOrderByUpdatedTimeDesc();
        List<Blogs>ar = blogRepository.findAll();
        Collections.sort(ar,new SortbyUpdatedDate());
		return ar;
	}

    /**
     *
     * @param request The blog post request
     * @param userAgent - Contains info about the user creating the Blog
     * TODO - Should handle any cases where the save operation fails.
     * TODO - Handle error case when the title of Blog is same(for the same user, or generically?)
     */
	public boolean saveBlog(PostBlogRequest request, UserAgent userAgent) {
        if (request == null || userAgent == null) {
            return false;
        }
		blogRepository.save(Blogs.create(request, userAgent));
		return true;
	}

    /**
     *
     * @param id - The BlogID to find.
     * @return - Returns the first row found with the associated blog ID.
     */
	public Blogs getBlogs(String id) {
		return blogRepository.findOne(id);
	}
}

/**
 * Helper comparator class to sort a List of Blog objects in DESC(updatedTime).
 */
class SortbyUpdatedDate implements Comparator<Blogs>

{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Blogs b1, Blogs b2)
    {
        java.util.Date t1 = b1.getUpdatedTime();
        java.util.Date t2 = b2.getUpdatedTime();
        if(t1 == null && t2 == null) {
            return 0;
        }
        if(t1 == null) {
            return 1;
        }
        if(t2 == null) {
            return -1;
        }
        return (int) (t2.getTime() - t1.getTime());
    }
}