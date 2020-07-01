package rs.ac.uns.ftn.backend.events;
import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("3m")
public class LoginEvent implements Serializable{
	
    private static final long serialVersionUID = 1L;
    private Date executionTime;
    private Long userId;
    
	public LoginEvent() {
		// TODO Auto-generated constructor stub
	}

	public LoginEvent(Date executionTime, Long userId) {
		super();
		this.executionTime = executionTime;
		this.userId = userId;
	}

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
