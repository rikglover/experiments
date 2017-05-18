package com.apress.client;

import com.apress.domain.Option;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.apress.domain.Poll;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

public class QuickPollClient {

    private static final String QUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";
    private static final String QUICK_POLL_URI_V2 = "http://localhost:8080/v2/polls";
    private static final String QUICK_POLL_URI_V3 = "http://localhost:8080/v3/polls";
    private static final String QUICK_POLL_URI_V3_OAUTH = "http://localhost:8080/oauth2/v3/polls";

    private RestTemplate restTemplate = new RestTemplate();


    private OAuth2RestTemplate restTemplate() {

        List<String> scopes = Arrays.asList("read", "write");

        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();

        resourceDetails.setGrantType("password");
        resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
        resourceDetails.setClientId("quickpolliOSClient");
        resourceDetails.setClientSecret("top_secret");
        resourceDetails.setScope(scopes);
        resourceDetails.setUsername("mickey");
        resourceDetails.setPassword("cheese");

        return new OAuth2RestTemplate(resourceDetails);
    }

    public Poll getPollById(Long pollId) {

        return restTemplate.getForObject(QUICK_POLL_URI_V1 + "/{pollId}", Poll.class, pollId);
    }

    public Poll getPollByIdWithOauth(Long pollId) {

        OAuth2RestTemplate restTemplate = restTemplate();

        return restTemplate.getForObject(QUICK_POLL_URI_V3_OAUTH + "/{pollId}", Poll.class, pollId);
    }

    public List<Poll> getAllPolls() {

        ParameterizedTypeReference<List<Poll>> responseType = new ParameterizedTypeReference<List<Poll>>() {
        };

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

        restTemplate.delete(QUICK_POLL_URI_V1 + "/{pollId}", pollId);
    }

    public void deletePollV3(Long pollId) {

        HttpHeaders authenticationHeaders = getAuthenticationHeader("admin", "admin");

        restTemplate.exchange(QUICK_POLL_URI_V3 + "/{pollId}", HttpMethod.DELETE, new HttpEntity<Void>(authenticationHeaders), Void.class, pollId);
    }

    public PageWrapper<Poll> getAllPolls(int page, int size) {

        ParameterizedTypeReference<PageWrapper<Poll>> responseType = new ParameterizedTypeReference<PageWrapper<Poll>>() {
        };

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(QUICK_POLL_URI_V2).queryParam("page", page).queryParam("size", size);

        ResponseEntity<PageWrapper<Poll>> responseEntity = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, responseType);

        return responseEntity.getBody();
    }

    private HttpHeaders getAuthenticationHeader(String username, String password) {

        String credentials = username + ":" + password;
        byte[] base64CredentialData = Base64.getEncoder().encode(credentials.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + new String(base64CredentialData));
        return headers;
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

        Poll newPoll3 = client.getPollByIdWithOauth(newPoll2.getId());

        System.out.println("new new new poll: " + newPoll3);

        client.deletePollV3(newPoll3.getId());

        PageWrapper<Poll> pollWrapper1 = client.getAllPolls(0, 4);

        pollWrapper1.getContent().forEach(System.out::println);

        PageWrapper<Poll> pollWrapper2 = client.getAllPolls(1, 4);

        pollWrapper2.getContent().forEach(System.out::println);

    }
}
