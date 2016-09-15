# camunda-rest-cas-auth
Camunda REST API CAS SSO Authentication Provider with user injection for debugging.

Rest Engine Library with class files: https://app.camunda.com/nexus/content/groups/public/org/camunda/bpm/camunda-engine-rest/7.4.0/

Installation:

1. Import into Eclipse with Maven support.
2. Add camunda engine and rest engine jar to the build path.
3. Build a library jar file.
4. Put the result in the webapp's classpath
5. Put Apache commons-logging in Tomcat's classpath http://commons.apache.org/proper/commons-logging/download_logging.cgi

Activation in the webapp's web.xml:
* comment out the "Http Basic Authentication Filter"

* add the following filter instead

(CAS filters themselves are omitted)
```
    <!-- REST Authentication filter -->
    <filter>
      <filter-name>camunda-auth</filter-name>
      <filter-class>
        org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter
      </filter-class>
      <init-param>
        <param-name>authentication-provider</param-name>
        <param-value>de.hofuniversity.iisys.camunda.rest.auth.CASAuthenticationProvider</param-value>
      </init-param>
    </filter>
    <filter-mapping>
      <filter-name>camunda-auth</filter-name>
      <url-pattern>/*</url-pattern>
    </filter-mapping>
```
