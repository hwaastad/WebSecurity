/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.websecurity.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
public class SmartGuestLoginModule implements LoginModule {

    private static final Logger LOGGER = Logger.getLogger(SmartGuestLoginModule.class.getName());
    private CallbackHandler callbackHandler;
    private Subject subject;
    private Map sharedState;
    private Map options;
    private boolean debug = true;
    private boolean succeeded = false;
    private boolean commitSucceeded = false;
    private String username = null;
    private char[] password = null;
    private JAASUserPrincipal userPrincipal = null;
    private JAASPasswordPrincipal passwordPrincipal = null;

    public SmartGuestLoginModule() {
        super();
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;

        debug = "true".equalsIgnoreCase((String) options.get("debug"));

    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("Error: no CallbackHandler available "
                    + "to garner authentication information from the user");
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("username");
        callbacks[1] = new PasswordCallback("password: ", false);

        try {

            callbackHandler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            password = ((PasswordCallback) callbacks[1]).getPassword();

            if (debug) {
                LOGGER.info("Username : " + username);
                LOGGER.info("Password : " + password);
            }

            if (username == null || password == null) {
                LOGGER.severe("Callback handler does not return login data properly");
                throw new LoginException("Callback handler does not return login data properly");
            }

            if (isValidUser()) { //validate user.
                succeeded = true;
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        if (succeeded == false) {
            return false;
        } else {
            userPrincipal = new JAASUserPrincipal(username);
            if (!subject.getPrincipals().contains(userPrincipal)) {
                subject.getPrincipals().add(userPrincipal);
                LOGGER.info("User principal added:" + userPrincipal);
            }
            passwordPrincipal = new JAASPasswordPrincipal(new String(password));
            if (!subject.getPrincipals().contains(passwordPrincipal)) {
                subject.getPrincipals().add(passwordPrincipal);
                LOGGER.info("Password principal added: " + passwordPrincipal);
            }

            //populate subject with roles.
            List<String> roles = getRoles();
            for (String role : roles) {
                JAASRolePrincipal rolePrincipal = new JAASRolePrincipal(role);
                if (!subject.getPrincipals().contains(rolePrincipal)) {
                    subject.getPrincipals().add(rolePrincipal);
                    LOGGER.info("Role principal added: " + rolePrincipal);
                }
            }

            commitSucceeded = true;

            LOGGER.info("Login subject were successfully populated with principals and roles");

            return true;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        if (succeeded == false) {
            return false;
        } else if (succeeded == true && commitSucceeded == false) {
            succeeded = false;
            username = null;
            if (password != null) {
                password = null;
            }
            userPrincipal = null;
        } else {
            logout();
        }
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        succeeded = false;
        succeeded = commitSucceeded;
        username = null;
        if (password != null) {
            for (int i = 0; i < password.length; i++) {
                password[i] = ' ';
                password = null;
            }
        }
        userPrincipal = null;
        return true;
    }

    private List<String> getRoles() {

//      Connection con = null;
//      ResultSet rs = null;
//      PreparedStatement stmt = null;

        List<String> roleList = new ArrayList<String>();
        roleList.add("SuperAdmin");

//      try {
//          con = getConnection();
//          String sql = (String)options.get("roleQuery");
//          stmt = con.prepareStatement(sql);
//          stmt.setString(1, username);
//   
//          rs = stmt.executeQuery();
//   
//          if (rs.next()) { 
//              roleList.add(rs.getString("rolename")); 
//          }
//      } catch (Exception e) {
//          LOGGER.error("Error when loading user from the database " + e);
//          e.printStackTrace();
//      } finally {
//           try {
//               rs.close();
//           } catch (SQLException e) {
//               LOGGER.error("Error when closing result set." + e);
//           }
//           try {
//               stmt.close();
//           } catch (SQLException e) {
//               LOGGER.error("Error when closing statement." + e);
//           }
//           try {
//               con.close();
//           } catch (SQLException e) {
//               LOGGER.error("Error when closing connection." + e);
//           }
//       }
        return roleList;
    }

    private boolean isValidUser() throws LoginException {
        return true;

//      String sql = (String)options.get("userQuery");
//      Connection con = null;
//      ResultSet rs = null;
//      PreparedStatement stmt = null;
//  
//      try {
//          con = getConnection();
//          stmt = con.prepareStatement(sql);
//          stmt.setString(1, username);
//          stmt.setString(2, new String(password));
//   
//          rs = stmt.executeQuery();
//   
//          if (rs.next()) { //User exist with the given user name and password.
//              return true;
//          }
//       } catch (Exception e) {
//           LOGGER.error("Error when loading user from the database " + e);
//           e.printStackTrace();
//       } finally {
//           try {
//               rs.close();
//           } catch (SQLException e) {
//               LOGGER.error("Error when closing result set." + e);
//           }
//           try {
//               stmt.close();
//           } catch (SQLException e) {
//               LOGGER.error("Error when closing statement." + e);
//           }
//           try {
//               con.close();
//           } catch (SQLException e) {
//               LOGGER.error("Error when closing connection." + e);
//           }
//       }
//       return false;
    }
}
