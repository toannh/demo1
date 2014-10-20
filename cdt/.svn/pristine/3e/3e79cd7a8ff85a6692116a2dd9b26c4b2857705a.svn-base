/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient.Http;

import vn.chodientu.component.imboclient.Url.Url;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import org.apache.http.Header;

/**
 * HTTP Client interface
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public interface HttpClient {

	/**
	 * HTTP methods
	 */
	public static final String GET = "GET";
	public static final String PUT = "PUT";
	public static final String HEAD = "HEAD";
	public static final String POST = "POST";
	public static final String DELETE = "DELETE";

	/**
     * Perform a POST-request against the given URL
     *
     * @param url URL to perform request against
     * @param data Post-data to send
     * @return HTTP response
     * @throws IOException
     */
	public ImboResponse post(Url url, String data) throws IOException;
	
	/**
     * Perform a POST-request against the given URL
     *
     * @param url URL to perform request against
     * @param data Post-data to send
     * @param headers Headers to send along with the request
     * @return HTTP response
     * @throws IOException
     */
	public ImboResponse post(Url url, String data, List<Header> headers) throws IOException;
	
	/**
     * Perform a POST-request against the given URL
     *
     * @param url URL to perform request against
     * @param data Post-data to send
     * @return HTTP response
     * @throws IOException
     */
    public Response post(URI url, String data) throws IOException;
	
    /**
     * Perform a POST-request against the given URL
     *
     * @param url URL to perform request against
     * @param data Post-data to send
     * @param headers Headers to send along with the request
     * @return HTTP response
     * @throws IOException
     */
    public Response post(URI url, String data, List<Header> headers) throws IOException;
    
    /**
     * Perform a POST-request against the given URL
     *
     * @param url URL to perform request against
     * @param input Input stream to use for reading POST-data from
     * @return HTTP response
     * @throws IOException
     */
    public Response post(URI url, InputStream input) throws IOException;
    
    /**
     * Perform a POST-request against the given URL
     *
     * @param url URL to perform request against
     * @param input Input stream to use for reading POST-data from
     * @param headers Heades to send along with the request
     * @return HTTP response
     * @throws IOException
     */
    public ImboResponse post(URI url, InputStream input, List<Header> headers) throws IOException;
    
    /**
     * Perform a POST-request against the given URL
     *
     * @param url URL to perform request against
     * @param file File to send along with request
     * @return HTTP response
     * @throws IOException
     */
    public Response post(URI url, File file) throws IOException;
    
    /**
     * Perform a POST-request against the given URL
     *
     * @param url URL to perform request against
     * @param file File to send along with request
     * @param headers Headers to send along with the request
     * @return HTTP response
     * @throws IOException
     */
    public Response post(URI url, File file, List<Header> headers) throws IOException;

    /**
     * Perform a GET-request against the given URL
     *
     * @param url URL to perform request against
     * @return HTTP response
     * @throws IOException
     */
    public Response get(URI url) throws IOException;
    
    /**
     * Perform a GET-request against the given URL
     *
     * @param url URL to perform request against
     * @return HTTP response
     * @throws IOException
     */
    public Response get(Url url) throws IOException;

    /**
     * Perform a HEAD-request against the given URL
     *
     * @param url URL to perform request against
     * @return HTTP response
     * @throws IOException
     */
    public Response head(URI url) throws IOException;
    
    /**
     * Perform a HEAD-request against the given URL
     *
     * @param url URL to perform request against
     * @return HTTP response
     * @throws IOException
     */
    public Response head(Url url) throws IOException;

    /**
     * Perform a DELETE-request against the given URL
     *
     * @param url URL to perform request against
     * @return HTTP response
     * @throws IOException
     */
    public Response delete(URI url) throws IOException;
    
    /**
     * Perform a DELETE-request against the given URL
     *
     * @param url URL to perform request against
     * @return HTTP response
     * @throws IOException
     */
    public ImboResponse delete(Url url) throws IOException;

    /**
     * Perform a PUT-request against the given URL
     *
     * @param url URL to perform request against
     * @param data Raw data to PUT, as String
     * @param headers Headers to send along with the request
     * @return HTTP response
     * @throws IOException
     */
    public Response put(URI url, String data, List<Header> headers) throws IOException;

    /**
     * Perform a PUT-request against the given URL
     *
     * @param url URL to perform request against
     * @param input Input stream to use for reading PUT-data from
     * @return HTTP response
     * @throws IOException
     */
    public Response put(URI url, InputStream input) throws IOException;
    
    /**
     * Perform a PUT-request against the given URL
     *
     * @param url URL to perform request against
     * @param input Input stream to use for reading PUT-data from
     * @param headers Heades to send along with the request
     * @return HTTP response
     * @throws IOException
     */
    public ImboResponse put(URI url, InputStream input, List<Header> headers) throws IOException;

    /**
     * Perform a PUT-request against the given URL
     *
     * @param url URL to perform request against
     * @param file File to send along with request
     * @return HTTP response
     * @throws IOException
     */
    public Response put(URI url, File file) throws IOException;
    
    /**
     * Perform a PUT-request against the given URL
     *
     * @param url URL to perform request against
     * @param file File to send along with request
     * @param headers Headers to send along with the request
     * @return HTTP response
     * @throws IOException
     */
    public Response put(URI url, File file, List<Header> headers) throws IOException;
    
    /**
     * Perform a PUT-request against the given URL
     *
     * @param url URL to perform request against
     * @param data Raw data to PUT, as String
     * @param headers Headers to send along with the request
     * @return HTTP response
     * @throws IOException
     */
    public Response put(Url url, String data, List<Header> headers) throws IOException;

    /**
     * Perform a PUT-request against the given URL
     *
     * @param url URL to perform request against
     * @param input Input stream to use for reading PUT-data from
     * @return HTTP response
     * @throws IOException
     */
    public Response put(Url url, InputStream input) throws IOException;
    
    /**
     * Perform a PUT-request against the given URL
     *
     * @param url URL to perform request against
     * @param input Input stream to use for reading PUT-data from
     * @param headers Headers to send along with the request
     * @return HTTP response
     * @throws IOException
     */
    public ImboResponse put(Url url, InputStream input, List<Header> headers) throws IOException;

    /**
     * Perform a PUT-request against the given URL
     *
     * @param url URL to perform request against
     * @param file File to send along with request
     * @return HTTP response
     * @throws IOException
     */
    public Response put(Url url, File file) throws IOException;
    
    /**
     * Perform a PUT-request against the given URL
     *
     * @param url URL to perform request against
     * @param file File to send along with request
     * @param headers Headers to send along with the request
     * @return HTTP response
     * @throws IOException
     */
    public ImboResponse put(Url url, File file, List<Header> headers) throws IOException;

    /**
     * Set request headers to send along with the request
     *
     * @param headers Headers to send
     * @return This client instance
     */
    public HttpClient setRequestHeaders(List<Header> headers);
    
    /**
     * Get request headers to send along with the request
     *
     * @return List of headers
     */
    public List<Header> getRequestHeaders();

}
