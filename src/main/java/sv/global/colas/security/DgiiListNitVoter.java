package sv.global.colas.security;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

public class DgiiListNitVoter implements AccessDecisionVoter<Object> {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	
	private HashMap<String, String> nitsPermitidos = new HashMap<String, String>();
	
	public DgiiListNitVoter() {
		super();
		nitsPermitidos.put("06142408661057", "06142408661057");
		nitsPermitidos.put("06142512851380", "06142512851380");
		nitsPermitidos.put("06142511721114", "06142511721114");
		nitsPermitidos.put("04282407690010", "04282407690010");
		nitsPermitidos.put("06140206731204", "06140206731204");
		nitsPermitidos.put("05150406560020", "05150406560020");
		nitsPermitidos.put("06141508630291", "06141508630291");
		nitsPermitidos.put("04020412520019", "04020412520019");
		nitsPermitidos.put("14061403620015", "14061403620015");
		nitsPermitidos.put("04030511550014", "04030511550014");
		nitsPermitidos.put("02022212731014", "02022212731014");
		nitsPermitidos.put("10113009570010", "10113009570010");
		nitsPermitidos.put("06142704540060", "06142704540060");
		nitsPermitidos.put("11140101871019", "11140101871019");
		nitsPermitidos.put("06141207911202", "06141207911202");
		nitsPermitidos.put("06142704901470", "06142704901470");
		nitsPermitidos.put("06142307701040", "06142307701040");
		nitsPermitidos.put("07151106721017", "07151106721017");		
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {		
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {		
		return true;
	}

	@Override

	//TODO: ver esta situación de la fecha que compara para entrar
	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> attributes) {
		
		if(authentication.getPrincipal()!=null){
			if(authentication.getPrincipal() instanceof DgiiUserDetailsImpl){
				DgiiUserDetailsImpl user = (DgiiUserDetailsImpl)authentication.getPrincipal();
				Date today = new Date();
				Date d12 = new Date();
				try {
					 d12 = sdf.parse("12/02/2015 00:00:00");					 
					 //today = sdf.parse("12/02/2015 00:00:03");
				} catch (ParseException e) {					
					e.printStackTrace();
				}				
				if(today.compareTo(d12)>0){
					return ACCESS_GRANTED;
				}else{
					if(nitsPermitidos.containsKey(user.getNit())){
						return ACCESS_GRANTED;
					}
				}				
			}				
		}
		
		return ACCESS_DENIED;
	}
	

}
