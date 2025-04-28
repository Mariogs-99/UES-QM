/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sv.global.colas.repositories;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import sv.global.colas.pojos.security.PersonW;

/**
 *
 * 
 */
public class PersonWRepositoryImpl implements PersonWRepository {

    
    private final static Log log = LogFactory.getLog(PersonWRepositoryImpl.class);
//    @Autowired
//    private DefaultLdapAuthoritiesPopulator authoritiesPopulator;
    
    @Override
    public PersonW findByPrimaryKey(String name) {
        PersonW person = new PersonW();
        boolean personEx = false;
        String query = "uid=" + name;
        String attribute = "mail";
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://oidtest.mh.gob.sv:390/dc=mh,dc=gob,dc=sv");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=consultaOID,cn=Users,dc=mh,dc=gob,dc=sv");
        env.put(Context.SECURITY_CREDENTIALS, "T0d0sM3ntim0sT0d0sM3ntim0s");
        DirContext ctx = null;
        Name dn = buildDn(name);
        Attributes attribs = null;
        DirContextAdapter context = new DirContextAdapter(dn);
        try {
            ctx = new InitialDirContext(env);
            SearchControls ctrl = new SearchControls();
            ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration enumeration = ctx.search("", query, ctrl);
            while (enumeration.hasMore()) {
                SearchResult result = (SearchResult) enumeration.next();
                attribs = result.getAttributes();
                person = mapFromContext(attribs, person);
                personEx = true;
            }
            if (personEx) {
                context = mapToContextPI(person, context);
                person.setAuthorities(false);
                String[] searchAttributes = new String[1];
                searchAttributes[0] = "uniqueMember";
                Attributes attributes = ctx.getAttributes(buildGrupoDn("ROLE_"), searchAttributes);
                if (attributes != null) {
                    Attribute memberAtts = attributes.get("uniqueMember");
                    if (memberAtts != null) {
                        for (NamingEnumeration vals = memberAtts.getAll();
                                vals.hasMoreElements();) {
                            if ((name).equalsIgnoreCase(getUserUID((String) vals.nextElement()))) {
                                person.setAuthorities(true);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            ctx.close();
        } catch (Exception e) {
        }
        return person;
    }
    
    protected Name buildDn(String username) {
        DistinguishedName dn = new DistinguishedName();
        dn.add("cn", "users");
        dn.add("cn", username);
        return dn;
    }
    protected Name buildGrupoDn(String group) {
        DistinguishedName dn = new DistinguishedName();
        dn.add("cn", "apps");
        dn.add("cn", group);
        return dn;
    }

    
    
    public PersonW mapFromContext(Attributes attribs, PersonW person) {
        try {
            person.setEmail((((BasicAttribute) attribs.get("mail")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- cargo fail!!", e);
        }
        try {
            person.setCargo((((BasicAttribute) attribs.get("nom_cargo_serv")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- cargo fail!!", e);
        }
        try {
            person.setGivenName((((BasicAttribute) attribs.get("givenname")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- givenname fail!", e);
        }

        try {
            person.setMiddleName((((BasicAttribute) attribs.get("middlename")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- middlename fail!", e);
        }
        try {
            person.setSn((((BasicAttribute) attribs.get("sn")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- sn fail!", e);
        }

        try {
            person.setDisplayName((((BasicAttribute) attribs.get("displayname")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- displayName fail!", e);
        }
        try {
            person.setTipoUsuario((((BasicAttribute) attribs.get("tipo_usuario")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- tipoUsuario fail!", e);
        }
        try {
            person.setEmailPi((((BasicAttribute) attribs.get("email_pi")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- emailPi fail!", e);
        }
        try {
            person.setEmailServ((((BasicAttribute) attribs.get("email_pi")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- emailServ fail!", e);
        }
        try {
            person.setCtrlchgpwd("1".equals((((BasicAttribute) attribs.get("ctrlchgpwd")).getAll()).next().toString()));
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- ctrlchgpwd fail!", e);
        }
        try {
            person.setCadmTrib((((BasicAttribute) attribs.get("adminTrib")).getAll()).next().toString());
        } catch (Exception e) {
            person.setCadmTrib("01");
            log.error("LDAP[ContextToPerson]- cadmTrib fail!", e);
        }
        try {
            person.setBanderas((((BasicAttribute) attribs.get("admintrib")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- banderas fail!", e);
        }
        try {
            person.setNitRepresentante((((BasicAttribute) attribs.get("representante")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- nitRepresentante fail!", e);
        }
        try {
            person.setNit((((BasicAttribute) attribs.get("nit")).getAll()).next().toString());
        } catch (Exception e) {
            person.setNit("NONE");
            log.error("LDAP[ContextToPerson]- nit fail!", e);
        }
        try {
            person.setUbicacionFisica((((BasicAttribute) attribs.get("ubicafisica")).getAll()).next().toString());
        } catch (Exception e) {
            person.setUbicacionFisica("01");
            log.error("LDAP[ContextToPerson]- ubicacionFisica fail!", e);
        }
        try {
            person.setDui((((BasicAttribute) attribs.get("dui")).getAll()).next().toString());
        } catch (Exception e) {
            person.setDui("N/A");
            log.error("LDAP[ContextToPerson]- dui fail!", e);
        }
        try {
            person.setClave((((BasicAttribute) attribs.get("userpassword")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- clave fail!", e);
        }
        try {
            person.setRequerimiento((((BasicAttribute) attribs.get("requerimiento")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- requerimiento fail!", e);
        }
        try {
            person.setTipoInstitucion((((BasicAttribute) attribs.get("ubicafisica")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- Institucion fail!", e);
        }
        try {
            person.setRol((((BasicAttribute) attribs.get("uniqueMember")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- rol fail!", e);
        }
        try {
            person.setPreferredlanguage((((BasicAttribute) attribs.get("preferredlanguage")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- Preferencia de lenguaje fail!", e);
        }
        try {
            person.setFh_ingreso_serv((((BasicAttribute) attribs.get("fh_ingreso_serv")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- Fecha de ingreso fail!", e);
        }
        try {
            person.setUidNumber((((BasicAttribute) attribs.get("uidNumber")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- uidNumber fail!", e);
        }
        try {
            person.setUnidad((((BasicAttribute) attribs.get("unidad")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- unidad fail!", e);
        }
        try {
            person.setGidNumber((((BasicAttribute) attribs.get("gidNumber")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- gidNumber fail!", e);
        }
        try {
            person.setUsername((((BasicAttribute) attribs.get("cn")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- username fail!", e);
        }
        try {
            person.setOrclisenabled((((BasicAttribute) attribs.get("orclisenabled")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- orclisenabled fail!", e);
        }
        try {
            person.setPasaporteUsr((((BasicAttribute) attribs.get("pasaporte_usr")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- pasaporte_usr fail!", e);
        }
        try {
            person.setPasaporteRep((((BasicAttribute) attribs.get("pasaporte_rep")).getAll()).next().toString());
        } catch (Exception e) {
            log.error("LDAP[ContextToPerson]- pasaporte_rep fail!", e);
        }

        return person;
    }
    
    protected DirContextAdapter mapToContextPI(PersonW person, DirContextAdapter context) {

        try {
            context.setAttributeValue("ip_registro", person.getIpUsuario());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- ip_registro fail!", e);
        }
        try {
            context.setAttributeValue("fh_ingreso_pi", person.getFh_ingreso_serv());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- fh_ingreso_pi fail!", e);
        }
        try {
            context.setAttributeValue("representante", person.getNitRepresentante());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- representante fail!", e);
        }
        try {
            context.setAttributeValue("pasaporte_usr", person.getPasaporteUsr());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- pasaporte_usr fail!", e);
        }
        try {
            context.setAttributeValue("pasaporte_rep", person.getPasaporteRep());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- pasaporte_rep fail!", e);
        }
        return mapToContextSW(person, context);
    }
    protected DirContextAdapter mapToContextSW(PersonW person, DirContextAdapter context) {

        context.setAttributeValues("objectclass",
                new String[]{"top",
            "person",
            "organizationalPerson",
            "inetOrgPerson",
            "account",
            "dgiiuser",
            "mhuser",
            "orcladuser",
            "orcldasconfigpublicgroup",
            "orcladuser",
            "orcluser",
            "orcluserv2",
            "posixaccount"
        });
        return mapToContext(person, context);
    }
    private String getUserUID(String userDN) {
        int start = userDN.indexOf("=");
        int end = userDN.indexOf(",");

        if (end == -1) {
            end = userDN.length();
        }

        return userDN.substring(start + 1, end);
    }
    
        protected DirContextAdapter mapToContext(PersonW person, DirContextAdapter context) {
        try {
            context.setAttributeValue("cn", person.getNit());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- cn fail!", e);
        }
        try {
            context.setAttributeValue("sn", person.getSn());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- sn fail!", e);
        }
        try {
            context.setAttributeValue("nom_cargo_serv", person.getCargo());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- cargo fail!!", e);
        }
        try {
            context.setAttributeValue("givenname", person.getGivenName());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- givenname fail!", e);
        }
        try {
            context.setAttributeValue("middlename", person.getMiddleName());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- middlename fail!", e);
        }
        try {
            context.setAttributeValue("tipo_usuario", person.getTipoUsuario());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- tipoUsuario fail!", e);
        }
        try {
            context.setAttributeValue("email_pi", person.getEmailPi());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- emailPi fail!", e);
        }
        try {
            context.setAttributeValue("mail", person.getEmail());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- email fail!", e);
        }
        try {
            context.setAttributeValue("email_serv", person.getEmailServ());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- emailServ fail!", e);
        }
        try {
            context.setAttributeValue("ctrlchgpwd", person.getCtrlchgpwd() ? "1" : "0");
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- ctrlchgpwd fail!", e);
        }
        try {
            context.setAttributeValue("adminTrib", person.getCadmTrib());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- cadmTrib fail!", e);
        }

        try {
            context.setAttributeValue("nit", person.getNit());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- nit fail!", e);
        }
        try {
            context.setAttributeValue("physicaldeliveryofficename", person.getUbicacionFisica());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- physicaldeliveryofficename fail!", e);
        }
        try {
            context.setAttributeValue("dui", person.getDui());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- dui fail!", e);
        }
        try {
            context.setAttributeValue("userpassword", person.getClave());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- clave fail!", e);
        }
        try {
            context.setAttributeValue("requerimiento", person.getRequerimiento());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- requerimiento fail!", e);
        }
        try {
            context.setAttributeValue("uniqueMember", person.getRol());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- uniqueMember fail!", e);
        }
        try {
            context.setAttributeValue("ubicafisica", person.getTipoInstitucion());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- Institucion fail!", e);
        }
        try {
            context.setAttributeValue("displayname", person.getDisplayName());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- Nombre Completo fail!", e);
        }
        try {
            context.setAttributeValue("preferredlanguage", person.getPreferredlanguage());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- Preferencia de lenguaje fail!", e);
        }
        try {
            context.setAttributeValue("fh_ingreso_serv", person.getFh_ingreso_serv());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- Fecha de ingreso fail!", e);
        }
        try {
            context.setAttributeValue("uidNumber", person.getUidNumber());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- uidNumber fail!", e);
        }
        try {
            context.setAttributeValue("unidad", person.getUnidad());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- unidad fail!", e);
        }
        try {
            context.setAttributeValue("gidNumber", person.getGidNumber());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- gidNumber fail!", e);
        }

        try {
            context.setAttributeValue("uid", person.getNit());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- cn fail!", e);
        }
        try {
            context.setAttributeValue("orclSAMAccountName", person.getNit());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- orclsamaccountname fail!", e);
        }
        try {
            context.setAttributeValue("homeDirectory", person.getNit());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- orclsamaccountname fail!", e);
        }
        try {
            context.setAttributeValue("orclisenabled", person.getOrclisenabled());
        } catch (Exception e) {
            log.error("LDAP[PersonToContext]- orclisenabled fail!", e);
        }

        return context;
    }

}
