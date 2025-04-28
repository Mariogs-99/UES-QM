/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author josue.henriquez
 */
public class PropertyFileSecurityBuilder implements DgiiSecurityBuilder{

    Resource application;
    Properties prop = new Properties();

    public PropertyFileSecurityBuilder() throws IOException {
        application = new ClassPathResource("/application.properties");
        prop.load(application.getInputStream());
        Resource resource = new ClassPathResource("/" + (String) prop.get("dgii.security.property.file.builder"));
        prop.clear();
        prop.load(resource.getInputStream());
    }

    @Override
    public Map<String, String> getUrlsAndRoles() {
        HashMap<String, String> urls = new HashMap<String, String>();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            urls.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return urls;
    }
}
