package rs.ac.uns.ftn.backend.exceptions.handler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


/*https://www.mkyong.com/spring-boot/spring-rest-error-handling-example/
https://dzone.com/articles/spring-rest-service-exception-handling-1
https://www.baeldung.com/exception-handling-for-rest-with-spring
*/
public class CustomErrorResponse {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
    
    
}
