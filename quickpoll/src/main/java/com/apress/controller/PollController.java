package com.apress.controller;

import com.apress.domain.Poll;
import com.apress.dto.error.ErrorDetail;
import com.apress.exception.ResourceNotFoundException;
import com.apress.repository.PollRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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
import javax.validation.Valid;
import java.net.URI;

@RestController
@Api(value = "polls", description = "Poll API")
public class PollController {

	@Inject
	private PollRepository pollRepository;

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieves all the polls", response = Poll.class, responseContainer = "List")
	public ResponseEntity<Iterable<Poll>> getAllPolls() {

		Iterable<Poll> allPolls = pollRepository.findAll();
		ResponseEntity<Iterable<Poll>> responseEntity = new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);

		return responseEntity;
	}

	@RequestMapping(value = "/polls", method = RequestMethod.POST)
	@ApiOperation(value = "Creates a new Poll", notes = "The newly created poll Id will be sent in the location response header", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Poll Created Successfully", response = Void.class),
			@ApiResponse(code = 500, message = "Error creating Poll", response = ErrorDetail.class) })
	public ResponseEntity<Void> createPoll(@Valid @RequestBody Poll poll) {

		poll = pollRepository.save(poll);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();

		URI newPollUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(poll.getId())
				.toUri();

		responseHeaders.setLocation(newPollUri);

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieves a Poll associated with the pollId", response = Poll.class)
	public ResponseEntity<?> getPoll(@PathVariable Long pollId) throws ResourceNotFoundException {

		verifyPoll(pollId);

		Poll p = pollRepository.findOne(pollId);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {

		verifyPoll(pollId);

		Poll p = pollRepository.save(poll);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {

		verifyPoll(pollId);

		pollRepository.delete(pollId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	protected void verifyPoll(Long pollId) throws ResourceNotFoundException {

		Poll poll = pollRepository.findOne(pollId);

		if (poll == null) {
			throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
		}
	}
}