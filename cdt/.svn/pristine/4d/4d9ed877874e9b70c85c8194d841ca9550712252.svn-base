/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient.Url;

/**
 * User URL
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class UserUrl extends ImboUrl implements Url {

    /**
     * {@inheritDoc}
     */
    public UserUrl(String baseUrl, String publicKey, String privateKey) {
        super(baseUrl, publicKey, privateKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getResourceUrl() {
        return baseUrl + "/users/" + publicKey + ".json";
    }

}
