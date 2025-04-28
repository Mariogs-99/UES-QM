package sv.global.colas.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

public class DgiiLdapUserDetailsMapper extends LdapUserDetailsMapper {

    private static final String DEFAULT_NIT = "00000000000000";
    private static final String DEFAULT_ADM_TRIB = "01";
    private static final String DEFAULT_UBICACION_FISICA = "Sin ubicacion";
    private static final String DEFAULT_UNIDAD = "01010";

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx,
            String username, Collection<? extends GrantedAuthority> authorities) {

        String newUsername = username.toLowerCase();
        DgiiUserDetailsImpl userDetails = new DgiiUserDetailsImpl(
                (LdapUserDetails) super.mapUserFromContext(ctx, username,
                authorities));

        userDetails.setEmail(((String) ctx.getObjectAttribute("mail")).trim());
        userDetails.setDisplayName((String) ctx
                .getObjectAttribute("displayname"));
        String nit = (String) ctx.getObjectAttribute("nit");
        String ubicacionFisica = (String) ctx.getObjectAttribute("ubicaFisica");
        String adminTri = (String) ctx.getObjectAttribute("adminTrib");
        String cunidad = (String) ctx.getObjectAttribute("unidad");


        if (!isNullOrEmpty(nit)) {
            userDetails.setNit(nit.trim());
        } else {
            userDetails.setNit(DEFAULT_NIT);
        }

        if (!isNullOrEmpty(cunidad)) {
            userDetails.setUnidad(cunidad.trim());
        } else {
            userDetails.setUnidad(DEFAULT_UNIDAD);
        }

        if (!isNullOrEmpty(ubicacionFisica)) {
            userDetails.setUbicacionFisica(ubicacionFisica);
        } else {
            userDetails.setUbicacionFisica(DEFAULT_UBICACION_FISICA);
        }

        if (!isNullOrEmpty(adminTri)) {
            userDetails.setCadmTrib(adminTri.trim());
        } else {
            userDetails.setCadmTrib(DEFAULT_ADM_TRIB);
        }

        //Transformo GrantedAuthorities en objetos AsRol
		/*List<AsRol> roles = new ArrayList<AsRol>();
         for (GrantedAuthority authority : ud.getAuthorities()) {
         AsRol rol = asRolService.findBySRol(authority.getAuthority());
         if(!Utils.isNullOrEmpty(rol)){
         roles.add(rol);
         }			
         }*/
        
        //Optimizando pues la implementacion original hacia muy lento el login
        ArrayList<String> al = new ArrayList<String>();
        for (GrantedAuthority au : userDetails.getAuthorities()) {
            al.add(au.getAuthority());
        }
        String[] stockArr = al.toArray(new String[0]);
        //userDetails.setRoles(asRolService.findAllBySRol(stockArr));
        //userDetails.setA
        return userDetails;
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null || obj.toString().length() < 1
                || obj.toString().equals("")) {
            return true;
        }
        return false;
    }
    
   
}