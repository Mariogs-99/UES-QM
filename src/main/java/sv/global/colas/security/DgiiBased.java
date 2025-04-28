package sv.global.colas.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

public class DgiiBased extends AbstractAccessDecisionManager {

	@Deprecated
	public DgiiBased() {
	}
	 
	public DgiiBased(List<AccessDecisionVoter> decisionVoters) {
        super(decisionVoters);
    }
	
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		int deny = 0;		
		boolean dgiiDeny = false;
		
		for(AccessDecisionVoter voter : getDecisionVoters()) {
			int result = voter.vote(authentication, object, configAttributes);
			if(voter instanceof DgiiListNitVoter){
				if(result==AccessDecisionVoter.ACCESS_DENIED){
					deny++;
					dgiiDeny = true;
				}
			}else{
				switch (result) {	            
	            case AccessDecisionVoter.ACCESS_DENIED:
	                deny++;
	                break;
	            default:
	                break;
	            }
			}
		}
		
		if(!dgiiDeny && deny==0){
			return;
		}
		
		throw new AccessDeniedException(messages.getMessage("AbstractAccessDecisionManager.accessDenied",
                    "Access is denied"));
        	
		// To get this far, every AccessDecisionVoter abstained
		//checkAllowIfAllAbstainDecisions();
		
	}

	
	
    
}
