package com.apress.dto.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {

	private int status;

	private long timeStamp;

	private String title;
	private String detail;
	private String path;
	private String developerMessage;

	private Map<String, List<ValidationError>> errors = new HashMap<>();

	public int getStatus() {

		return status;
	}

	public void setStatus(int status) {

		this.status = status;
	}

	public long getTimeStamp() {

		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {

		this.timeStamp = timeStamp;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getDetail() {

		return detail;
	}

	public void setDetail(String detail) {

		this.detail = detail;
	}

	public String getPath() {

		return path;
	}

	public void setPath(String path) {

		this.path = path;
	}

	public String getDeveloperMessage() {

		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {

		this.developerMessage = developerMessage;
	}

	public Map<String, List<ValidationError>> getErrors() {

		return errors;
	}

	public void setErrors(Map<String, List<ValidationError>> errors) {

		this.errors = errors;
	}
}
