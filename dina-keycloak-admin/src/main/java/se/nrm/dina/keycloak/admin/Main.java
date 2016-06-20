/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.keycloak.admin;
  
/**
 *
 * @author idali
 */
public class Main {
    
    private static final String KEYLOAK_AUTH_PATH = "https://beta-sso.dina-web.net/auth";
    private static final String TSV_PATH = "/Users/idali/Desktop/keycloakAdmin/users 2.tsv";

    public static void main(String[] args) {
      
        String serviceUrl;
        String tsvPath;
        if (args != null && args.length >= 2) {
            serviceUrl = args[0];
            tsvPath = args[1];
            System.out.println(serviceUrl);
            System.out.println(tsvPath);
            new AdminClient().uploadUser(serviceUrl, tsvPath);
        } else { 
            System.out.println("You need specify keycloak url and tsv file path");
            System.exit(0);
        }  
    }
}
