package com.apress.client;

import com.apress.domain.Option;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.apress.domain.Poll;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuickPollClient {

    private static final String QUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";

    private RestTemplate restTemplate = new RestTemplate();

    public Poll getPollById(Long pollId) {

        return restTemplate.getForObject(QUICK_POLL_URI_V1 + "/{pollId}", Poll.class, pollId);
    }

    public List<Poll> getAllPolls() {

        ParameterizedTypeReference<List<Poll>> responseType = new ParameterizedTypeReference<List<Poll>>() { };

        ResponseEntity<List<Poll>> responseEntity = restTemplate.exchange(QUICK_POLL_URI_V1, HttpMethod.GET, null, responseType);

        List<Poll> allPolls = responseEntity.getBody();

        return allPolls;
    }

    public List<Poll> getAllPollsByArray() {

        Poll[] allPolls = restTemplate.getForObject(QUICK_POLL_URI_V1, Poll[].class);

        return Arrays.asList(allPolls);
    }

    public Poll createPoll(Poll poll) {

        return restTemplate.postForObject(QUICK_POLL_URI_V1, poll, Poll.class);
    }

    public void updatePoll(Poll poll) {

        restTemplate.put(QUICK_POLL_URI_V1 + "/{pollId}", poll, poll.getId());
    }

    public void deletePoll(Long pollId) {

        restTemplate.delete(QUICK_POLL_URI_V1 + "/{pollId}",  pollId);
    }

    public static void main(String[] args) {

        QuickPollClient client = new QuickPollClient();

        Option option1 = new Option("Red");
        Option option2 = new Option("Blue");

        Set<Option> options = Stream.of(option1, option2).collect(Collectors.toSet());

        Poll newPoll = new Poll("What is your favourate color?", options);

        System.out.println("outputting original poll: " + newPoll);

        Poll newPoll2 = client.createPoll(newPoll);

        System.out.println("new new poll... " + newPoll2);

        newPoll2.setQuestion(newPoll2.getQuestion() + "!?!?!?");

        client.updatePoll(newPoll2);

        Poll newPoll3 = client.getPollById(newPoll2.getId());

        System.out.println("new new new poll: " + newPoll3);

        client.deletePoll(newPoll3.getId());
    }
}
