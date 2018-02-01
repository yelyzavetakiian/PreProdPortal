import consumer.PostConsumer;
import model.Post;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Tests {
    @Test
    public void testGetPostById() {
        PostConsumer consumer = new PostConsumer();
        Post post = consumer.getPostById(1);

        Assert.assertTrue(post.getTitle().contains("sunt aut facere"));
    }

    @Test
    public void testGetAllPosts() {
        PostConsumer consumer = new PostConsumer();
        List<Post> posts = consumer.getAllPosts();

        Assert.assertTrue(posts.get(0).getTitle().equals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

    @Test
    public void testCreatePost() {
        PostConsumer consumer = new PostConsumer();

        Post post = new Post();
        post.setUserId(1);
        post.setId(211);
        post.setTitle("magnam facilis autem");
        post.setBody("dolore placeat quibusdam ea quo vitae nmagni quis enim qui quis");
        consumer.createPost(post);

        Post resultPost = consumer.createPost(post);

        Assert.assertEquals(post.getUserId(), resultPost.getUserId());
        Assert.assertEquals(post.getId(), resultPost.getId());
        Assert.assertEquals(post.getTitle(), resultPost.getTitle());
        Assert.assertEquals(post.getBody(), resultPost.getBody());
    }

    @Test
    public void testUpdatePost() {
        PostConsumer consumer = new PostConsumer();

        Post post = new Post();
        post.setUserId(1);
        post.setId(20);
        post.setTitle("magnam facilis autem");
        post.setBody("dolore placeat quibusdam ea quo vitae nmagni quis enim qui quis");

        Post resultPost = consumer.updatePost(20, post);

        Assert.assertTrue(post.getUserId() == resultPost.getUserId());
        Assert.assertEquals(post.getId(), resultPost.getId());
        Assert.assertEquals(post.getTitle(), resultPost.getTitle());
        Assert.assertEquals(post.getBody(), resultPost.getBody());
    }

    @Test
    public void testDeletePost() {
        PostConsumer consumer = new PostConsumer();
        Post post = consumer.deletePost(1);
    }
}
