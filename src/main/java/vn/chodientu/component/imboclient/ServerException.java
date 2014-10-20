/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient;

import vn.chodientu.component.imboclient.Http.Response;

import java.io.IOException;

/**
 * Server exception
 * 
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class ServerException extends IOException {
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 3243355260045940012L;
	
	/**
	 * Holds the response instance
	 */
	private Response response;
	
	/**
	 * Holds the error code for this exception
	 */
	private int errorCode = 0;
	
	/**
	 * Exception constructor
	 * 
	 * @param errorMessage
	 */
	public ServerException(String errorMessage, int errorCode) {
		super(errorMessage);
		
		this.errorCode = errorCode;
	}

	/**
	 * Set the response instance
	 * 
	 * @param response Response object containing info about the server response
	 * @return This exception instance
	 */
	public ServerException setResponse(Response response) {
		this.response = response;
		
		return this;
	}
	
	/**
	 * Get the response instance
	 * 
	 * @return ResponseInterface
	 */
	public Response getResponse() {
		return this.response;
	}
	
	/**
	 * Get the error code for this exception
	 * 
	 * @return Error code
	 */
	public int getErrorCode() {
		return this.errorCode;
		
	}
	
}