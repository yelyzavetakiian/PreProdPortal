package consumer;

import http.client.RestClient;
import json.parser.JsonParser;
import model.Post;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class PostConsumer {
    private RestClient client = new RestClient("https://jsonplaceholder.typicode.com");
    private JsonParser parser = new JsonParser();

    private static final String URL = "/posts";
    private static final Logger LOGGER = Logger.getLogger(PostConsumer.class);

    public Post getPostById(int id) {
        try {
            String response = EntityUtils.toString(client.get(URL + "/" + id).getEntity());
            return parser.convertResponseToObject(response, Post.class);
        } catch (IOException e) {
            LOGGER.error("Can not get response", e);
            throw new IllegalArgumentException();
        }
    }

    public List<Post> getAllPosts() {
        try {
            String response = EntityUtils.toString(client.get(URL).getEntity());
            return parser.convertResponseToListOfObjects(response, Post.class);
        } catch (IOException e) {
            LOGGER.error("Can not get response", e);
            throw new IllegalArgumentException();
        }
    }

    public Post createPost(Post post) {
        try {
            String response = EntityUtils.toString(client.post(URL, parser.convertObjectToJson(post)).getEntity());
            return parser.convertResponseToObject(response, Post.class);
        } catch (IOException e) {
            LOGGER.error("Can not get response", e);
            throw new IllegalArgumentException();
        }
    }

    public Post updatePost(int id, Post post) {
        try {
            String response = EntityUtils.toString(client.put(URL + "/" + id, parser.convertObjectToJson(post)).getEntity());
            return parser.convertResponseToObject(response, Post.class);
        } catch (IOException e) {
            LOGGER.error("Can not get response", e);
            throw new IllegalArgumentException();
        }
    }

    public Post deletePost(int id) {
        try {
            String response = EntityUtils.toString(client.delete(URL + "/" + id).getEntity());
            return parser.convertResponseToObject(response, Post.class);
        } catch (IOException e) {
            LOGGER.error("Can not get response", e);
            throw new IllegalArgumentException();
        }
    }
}
