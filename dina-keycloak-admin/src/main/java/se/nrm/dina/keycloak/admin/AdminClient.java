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
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
//import org.keycloak.admin.client.Keycloak;  
//import org.keycloak.admin.client.resource.RoleMappingResource; 
//import org.keycloak.representations.idm.ClientRepresentation;
//import org.keycloak.representations.idm.CredentialRepresentation;
//import org.keycloak.representations.idm.RoleRepresentation;
//import org.keycloak.representations.idm.UserRepresentation; 

/**
 *
 * @author idali
 */
public class AdminClient {
    
    
    private static final String MASTER_REALM = "master";
    private static final String DINA_REALM = "dina";
    
    private static final String MASTER_ADMIN_USERNAME = "admin";
    private static final String MASTER_ADMIN_PASSWORD = "dina";
    
    private static final String MASTER_AUTH_CLIENT_ID = "security-admin-console";
    
    private Keycloak kc;
    private List<RoleRepresentation> roleList;
    
    public void uploadUser(String url, String tsvFile) {
        
        TSVReader fileReader = new TSVReader(tsvFile);
        List<String[]> list = fileReader.readTSVFile();
        list.remove(0);
        
        List<String> groups = new ArrayList();
        groups.add("admin");
        groups.add("user");
         
        Map<String, List<String>> roleMap = new HashMap<>();
        roleMap.put("collections", groups);
        roleMap.put("dina-rest", groups);

        Map<String, Object> attributes = new HashMap<>();

        kc = Keycloak.getInstance(
                url,
                MASTER_REALM, // the realm to log in to
                MASTER_ADMIN_USERNAME, MASTER_ADMIN_PASSWORD, // the user
                MASTER_AUTH_CLIENT_ID);
  
//        RoleRepresentation rr = new RoleRepresentation();
//        rr.setName("admin");
//        rr.setScopeParamRequired(false);
//         
//        kc.realm(DINA_REALM).roles().create(rr); 
////        kc.realm(DINA_REALM).clients().get("dina-rest").roles().create(rr); 
//         
//        rr = new RoleRepresentation();
//        rr.setName("user");
//        rr.setScopeParamRequired(false);
//         
//        kc.realm(DINA_REALM).roles().create(rr); 
//        kc.realm(DINA_REALM).clients().get("dina-rest").roles().create(rr); 
      
        
//        roleList = kc.realm(DINA_REALM).roles().list(); 
         
        List<String> userIds = new ArrayList<>(); 
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
                     
                    System.out.println("user -> " + userId + "--" + password);
                    
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
                    String uId = StringUtils.substringAfterLast((String)response.getHeaders().get("Location").get(0), "/"); 
                    userIds.add(uId); 
                    response.close();
                });
            
               userIds.stream()
                .forEach(u -> {
                    RoleMappingResource r = kc.realm(DINA_REALM).users().get(u).roles();
                    r.realmLevel().add(roleList); 
                });

                List<ClientRepresentation> clients = kc.realm(DINA_REALM).clients().findAll();
                clients.stream()
                        .forEach(c -> { 
                            System.out.println("c -> " + c.getClientId());
                        }); 
    }
     
}
