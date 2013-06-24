/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.examples.oauth2connectorexample;

import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.oauth.OAuth2;
import org.mule.api.annotations.oauth.OAuthAccessToken;
import org.mule.api.annotations.oauth.OAuthConsumerKey;
import org.mule.api.annotations.oauth.OAuthConsumerSecret;
import org.mule.api.annotations.oauth.OAuthProtected;
import org.mule.api.annotations.oauth.OAuthScope;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.examples.oauth2connectorexample.client.FourSquareClient;
import org.mule.examples.oauth2connectorexample.entities.UsersListResponse;
import org.mule.examples.oauth2connectorexample.exception.Oauth2ConnectorExampleException;
import org.mule.examples.oauth2connectorexample.exception.Oauth2ConnectorExampleTokenExpiredException;

/**
 * Cloud Connector
 * 
 * @author MuleSoft, Inc.
 */
@Connector(name = "oauth2example", schemaVersion = "1.0-SNAPSHOT", friendlyName = "OAuth 2 Example")
@OAuth2(authorizationUrl = "https://foursquare.com/oauth2/authenticate", accessTokenUrl = "https://foursquare.com/oauth2/access_token")
public class Oauth2ExampleConnector {

	private FourSquareClient client;
	
	public Oauth2ExampleConnector() {
		client = new FourSquareClient();
	}
	
	/**
	 * The consumer ApiKey
	 */
	@Configurable
	@OAuthConsumerKey
	private String apiKey;
	
	/**
	 * The consumer ApiSecret
	 */
	@Configurable
	@OAuthConsumerSecret
	private String apiSecret;

	/**
     * The OAuth scopes you want to request
     */
	@Configurable
	@OAuthScope
	@Optional
	@Default("")
    private String scope;
    
    @OAuthAccessToken
    private String accessToken;

    
    /**
     * Retrieves the User List for a specific user
     * 
     * {@sample.xml ../../../doc/Oauth2Example-connector.xml.sample oauth2example:users-get-list}
     * 
     * @param userId The user from which the list of users will be obtained
     * @param group The group of users to filter by
     * @param location The locations near by of users to filter by
     * @return A {@link UsersListResponse} with the list of users
     * @throws Oauth2ConnectorExampleTokenExpiredException If the token has expired or is no longer valid
     * @throws Oauth2ConnectorExampleException If the service responds with an error
     */
    @OAuthProtected
	@Processor
    public UsersListResponse usersGetList(@Optional @Default("self") String userId, @Optional @Default("") String group, @Optional @Default("") String location)
    		 throws Oauth2ConnectorExampleTokenExpiredException, Oauth2ConnectorExampleException {
		return client.usersGetList(accessToken, userId, group, location);
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
