FROM jboss/keycloak-adapter-wildfly:1.9.5.Final


RUN /opt/jboss/wildfly/bin/add-user.sh admin dina --silent
#ADD mysql-connector-java-5.1.37-bin.jar /opt/jboss/wildfly/standalone/deployments/


ADD docker-configuration/wildfly-modules /opt/jboss/wildfly/modules/
ADD docker-configuration/wildfly-customization /opt/jboss/wildfly/customization/

USER root
RUN chmod +x /opt/jboss/wildfly/customization/execute.sh
RUN /opt/jboss/wildfly/customization/execute.sh

# Fix for Error: Could not rename /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/current 
RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history
RUN chown -R jboss:jboss /opt/jboss/wildfly/
USER jboss

ADD dina-data-service/target/collections.war /opt/jboss/wildfly/standalone/deployments/ 

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]








