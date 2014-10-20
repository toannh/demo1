/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient.Url;

import vn.chodientu.component.imboclient.util.Crypto;

/**
 * Access token implementation
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class AccessToken {

	/**
     * Generate an access token for a given URL using a key
     * 
     * @param url The URL to generate the token for
     * @param key The key to use when generating the token
     * @return Returns an access token for a URL. Given the same URL and key combo this method returns the same token every time.
     */
    public String generateToken(String url, String key) {
        return Crypto.hashHmacSha256(url, key);
    }

}