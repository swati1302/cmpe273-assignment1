package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.library.domain.Review;

public class ReviewRepository implements ReviewRepositoryInterface {
    /** In-memory map to store books. (Key, Value) -> (Id, Review) */
    private final ConcurrentHashMap<Integer, Review> reviewInMemoryMap;

    /** Never access this key directly; instead use generateISBNKey() */
    private Integer id;

    public ReviewRepository(ConcurrentHashMap<Integer, Review> reviewMap) {
	checkNotNull(reviewMap, "bookMap must not be null for BookRepository");
	reviewInMemoryMap = reviewMap;
	id = 0;
    }

    /**
     * This should be called if and only if you are adding new books to the
     * repository.
     * 
     * @return a new incremental Id
     */
    private final Integer generateId() {
	// increment existing id and return the new value
	return Integer.valueOf(++id);
    }

    /**
     * This will auto-generate unique ISBN for new books.
     */
    @Override
    public Review saveReview(long isbn, Review newReview) {
	checkNotNull(newReview, "newReview instance must not be null");
	// Generate new Id
	Integer id = generateId();
	newReview.setId(id);

	reviewInMemoryMap.putIfAbsent(id, newReview);

	return newReview;
    }

    @Override
    public Review getReviewById(Integer id) {
	checkArgument(id > 0,
		"Id was %s but expected greater than zero value", id);
	return reviewInMemoryMap.get(id);
    }
    
    @Override
    public ArrayList<Review> getReviews() {
    	
    	ArrayList<Review> Reviews = new ArrayList<Review>();
    	
    	for(Integer i = 1; i <= reviewInMemoryMap.size(); i++ ) {
    		Review pivot = new Review();
    		pivot = reviewInMemoryMap.get(i);
    	    Reviews.add(pivot);
    	}
    	return Reviews;
    }
    
}
