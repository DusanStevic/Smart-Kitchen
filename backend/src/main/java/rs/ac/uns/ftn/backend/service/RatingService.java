package rs.ac.uns.ftn.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.backend.repository.RatingRepository;

@Service
public class RatingService {
	@Autowired
    private RatingRepository ratingRepository;

}
