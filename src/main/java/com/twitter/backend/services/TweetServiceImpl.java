package com.twitter.backend.services;

import com.twitter.backend.modals.Tweet;
import com.twitter.backend.repositories.TweetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TweetServiceImpl implements TweetService {
    Logger logger = LoggerFactory.getLogger(TweetServiceImpl.class);

    TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepo) {
        this.tweetRepository=tweetRepo;
    }

    @Override
    public Tweet postTweet(Tweet tweet) {
        logger.info("Posting Tweet by: " +tweet.getUsername());
        tweet.setId(UUID.randomUUID());
        tweet.setCreated_timeStamp(LocalDateTime.now());
        tweetRepository.save(tweet);
        logger.info("Tweet poster successfully");
        return tweet;
    }

    @Override
    public Tweet deleteTweet(Tweet tweet) {
        logger.info("Deleting tweet....");
        tweetRepository.delete(tweet);
        logger.info("Deleted successfully");
        return tweet;
    }

}
