/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.keycloak.admin;
  
import java.util.Scanner;

/**
 *
 * @author idali
 */
public class Main {
                                                      
//    private static final String KEYLOAK_AUTH_PATH = "https://beta-sso.dina-web.net/auth";
//    private static final String TSV_PATH = "/Users/idali/Desktop/keycloakAdmin/users 2.tsv";
//    /Users/idali/keycloak_admin_test/users.tsv

    public static void main(String[] args) {
      
        String serviceUrl;
        String tsvPath;
         
        
        if (args != null && args.length >= 2) {
            serviceUrl = args[0];
            tsvPath = args[1];
            System.out.println(serviceUrl);
            System.out.println(tsvPath);
            
        } else { 
            Scanner scanner = new Scanner(System.in); 
            System.out.print("Enter keycloak url: "); 
            serviceUrl = scanner.next();
            
            System.out.print("Enter tsv file path: "); 
            tsvPath = scanner.next(); 
//            System.out.println("You need specify keycloak url and tsv file path");
//            System.exit(0);
        }  
        new AdminClient().uploadUser(serviceUrl, tsvPath);
    }
}
