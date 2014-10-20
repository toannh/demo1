/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient.Url;

import java.net.URI;

/**
 * URL interface
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public interface Url {

    /**
     * Returns the URL with query parameters added
     *
     * @return Full URL with query parameters, as a String
     */
    public String getUrl();

    /**
     * Get the complete URL as an URL-encoded string
     *
     * @return URL-encoded string
     */
    public String getUrlEncoded();

    /**
     * Resets the URL - removes all query parameters
     *
     * @return URL without any query parameters
     */
    public Url reset();

    /**
     * Adds a query parameter to the URL
     *
     * @param key Name of the parameter. For instance "page" or "t[]"
     * @param value Value of the parameter. For instance "10" or "border:width=50,height=50"
     * @return URL with the query added
     */
    public Url addQueryParam(String key, String value);

    /**
     * Returns the URL with query parameters added
     *
     * @return Full URL with query parameters, as a String
     */
    public String toString();

    /**
     * Returns the URL in URI format
     *
     * @return Full URL with query parameters, as a URI
     */
    public URI toUri();

}