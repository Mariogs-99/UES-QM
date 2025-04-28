package sv.global.colas.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;

public class Utils {

	public static String EMPTY_STRING = "";
	
	public static boolean isNullOrEmpty(Object obj) {
        if (obj == null || obj.toString().length() < 1 || obj.toString().equals(EMPTY_STRING)) {
            return true;
        }
        return false;
    }
	
	 public static Object nvl(Object obj, Object val) {
	        if (isNullOrEmpty(obj)) {
	            return val;
	        }
	        return obj;
	 }
         public String getTipoUsuario(Collection<GrantedAuthority> roles){
            String tipo = "ROLE_GC_R4"; // Tipo Tecnico
            for (GrantedAuthority rol : roles) {
                if(rol.getAuthority().equalsIgnoreCase("ROLE_GC_R3")) {
                    tipo = "ROLE_GC_R3";
                    break;
                }
            }
            return tipo;
        }
         public String getAdminUsuario(Collection<GrantedAuthority> roles){
            String tipo = "ROLE_GC_R2"; // Tipo Tecnico
            for (GrantedAuthority rol : roles) {
                if(rol.getAuthority().equalsIgnoreCase("ROLE_GC_R1")) {
                    tipo = "ROLE_GC_R1";
                    break;
                }
            }
            return tipo;
        }
        public String getTecnicoMesaEntrada(Collection<GrantedAuthority> roles){
            String tipo = "ROLE_GC_R4"; // Tipo Tecnico
            for (GrantedAuthority rol : roles) {
                if(rol.getAuthority().equalsIgnoreCase("ROLE_GC_R5")) {
                    tipo = "ROLE_GC_R5";
                    break;
                }
            }
            return tipo;
        }        
         
        public static String quitarGuionesNIT(String nit){
		
		String result="";
		try{    
                    if(nit!=null){
			String st[] = nit.split("-");
			for(int i=0;i<st.length;i++){
				result += st[i];
			}
                    }
		}catch(Exception ex){
                    ex.printStackTrace();
		}
		return result;
	}
        
        
        public static Date addMinutesToDate(int minutes, Date beforeTime){
	    final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

	    long curTimeInMs = beforeTime.getTime();
	    Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
	    return afterAddingMins;
	}
        
        
        public static String formatNit(String nit){
		String formattetNit="";
		
		try{
                    if(nit!=null){    
			formattetNit+= nit.substring(0,4)+"-";
			formattetNit+= nit.substring(4,10)+"-";
			formattetNit+= nit.substring(10,13)+"-";
			formattetNit+= nit.substring(13,14);
                    }
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return formattetNit;
	}
        
        
        public static String formatTelefono(String telefono){
		String formattetTelefono="";
		
		try{
                    
                    if(telefono!=null){
                        formattetTelefono+= telefono.substring(0,4)+"-";
			formattetTelefono+= telefono.substring(4,8);
                    }

		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return formattetTelefono;
	}
        
        
        
        public Boolean contains(String value, Object obj){
            Boolean result=false;
            Field[] field;
            try{
                Class<?> clase = obj.getClass();
                field = clase.getDeclaredFields();
                for(Field f : field){
                    f.setAccessible(true);
                    if(!f.getName().equals("serialVersionUID") 
                            && !f.getName().equals("idTiquete")
                            && !f.getName().equals("idReservaCita")
                            && !f.getName().equals("idTramite")
                            && !f.getName().equals("idServicio")
                            && !f.getName().equals("prioridad")){
                        System.out.println("Name field: "+f.getName()+", value field: "+f.get(obj).toString()+", compare value: "+value);
                        if(f.get(obj).toString().toLowerCase().contains(value.toLowerCase())){
                            result = true;
                        }
                    }
                }
                
            }catch(Exception ex){
                ex.printStackTrace();
            }   
            
            return result;
        
        }
        
	
}
