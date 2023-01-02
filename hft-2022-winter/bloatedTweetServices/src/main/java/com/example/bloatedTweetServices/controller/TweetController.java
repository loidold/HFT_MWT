package com.example.bloatedTweetServices.controller;

import com.example.bloatedTweetServices.repository.Tweet;
import com.example.bloatedTweetServices.service.TweetService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet")
public class TweetController {

  private TweetService tweetService;

  public TweetController(TweetService tweetService) {
    this.tweetService = tweetService;
  }

  @GetMapping
  public List<Tweet> getAllTweets(){
    return tweetService.getAllTweets();
  }

  @PostMapping
  public void addTweet(@RequestBody Tweet tweet){
    tweetService.addTweet(tweet);
  }

}
