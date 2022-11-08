// Great Tutorial: https://leetcode.com/problems/design-twitter/discuss/82825/Java-OO-Design-with-most-efficient-function-getNewsFeed

package leetcode.java.LC355;

import java.util.*;

public class Main_355 {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }
}


class Twitter {
    private static int timeStamp = 0;
    private Map<Integer, User> userMap;
    UserService us;

    class Tweet {
        public int tweetId;
        public int time;
        public Tweet next;

        public Tweet(int tweetId) {
            this.tweetId = tweetId;
            this.time = timeStamp++;
            // this.next = null;
        }
    }

    class User {
        private int userId;
        public Set<Integer> follows;
        public Tweet tweet_head;

        public User(int userId) {
            this.userId = userId;
            this.follows = new HashSet<>();
            this.follows.add(userId);
            tweet_head = null;
        }

        public void follow(int followeeId) {
            this.follows.add(followeeId);
        }

        public void unfollow(int followeeId) {
            this.follows.remove(followeeId);
        }

        public void post(int tweetId) {
            Tweet tweet = new Tweet(tweetId);
            tweet.next = this.tweet_head;
            this.tweet_head = tweet;
        }
    }

    class UserService {
        public User checkUser(int userId) {
            if (userMap.containsKey(userId)) {
                return userMap.get(userId);
            } else {
                User user = new User(userId);
                userMap.put(userId, user);
                return user;
            }
        }
    }


    public Twitter() {
        userMap = new HashMap<>();
        us = new UserService();
    }

    public void postTweet(int userId, int tweetId) {
        User user = us.checkUser(userId);
        user.post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> results = new LinkedList<>();

        Set<Integer> follows = us.checkUser(userId).follows;
        PriorityQueue<Tweet> pq = new PriorityQueue<>(userMap.size(), (a, b) -> (b.time - a.time));
        for (int followerId : follows) {
            Tweet t_head = userMap.get(followerId).tweet_head;
            if (t_head != null) {
                pq.offer(t_head);
            }
        }
        while (!pq.isEmpty() && results.size() < 10) {
            Tweet t = pq.poll();
            results.add(t.tweetId);
            if (t.next != null) {
                pq.offer(t.next);
            }
        }
        return results;
    }

    public void follow(int followerId, int followeeId) {
        User follower = us.checkUser(followerId);
        us.checkUser(followeeId);
        follower.follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        User follower = us.checkUser(followerId);
        follower.unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */