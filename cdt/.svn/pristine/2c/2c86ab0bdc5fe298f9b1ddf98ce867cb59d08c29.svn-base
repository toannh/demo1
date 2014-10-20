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

/**
 * Metadata URL
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class MetadataUrl extends ImboUrl implements Url {

    /**
     * Image identifier for the image
     */
    private String imageIdentifier;

    /**
     * Class constructor
     *
     * {@inheritDoc}
     * @param imageIdentifier The image identifier to use in the URL
     */
    public MetadataUrl(String baseUrl, String publicKey, String privateKey, String imageIdentifier) {
        super(baseUrl, publicKey, privateKey);

        this.imageIdentifier = imageIdentifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getResourceUrl() {
        String[] parts = {
            baseUrl,
            "users",
            publicKey,
            "images",
            imageIdentifier,
            "meta.json"
        };
        return TextUtils.join("/", parts);
    }

}