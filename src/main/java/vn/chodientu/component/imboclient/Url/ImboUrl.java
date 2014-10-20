/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient.Url;

import vn.chodientu.component.imboclient.util.TextUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

/**
 * Abstract Imbo URL for other implementations to extend
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public abstract class ImboUrl implements Url {

    /**
     * Base URL
     */
    protected String baseUrl;

    /**
     * Public key
     */
    protected String publicKey;

    /**
     * Private key
     */
    private String privateKey;

    /**
     * Access token generator
     */
    private AccessToken accessToken;

    /**
     * Query parameters for the URL
     */
    private ArrayList<BasicNameValuePair> queryParams = new ArrayList<BasicNameValuePair>();

    /**
     * Class constructor
     *
     * @param baseUrl The base URL to use
     * @param publicKey The public key to use
     * @param privateKey The private key to use
     */
    public ImboUrl(String baseUrl, String publicKey, String privateKey) {
        this.baseUrl = baseUrl;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * Returns the URL with query parameters added
     *
     * @return Full URL with query parameters, as a String
     */
    public String getUrl() {
        String url = getResourceUrl();
        String queryString = getRawQueryString();

        if (!queryString.isEmpty()) {
            url += "?" + queryString;
        }

        if (publicKey == null || privateKey == null) {
            return url;
        }
        
        String token = getAccessToken().generateToken(url, privateKey);
        String encodedQueryString = getQueryString();
        
        String fullUrl = getResourceUrl();
        if (!encodedQueryString.isEmpty()) {
            fullUrl += "?" + encodedQueryString;
        }
        
        fullUrl += (queryParams.isEmpty() ? "?" : "&") + "accessToken=" + token;
        
        return fullUrl;
    }

    /**
     * Get the complete URL as an URL-encoded string
     *
     * @return URL-encoded string
     */
    public String getUrlEncoded() {
        String url = getUrl();
        url = url.replace("&", "&amp;");
        url = url.replace("[]", "%5B%5D");
        return url;
    }

    /**
     * Returns the URL with query parameters added
     *
     * @return Full URL with query parameters, as a String
     */
    public String toString() {
        return getUrl();
    }

    /**
     * Returns the URL in URI format
     *
     * @return Full URL with query parameters, as a URI
     */
    public URI toUri() {
        try {
            return new URI(getUrl());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        	return null;
        }
    }

    /**
     * Adds a query parameter to the URL
     *
     * @param key Name of the parameter. For instance "page" or "t[]"
     * @param value Value of the parameter. For instance "10" or "border:width=50,height=50"
     * @return URL with the query added
     */
    public Url addQueryParam(String key, String value) {
        queryParams.add(new BasicNameValuePair(key, value));

        return this;
    }

    /**
     * Resets the URL - removes all query parameters
     *
     * @return URL without any query parameters
     */
    public Url reset() {
        queryParams.clear();
        return this;
    }

    /**
     * Get an instance of the access token
     *
     * If no instance have been provided prior to calling this method, this method must
     * instantiate the AccessToken class and return that instance.
     *
     * @return AccessToken
     */
    public AccessToken getAccessToken() {
        if (accessToken == null) {
            accessToken = new AccessToken();
        }

        return accessToken;
    }

    /**
     * Set an instance of the access token
     *
     * @return AccessToken $accessToken An instance of the access token
     * @return UrlInterface
     */
    public Url setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    /**
     * Retrieves the query string for this URL
     *
     * @param urlEncode Whether to URL-encode the values or not
     * @return Query string, as a String
     */
    private String getQueryString(boolean urlEncode) {
        if (queryParams.isEmpty()) {
            return "";
        }

        return TextUtils.join("&", queryParams, urlEncode);
    }
    
    /**
     * Retrieves the query string for this URL
     *
     * @param urlEncode Whether to URL-encode the values or not
     * @return Query string, as a String
     */
    private String getQueryString() {
        return getQueryString(true);
    }
    
    /**
     * Retrieves the query string for this URL
     *
     * @param urlEncode Whether to URL-encode the values or not
     * @return Query string, as a String
     */
    private String getRawQueryString() {
        return getQueryString(false);
    }

    /**
     * Get the raw URL (with no access token appended)
     *
     * @return The raw URL as a String
     */
    abstract protected String getResourceUrl();
}