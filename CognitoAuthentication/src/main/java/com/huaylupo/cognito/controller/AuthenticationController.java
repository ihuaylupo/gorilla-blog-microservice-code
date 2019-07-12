/**
 * 
 */
package com.huaylupo.cognito.controller;


import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huaylupo.cognito.exception.CognitoException;
import com.huaylupo.cognito.model.AuthenticationRequest;
import com.huaylupo.cognito.model.AuthenticationResponse;
import com.huaylupo.cognito.model.PasswordRequest;
import com.huaylupo.cognito.model.PasswordResponse;
import com.huaylupo.cognito.model.UserRequest;
import com.huaylupo.cognito.model.UserResponse;
import com.huaylupo.cognito.model.UserSignUpRequest;
import com.huaylupo.cognito.model.UserSignUpResponse;
import com.huaylupo.cognito.security.model.SpringSecurityUser;
import com.huaylupo.cognito.service.CognitoAuthenticationService;

/**
 * SpringBoot Authentication Controller
 * @author ihuaylupo
 * @version 1.0
 * @since Jun 25, 2018
 */

@RestController
@RequestMapping("auth")
public class AuthenticationController {


	@Autowired(required = false)
	private CognitoAuthenticationService authService;



	/**
	 * Spring Controller that has the logic to authenticate.
	 *@param authenticationRequest
	 *@return ResponseEntity<?>
	 *@throws IOException
	 *@throws JOSEException
	 *@user ihuaylupo
	 *@since 2018-06-28 
	 */
	@CrossOrigin
	@RequestMapping(method = POST)
	public ResponseEntity<AuthenticationResponse> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest)throws CognitoException{

	
		AuthenticationResponse authenticationResponse = null;
		UserResponse userResponse = null;
		

		SpringSecurityUser userAuthenticated = authService.authenticate(authenticationRequest);
		if (null != userAuthenticated) {
			// use the credentials
			// and authenticate against the third-party system
			
			userResponse = authService.getUserInfo(userAuthenticated.getAccessToken());
			authenticationResponse = new AuthenticationResponse();
			authenticationResponse.setAccessToken(userAuthenticated.getIdToken());
			authenticationResponse.setExpiresIn(userAuthenticated.getExpiresIn().toString());
			authenticationResponse.setSessionToken(userAuthenticated.getAccessToken());
			authenticationResponse.setUserData(userResponse);
		} 

		// Return the token
		return ResponseEntity.ok(authenticationResponse);

	}



	/**
	 * SignUpRequest - Method that signs up a user into Amazon Cognito
	 *@param authenticationRequest
	 *@return ResponseEntity<User>
	 * @user ihuaylupo
	 * @since 2018-07-03 
	 */
	@RequestMapping(value="/SignUp",method=RequestMethod.POST)
	public ResponseEntity<UserSignUpResponse> signUpRequest(@RequestBody UserSignUpRequest signUpRequest)throws CognitoException{
		UserSignUpResponse user = null;

		//Calls the service that Signs up an specific User
		user = authService.signUp(signUpRequest);
		authService.signUpConfirmation(signUpRequest);
		return ResponseEntity.ok(user);

	}

	/**
	 * SignUpRequest - Method that signs up a user into Amazon Cognito
	 *@param authenticationRequest
	 *@return ResponseEntity<User>
	 * @user ihuaylupo
	 * @since 2018-07-03 
	 */
	@RequestMapping(value="/ResetPassword",method=RequestMethod.POST)
	public ResponseEntity<PasswordResponse> resetPassword(@RequestBody PasswordRequest resetPasswordRequest)throws CognitoException{

		//Calls the service that Signs up an specific User
		PasswordResponse response = authService.resetPassword(resetPasswordRequest);

		return ResponseEntity.ok(response);

	}

	
	/**
	 * SignUpRequest - Method that signs up a user into Amazon Cognito
	 *@param authenticationRequest
	 *@return ResponseEntity<User>
	 * @throws CognitoException 
	 * @user ihuaylupo
	 * @since 2018-07-03 
	 */
	@RequestMapping(value="/ConfirmResetPassword",method=RequestMethod.POST)
	public ResponseEntity<PasswordResponse> confirmResetPassword(@RequestBody PasswordRequest resetPasswordRequest) throws CognitoException{

		//Calls the service that Signs up an specific User
		PasswordResponse response =  authService.confirmResetPassword(resetPasswordRequest);

		return ResponseEntity.ok(response);

	}

	/**
	 * SignUpRequest - Method that signs up a user into Amazon Cognito
	 *@param authenticationRequest
	 *@return ResponseEntity<User>
	 * @user ihuaylupo
	 * @since 2018-07-03 
	 */
	@RequestMapping(value="/SignOut",method=RequestMethod.POST)
	public ResponseEntity<AuthenticationResponse> signOut(@RequestBody AuthenticationRequest authenticationRequest)throws CognitoException{
		AuthenticationResponse response = new AuthenticationResponse();
		//Calls the service that Signs up an specific User

		String message =  authService.signOut(authenticationRequest.getAccessToken(),authenticationRequest.getUsername());

		response.setMessage(message);
		response.setUsername(authenticationRequest.getUsername());

		return ResponseEntity.ok(response);

	}




	@DeleteMapping
	public ResponseEntity<AuthenticationResponse> deleteUser(@RequestBody UserRequest userRequest)throws CognitoException{
		AuthenticationResponse response = new AuthenticationResponse();

		authService.deleteUser(userRequest.getUsername());

		response.setMessage("User Deleted");
		response.setUsername(userRequest.getUsername());
		
		return ResponseEntity.ok(response);
	}


	

}
