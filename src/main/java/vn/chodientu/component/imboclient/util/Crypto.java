/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient.util;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Cryptography tools
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class Crypto {

    /**
     * Hash a string of data using a given key with the HMAC-SHA256 algorithm
     *
     * @param data Input data
     * @param key Key to use for hashing
     * @return Hashed output
     */
    public static String hashHmacSha256(String data, String key) {
        Charset charset = Charset.forName("UTF-8");
        String algoName = "HmacSHA256";
        Mac algorithm = null;
        try {
            algorithm = Mac.getInstance(algoName);
        } catch (NoSuchAlgorithmException e) {
            // This should hopefully never happen
            return "hmac-sha-256-algorithm-not-found";
        }

        byte[] byteKey = charset.encode(key).array();
        SecretKeySpec secretKey = new javax.crypto.spec.SecretKeySpec(byteKey, algoName);
        try {
            algorithm.init(secretKey);
        } catch (InvalidKeyException e) {
            // .. And this shouldn't really ever happen, either
            return "invalid-key-for-access-token-generation";
        }

        final byte[] macData = algorithm.doFinal(data.getBytes());

        String result = "";
        for (final byte element : macData) {
           result += Integer.toString((element & 0xff) + 0x100, 16).substring(1);
        }

        return result;
    }

}
