/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient.Http;

import java.util.HashMap;

import org.apache.http.Header;

/**
 * Client response interface 
 * 
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public interface Response {

	/**
	 * Internal error codes sent from the Imbo server
	 */
	public static final int ERR_UNSPECIFIED = 0;

    // Authentication errors
    public static final int AUTH_UNKNOWN_PUBLIC_KEY    = 100;
    public static final int AUTH_MISSING_PARAM         = 101;
    public static final int AUTH_INVALID_TIMESTAMP     = 102;
    public static final int AUTH_SIGNATURE_MISMATCH    = 103;

    // Image resource errors
    public static final int IMAGE_ALREADY_EXISTS       = 200;
    public static final int IMAGE_NO_IMAGE_ATTACHED    = 201;
    public static final int IMAGE_HASH_MISMATCH        = 202;
    public static final int IMAGE_UNSUPPORTED_MIMETYPE = 203;
    public static final int IMAGE_BROKEN_IMAGE         = 204;
    
    /**
     * Get the headers for the HTTP response
     * 
     * @return Headers in key => value hash map
     */
    public HashMap<String, String> getHeaders();
    
    /**
     * Set the headers for this HTTP response
     * 
     * @param headers Array of headers
     * @return This response instance
     */
    public Response setHeaders(Header[] headers);
    
    /**
     * Get the response body
     * 
     * @return Response body
     */
    public String getBody();
    
    /**
     * Set the body contents
     * 
     * @param body The body of the request, as a string
     * @return This response instance
     */
    public Response setBody(String body);
    
    /**
     * Get the raw response body
     * 
     * @return Raw response body
     */
    public byte[] getRawBody();
    
    /**
     * Set the body contents in a raw, byte-array format
     * 
     * @param body The body of the request, as a byte array
     * @return This response instance
     */
    public Response setRawBody(byte[] body);
    
    /**
     * Get the status code for this request
     * 
     * @return The status code for this request
     */
    public int getStatusCode();
    
    /**
     * Set the status code
     * 
     * @param code The HTTP status code to set
     * @return This response instance
     */
    public Response setStatusCode(int code);
    
    /**
     * Get the optional Imbo error code from the body
     * 
     * @return The internal error code from Imbo
     */
    public int getImboErrorCode();
    
    /**
     * Get the optional Imbo error message from the body
     * 
     * @return The internal error message from Imbo
     */
    public String getImboErrorDescription();
    
    /**
     * Whether or not the response is a success (in the 2xx range)
     * 
     * @return True if the request was a success, false otherwise
     */
    public boolean isSuccess();
    
    /**
     * Whether or not the response is an error (> 4xx range)
     * 
     * @return True if the request resulted in an error, false otherwise
     */
    public boolean isError();
    
    /**
     * Returns the image identifier associated with the response
     * 
     * If the response does not contain any image identifier (for instance if the
     * request made was against the metadata resource) NULL will be returned.
     * 
     * @return 
     */
    public String getImageIdentifier();
    
    /**
     * Get the content type of this response
     * 
     * @return Content-Type header value
     */
    public String getContentType();
    
    /**
     * Set the content type of this response
     * 
     * @param contentType Content-Type header value
     * @return This response instance
     */
    public Response setContentType(String contentType);
    
    /**
     * Get the content length of this response
     * 
     * @return Content-Length header value
     */
    public long getContentLength();
    
    /**
     * Set the content length of this response
     * 
     * @param contentType Content-Length header value
     * @return This response instance
     */
    public Response setContentLength(long contentLength);
	
}