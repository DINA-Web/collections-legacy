/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.keycloak.admin;
 
import java.util.ArrayList;   
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder; 
import org.keycloak.representations.idm.CredentialRepresentation; 
import org.keycloak.representations.idm.UserRepresentation; 

/**
 *
 * @author idali
 */
public class AdminClient {
    
    
    private static final String MASTER_REALM = "master";
    private static final String DINA_REALM = "dina"; 
    private static final String ADMIN_REALM = "admin-cli";
    private static final String URL = "https://beta-sso.dina-web.net/auth";
    
    private static final String MASTER_ADMIN_USERNAME = "admin";
    private static final String MASTER_ADMIN_PASSWORD = "dina";
     
    private Keycloak kc; 
    public void uploadUser(String url, String tsvFile) {
        
        TSVReader fileReader = new TSVReader(tsvFile);
        List<String[]> list = fileReader.readTSVFile();
        list.remove(0);
       
        List<String> groups = new ArrayList();
        groups.add("admin"); 
         
        Map<String, List<String>> roleMap = new HashMap<>();
        roleMap.put("collections", groups);
        roleMap.put("dina-rest", groups);
         
        Map<String, Object> attributes = new HashMap<>();
        
        kc = KeycloakBuilder.builder()
                .serverUrl(url) //
                .realm(MASTER_REALM)//
                .username(MASTER_ADMIN_USERNAME) //
                .password(MASTER_ADMIN_PASSWORD) //
                .clientId(ADMIN_REALM) // 
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()) //
                .build();
 
        List<String> userIds = new ArrayList<>(); 
        System.out.println("list :" + list);
        list.stream()
                .forEach((String[] r) -> { 
                    String agentId = "0";
                    if(r[0] != null) {
                        agentId = r[0];
                    }
                    String specifyUserId = r[1];
                    String email = StringUtils.substringBetween(r[2], "\"", "\"" );
                    String userId = StringUtils.substringBetween(r[3].trim(), "\"", "\"" );
                    String password = StringUtils.substringBetween(r[5].trim(), "\"", "\"" );
                       
                    List<String> attList = new ArrayList<>();
                    attList.add(userId);
                    attributes.put("userid", attList);
                    
                    List<String> attAgent = new ArrayList();
                    attAgent.add(agentId);
                    attributes.put("agentId", attAgent);
                    
                    List<String> attSpecify = new ArrayList();
                    attSpecify.add(specifyUserId);
                    attributes.put("specifyId", attSpecify);

                    CredentialRepresentation credential = new CredentialRepresentation();
                    credential.setType(CredentialRepresentation.PASSWORD); 
                    credential.setValue(password);
                    credential.setTemporary(false);
              
                    UserRepresentation user = new UserRepresentation();
                    user.setUsername(userId);
                    user.setEmail(email); 
                    user.setEnabled(true);
                       
                    user.setRealmRoles(groups); 
                    user.setClientRoles(roleMap);
                    user.setAttributes(attributes);  
                     
                    List<CredentialRepresentation> clist = new ArrayList<>();
                    clist.add(credential);
              
                    user.setCredentials(clist); 
                    javax.ws.rs.core.Response response = kc.realm(DINA_REALM).users().create(user);
                     
                    if(response != null) {
                        response.close();
                    }  
                });
             
    }
     
}
