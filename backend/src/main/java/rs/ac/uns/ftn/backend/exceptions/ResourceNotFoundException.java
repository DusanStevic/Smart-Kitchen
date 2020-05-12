package rs.ac.uns.ftn.backend.exceptions;

/*https://www.mkyong.com/spring-boot/spring-rest-error-handling-example/
https://dzone.com/articles/spring-rest-service-exception-handling-1
https://www.baeldung.com/exception-handling-for-rest-with-spring
*/
public class ResourceNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
