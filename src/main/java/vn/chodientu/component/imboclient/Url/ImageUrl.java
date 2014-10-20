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

import java.util.ArrayList;

/**
 * Image URL
 * 
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class ImageUrl extends ImboUrl implements Url {

    /**
     * Image identifier for this image
     */
    private String imageIdentifier;
    
    /**
     * Class constructor
     * 
     * @param baseUrl The base URL to use
     * @param publicKey The public key to use
     * @param privateKey The private key to use
     * @param imageIdentifier The image identifier to use in the URL
     */
    public ImageUrl(String baseUrl, String publicKey, String privateKey, String imageIdentifier) {
        super(baseUrl, publicKey, privateKey);
        
        this.imageIdentifier = imageIdentifier;
    }

    /**
     * Append a border transformation query parameter to the URL
     * 
     * @param color Color of the border, in hex format
     * @param width Width of the left and right sides of the border
     * @param height Height of the left and right sides of the border
     * @return ImageUrl
     */
    public ImageUrl border(String color, int width, int height) {
        if (color == null) {
            color = "000000";
        }
        
        addQueryParam("t[]", ( 
                "border:color=" +
                TextUtils.normalizeColor(color) +
                ",width=" + width +
                ",height=" + height
            )
        );
        
        return this;
    }
    
    /**
     * Append a border transformation query parameter to the URL.
     * Border will be 1px in width and height. 
     * 
     * @param color Color of the border, in hex format. Defaults to '000000' (black)
     * @return ImageUrl
     */
    public ImageUrl border(String color) {
        return border(color, 1, 1);
    }

    /**
     * Append a compress transformation query parameter to the URL
     *
     * @param level A value between 0 and 100 where 100 is the highest compression ratio
     * @return ImageUrl
     */
    public ImageUrl compress(int level) {
        addQueryParam("t[]", "compress:level=" + level);
        
        return this;
    }
    
    /**
     * Append a compress transformation query parameter to the URL with a compression level of 75
     * 
     * @return ImageUrl
     */
    public ImageUrl compress() {
        return compress(75);
    }

    /**
     * Change the URL to trigger the convert transformation
     *
     * @param type The type to convert to
     * @return ImageUrl
     */
    public ImageUrl convert(String type) {
        imageIdentifier = imageIdentifier.substring(0, 32) + "." + type;
        
        return this;
    }

    /**
     * Convenience method to trigger GIF conversion
     *
     * @return ImageUrl
     */
    public ImageUrl gif() {
        return convert("gif");
    }

    /**
     * Convenience method to trigger JPG conversion
     *
     * @return ImageUrl
     */
    public ImageUrl jpg() {
        return convert("jpg");
    }

    /**
     * Convenience method to trigger PNG conversion
     *
     * @return ImageUrl
     */
    public ImageUrl png() {
        return convert("png");
    }

    /**
     * Append a crop transformation query parameter to the URL
     *
     * @param x X coordinate of the top left corner of the crop
     * @param y Y coordinate of the top left corner of the crop
     * @param width Width of the crop
     * @param height Height of the crop
     * @return ImageUrl
     */
    public ImageUrl crop(int x, int y, int width, int height) {
        addQueryParam("t[]", ( 
                "crop:" +
                "x=" + x +
                ",y=" + y + 
                ",width=" + width +
                ",height=" + height
            )
        );
        
        return this;
    }

    /**
     * Append a flipHorizontally transformation query parameter to the URL
     *
     * @return ImageUrl
     */
    public ImageUrl flipHorizontally() {
        addQueryParam("t[]", "flipHorizontally");
        
        return this;
    }

    /**
     * Append a flipVertically transformation query parameter to the URL
     *
     * @return ImageUrl
     */
    public ImageUrl flipVertically() {
        addQueryParam("t[]", "flipVertically");
        
        return this;
    }

    /**
     * Append a resize transformation query parameter to the URL
     *
     * @param width Width of the resized image
     * @param height Height of the resized image
     * @return ImageUrl
     */
    public ImageUrl resize(int width, int height) {
        ArrayList<String> params = new ArrayList<String>();
        
        if (width > 0) {
            params.add("width=" + width);
        }
        
        if (height > 0) {
            params.add("height=" + height);
        }
        
        addQueryParam("t[]", "resize:" + TextUtils.join(",", params));
        
        return this;
    }

    /**
     * Append a maxSize transformation query parameter to the URL
     *
     * @param maxWidth Max width of the resized image
     * @param maxHeight Max height of the resized image
     * @return ImageUrl
     */
    public ImageUrl maxSize(int maxWidth, int maxHeight) {
        ArrayList<String> params = new ArrayList<String>();
        
        if (maxWidth > 0) {
            params.add("width=" + maxWidth);
        }
        
        if (maxHeight > 0) {
            params.add("height=" + maxHeight);
        }
        
        addQueryParam("t[]", "maxSize:" + TextUtils.join(",", params));
        
        return this;
    }

    /**
     * Append a rotate transformation query parameter to the URL
     *
     * @param angle The angle to rotate
     * @param bg Background color of the rotated image
     * @return ImageUrl
     */
    public ImageUrl rotate(double angle, String bg) {
        // Don't put decimals into the URL if we have no fractions
        String ang = Double.toString(angle);
        if ((angle - (int) angle) == 0) {
            ang = Integer.toString((int) angle);
        }
        
        addQueryParam("t[]",
            "rotate:angle=" + ang +
            ",bg=" + TextUtils.normalizeColor(bg)
        );
        
        return this;
    }
    
    /**
     * Append a rotate transformation query parameter to the URL
     *
     * @param angle The angle to rotate
     * @return ImageUrl
     */
    public ImageUrl rotate(double angle) {
        return rotate(angle, "000000");
    }

    /**
     * Append a thumbnail transformation query parameter to the URL
     *
     * @param width Width of the thumbnail
     * @param height Height of the thumbnail
     * @param fit Fit type. 'outbound' or 'inset'
     * @return ImageUrl
     */
    public ImageUrl thumbnail(int width, int height, String fit) {
        if (width == 0) {
            width = 50;
        }
        
        if (height == 0) {
            width = 50;
        }
        
        if (fit == null) {
            fit = "outbound";
        }
        
        addQueryParam("t[]", "thumbnail:width=" + width + ",height=" + height + ",fit=" + fit);
        
        return this;
    }
    
    /**
     * Append a thumbnail transformation query parameter to the URL
     *
     * @return ImageUrl
     */
    public ImageUrl thumbnail() {
        return thumbnail(50, 50, null);
    }

    /**
     * Append a canvas transformation query parameter to the URL
     *
     * @param width Width of the new canvas
     * @param height Height of the new canvas
     * @param mode The placement mode
     * @param x X coordinate of the placement of the upper left corner of the existing image
     * @param y Y coordinate of the placement of the upper left corner of the existing image
     * @param bg Background color of the canvas, in hex-format
     * @return ImageUrl
     */
    public ImageUrl canvas(int width, int height, String mode, int x, int y, String bg) {
        ArrayList<String> params = new ArrayList<String>();
        params.add("width="  + width);
        params.add("height=" + height);
        
        if (mode != null) {
            params.add("mode=" + mode);
        }
        
        params.add("x=" + x);
        params.add("y=" + y);
        
        if (bg != null) {
            params.add("bg=" + TextUtils.normalizeColor(bg));
        }
        
        addQueryParam("t[]", "canvas:" + TextUtils.join(",", params));
        
        return this;
    }
    
    /**
     * Append a canvas transformation query parameter to the URL
     *
     * @param width Width of the new canvas
     * @param height Height of the new canvas
     * @return ImageUrl
     */
    public ImageUrl canvas(int width, int height) {
        addQueryParam("t[]", "canvas:width=" + width + ",height=" + height);
        
        return this;
    }

    /**
     * Append a transpose transformation query parameter to the URL
     *
     * @return ImageUrl
     */
    public ImageUrl transpose() {
        addQueryParam("t[]", "transpose");
        
        return this;
    }

    /**
     * Append a transverse transformation query parameter to the URL
     *
     * @return ImageUrl
     */
    public ImageUrl transverse() {
        addQueryParam("t[]", "transverse");
        
        return this;
    }

    /**
     * Append a desaturate transformation query parameter to the URL
     *
     * @return ImageUrl
     */
    public ImageUrl desaturate() {
        addQueryParam("t[]", "desaturate");
        
        return this;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Url reset() {
        super.reset();
        
        imageIdentifier = imageIdentifier.substring(0, 32);
        
        return this;
    }
    
    /**
     * {@inheritDoc}
     */
    protected String getResourceUrl() {
        String[] parts = {
            baseUrl,
            "users",
            publicKey,
            "images",
            imageIdentifier
        };
        return TextUtils.join("/", parts);
    }

}