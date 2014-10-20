/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.message.BasicNameValuePair;

/**
 * Various text utilities
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class TextUtils {

    /**
     * Join a set of query params with a given delimiter, optionally URL-encoded
     *
     * @param delimiter Which delimiter (character(s)) to use for separating tokens
     * @param tokens Tokens to separate
     * @return String, with the delimiter between each token
     */
    public static String join(CharSequence delimiter, Iterable<BasicNameValuePair> tokens, boolean urlEncode) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (BasicNameValuePair queryParam : tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            
            sb.append(queryParam.getName() + "=" + (urlEncode ? TextUtils.urlEncode(queryParam.getValue()) : queryParam.getValue()));
        }

        return sb.toString();
    }
    
    /**
     * Join a set of tokens with a given delimiter
     *
     * @param delimiter Which delimiter (character(s)) to use for separating tokens
     * @param tokens Tokens to separate
     * @return String, with the delimiter between each token
     */
    public static String join(CharSequence delimiter, Iterable<String> tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (String value : tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            
            sb.append(value);
        }

        return sb.toString();
    }

    /**
     * Join a set of tokens with a given delimiter
     *
     * @param delimiter Which delimiter (character(s)) to use for separating tokens
     * @param tokens Tokens to separate
     * @return String, with the delimiter between each token
     */
    public static String join(CharSequence delimiter, Object[] tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object value : tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(value);
        }

        return sb.toString();
    }

    /**
     * URL-encode the given input string
     *
     * @param value Input value
     * @return URL-encoded string
     */
    public static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8")
                      .replaceAll("\\+", "%20")
                      .replaceAll("\\%21", "!")
                      .replaceAll("\\%27", "'")
                      .replaceAll("\\%28", "(")
                      .replaceAll("\\%29", ")")
                      .replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
            // This really should not happen, but if it does, use the raw value
            return value;
        }
    }

    /**
     * Normalize color definition
     *
     * @param color Input color definition
     * @return Normalized color definition
     */
    public static String normalizeColor(String color) {
        return color.replace("#", "").toLowerCase();
    }

}
