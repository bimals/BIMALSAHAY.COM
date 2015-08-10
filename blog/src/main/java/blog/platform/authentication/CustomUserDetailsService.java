package blog.platform.authentication;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getinsured.hix.model.UserRole;
import com.getinsured.hix.model.UserSession;
import com.getinsured.hix.platform.security.repository.IUserRepository;
import com.getinsured.hix.platform.security.repository.IUserSessionRepository;
import com.getinsured.hix.platform.security.session.SessionTrackerService;

import blog.model.AccountUser;


@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService,AuthenticationUserDetailsService {
	private static final Logger LOGGER = Logger.getLogger(CustomUserDetailsService.class);
	
	@Autowired private IUserRepository userRepository;
	@Autowired private IUserSessionRepository sessionRepo;
	@Autowired private SessionTrackerService sessionTracker;
		
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("Getting user details from loadUserByUsername :: " );
		
		AccountUser domainUser = null;
		List<UserSession> invalidatedSessions = null;
		try {
			
			if(username.contains("ssoAuth:")){
				username=StringUtils.substringAfter(username, "ssoAuth:");
				domainUser = userRepository.findByExtnAppUserId(username);
				domainUser.setPassword("ssoAuth:NOT_APPLICABLE");
				domainUser.setUuid("ssoAuth:NOT_APPLICABLE");
				
			}else if(username.equalsIgnoreCase("anonymous")) {
				domainUser = new AccountUser();
				domainUser.setUserName("anonymous");
				domainUser.setAuthenticated(true);
				domainUser.setConfirmed(1);
			}
			else {
				domainUser = userRepository.findByUserName(username.toLowerCase());
			}
			
			
			if (domainUser == null) {
				LOGGER.error("Username Not Found :: " + username);
	            //throw new UsernameNotFoundException(username);
	        }else{
	        	List<UserSession> existingSessions = this.sessionRepo.findActiveUserSession(domainUser.getId());
	        	if(existingSessions != null && existingSessions.size() > 0){
	        		// Check if multiple Sessions are allowed
	        		if(!this.sessionTracker.checkSessionMultiplicityAllowed(this.isPrivilegedUser(domainUser))){
	        			invalidatedSessions = this.sessionTracker.invalidateAllSessions(existingSessions);
	        			if(LOGGER.isInfoEnabled()){
	        				LOGGER.info("Invalidated existing session ["+invalidatedSessions.size()+"] for user "+domainUser.getId());
	        			}
	        		}
	        	}
	        }
		} catch (Exception ex) {
        	LOGGER.error("Exception : " + ex.getMessage(),ex);
            throw new AuthenticationServiceException(ex.getMessage(), ex);
        }
		return domainUser;
	}

	@Override
	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		LOGGER.info("Getting user details from loadUserDetails :: " );
		
		String username = token.getName();
		UserDetails domainUser = null;
		domainUser= loadUserByUsername(username);
		
		//LOGGER.info("Loaded user details object for ("+username+") ::");
		return domainUser;
	}
	
	private boolean isPrivilegedUser(AccountUser user){
		Set<UserRole> userRoles = user.getUserRole();
		boolean isPrivilegedRole = false;
		UserRole tmp = null;
		Iterator<UserRole> cursor = userRoles.iterator();
		while(cursor.hasNext()){
			tmp = cursor.next();
			if(tmp.isDefaultRole()){
				if(tmp.getRole().getPrivileged() == 1){
					isPrivilegedRole = true;
					break;
				}
			}
		}
		return isPrivilegedRole;
	}
	
	
}
