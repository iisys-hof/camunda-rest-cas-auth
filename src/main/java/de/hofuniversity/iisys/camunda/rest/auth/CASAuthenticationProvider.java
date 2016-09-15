package de.hofuniversity.iisys.camunda.rest.auth;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationProvider;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;

public class CASAuthenticationProvider implements AuthenticationProvider
{
    private static final String PROPERTIES = "rest-cas-sso";
    
    private static final String DEBUG_MODE = "sso.rest.debug";
    private static final String DEBUG_USER = "sso.rest.debug_user";
    
    private final boolean fDebug;
    
    private final String fDebugUser;
    
    public CASAuthenticationProvider()
    {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES,
                Locale.getDefault(), loader);
        
        fDebug = Boolean.parseBoolean(rb.getString(DEBUG_MODE));
        fDebugUser = rb.getString(DEBUG_USER);
    }
    
    @Override
    public void augmentResponseByAuthenticationChallenge(
        HttpServletResponse arg0, ProcessEngine arg1)
    {
        // TODO send to CAS?
    }

    @Override
    public AuthenticationResult extractAuthenticatedUser(
        HttpServletRequest req, ProcessEngine engine)
    {
        boolean success = true;
        String user = req.getRemoteUser();
        
        // debug mode: if no user is detected, log in a default user
        if(fDebug && user == null && fDebugUser != null)
        {
            user = fDebugUser;
        }
        
        AuthenticationResult result = null;
        
        if(user != null)
        {
            if(success)
            {
                result = AuthenticationResult.successful(user);
            }
            else
            {
                result = AuthenticationResult.unsuccessful(user);
            }
        }
        else
        {
            result = AuthenticationResult.unsuccessful();
        }
        
        return result;
    }

}
