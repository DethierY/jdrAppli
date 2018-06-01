package jdr.appli.model.fonctional;

import org.springframework.http.HttpStatus;

public class CreationResponse {
	
	private String message;
	private HttpStatus status;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
