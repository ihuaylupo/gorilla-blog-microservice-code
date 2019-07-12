/**
 * 
 */
package com.huaylupo.cognito.security.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ihuaylupo
 * @version
 * @since Jun 25, 2018
 */
public class SpringSecurityUser {

	private String username;
	private String password;
	private String email;
    private String accessToken;
    private Integer expiresIn;
    private String tokenType;
    private String refreshToken;
    private String idToken;
	private Date lastPasswordReset;
	private Boolean accountNonExpired = true;
	private Boolean accountNonLocked = true;
	private Boolean credentialsNonExpired = true;
	private Boolean enabled = true;


	public SpringSecurityUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}


	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	/**
	 * @return the expiresIn
	 */
	public Integer getExpiresIn() {
		return expiresIn;
	}


	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}


	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}


	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}


	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}


	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}


	/**
	 * @return the idToken
	 */
	public String getIdToken() {
		return idToken;
	}


	/**
	 * @param idToken the idToken to set
	 */
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public Date getLastPasswordReset() {
		return this.lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}


	@JsonIgnore
	public Boolean getAccountNonExpired() {
		return this.accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonExpired() {
		return this.getAccountNonExpired();
	}

	@JsonIgnore
	public Boolean getAccountNonLocked() {
		return this.accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isAccountNonLocked() {
		return this.getAccountNonLocked();
	}

	@JsonIgnore
	public Boolean getCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return this.getCredentialsNonExpired();
	}

	@JsonIgnore
	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return this.getEnabled();
	}
}
