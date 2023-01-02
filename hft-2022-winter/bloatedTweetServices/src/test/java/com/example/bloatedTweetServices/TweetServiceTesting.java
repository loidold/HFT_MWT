package com.example.bloatedTweetServices;

import com.example.bloatedTweetServices.repository.Tweet;
import com.example.bloatedTweetServices.repository.TweetRepository;
import com.example.bloatedTweetServices.service.TweetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TweetServiceTesting {

  @Mock
  TweetRepository tweetRepository;

  @InjectMocks
  private TweetService tweetService;

  @Test
  public void invalidTweetShouldBeRejected(){
    Tweet tweet = new Tweet("SeniorTwitDev","blablabl elon has bad ideas");
    Assertions.assertThrows(
        IllegalStateException.class,
        () -> tweetService.addTweet(tweet)
    );
  }

  @Test
  public void invalidTweetShouldBeRejectedWhenBadStringAtTheBegining() {
    Tweet tweet = new Tweet("SeniorTwitDev", "elon has bad ideas blaba");
    Assertions.assertThrows(IllegalStateException.class, () -> tweetService.addTweet(tweet));
  }

    @Test
    public void invalidTweetShouldBeRejectedWhenMixedWithUppercase(){
      Tweet tweet = new Tweet("SeniorTwitDev","blablabl Elon has bad Ideas");
      Assertions.assertThrows(
          IllegalStateException.class,
          () -> tweetService.addTweet(tweet)
      );
    }


    @Test
    public void validTweetShouldBePersisted(){
      Tweet tweet = new Tweet("TeslaStan","ah boi elon is a geniuse");
      Assertions.assertDoesNotThrow(() -> tweetService.addTweet(tweet));
    }
  }



