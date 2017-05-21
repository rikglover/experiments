package com.apress.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Poll extends ResourceSupport {

	@Id
	@GeneratedValue
	@Column(name = "POLL_ID")
	private Long id;

	@Column(name = "QUESTION")
	@NotEmpty
	private String question;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "POLL_ID")
	@OrderBy
	@Size(min = 2, max = 6)
	private Set<Option> options;

	public Poll() {
	}

	public Poll(String question, Set<Option> options) {

		this.question = question;
		this.options = options;
	}

	public Long getPollId() {

		return id;
	}

	public void setPollId(Long id) {

		this.id = id;
	}

	public String getQuestion() {

		return question;
	}

	public void setQuestion(String question) {

		this.question = question;
	}

	public Set<Option> getOptions() {

		return options;
	}

	public void setOptions(Set<Option> options) {

		this.options = options;
	}

	@Override
	public String toString() {

		return getId() + ", " + getQuestion() + ", " + getOptions();
	}
}