package com.apress.v1.controller;

import com.apress.domain.Vote;
import com.apress.repository.VoteRepository;
import com.wordnik.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;

@RestController("voteControllerV1")
@RequestMapping("/v1/")
@Api(value = "votes", description = "Votes API")
public class VoteController {

	@Inject
	private VoteRepository voteRepository;

	@RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
	public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {

		vote = voteRepository.save(vote);

		// Set the headers for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(vote.getId())
				.toUri();

		responseHeaders.setLocation(uri);

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
	public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {

		return voteRepository.findByPoll(pollId);
	}
}
