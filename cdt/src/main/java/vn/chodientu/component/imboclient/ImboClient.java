/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file
 * that was distributed with this source code.
 */
package vn.chodientu.component.imboclient;

import vn.chodientu.component.imboclient.Http.HttpClient;
import vn.chodientu.component.imboclient.Http.ImboHttpClient;
import vn.chodientu.component.imboclient.Http.Response;
import vn.chodientu.component.imboclient.Images.Image;
import vn.chodientu.component.imboclient.Images.ImagesResponse;
import vn.chodientu.component.imboclient.Images.Query;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.component.imboclient.Url.ImagesUrl;
import vn.chodientu.component.imboclient.Url.MetadataUrl;
import vn.chodientu.component.imboclient.Url.StatusUrl;
import vn.chodientu.component.imboclient.Url.Url;
import vn.chodientu.component.imboclient.Url.UserUrl;
import vn.chodientu.component.imboclient.util.Crypto;
import vn.chodientu.component.imboclient.util.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TimeZone;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Imbo client
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class ImboClient {

    /**
     * URLs for the server hosts
     */
    private String[] serverUrls;

    /**
     * Public key used for signed requests
     */
    private String publicKey;

    /**
     * Private key used for signed requests
     */
    private String privateKey;

    /**
     * Holds a HTTP client instance
     */
    private HttpClient httpClient;

    /**
     * Constructs the Imbo client
     *
     * @param serverUrl URL to the server
     * @param publicKey Public key to use for this instance
     * @param privateKey Private key to use for this instance
     */
    public ImboClient(String serverUrl, String publicKey, String privateKey) {
        this.serverUrls = parseUrls(serverUrl);
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * Constructs the Imbo client
     *
     * @param serverUrls URLs to the server
     * @param publicKey Public key to use for this instance
     * @param privateKey Private key to use for this instance
     */
    public ImboClient(String[] serverUrls, String publicKey, String privateKey) {
        this.serverUrls = parseUrls(serverUrls);
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * Return the current server URL's used by the client
     *
     * @return String[] List of server URLs
     */
    public String[] getServerUrls() {
        return serverUrls;
    }

    /**
     * Get the URL to the status resource
     *
     * @return URL to the status resource
     */
    public StatusUrl getStatusUrl() {
        return new StatusUrl(serverUrls[0]);
    }

    /**
     * Get the URL to the current user
     *
     * @return URL to the current user
     */
    public UserUrl getUserUrl() {
        return new UserUrl(serverUrls[0], publicKey, privateKey);
    }

    /**
     * Get the URL to the images resource of the current user
     *
     * @return URL to the images resource
     */
    public ImagesUrl getImagesUrl() {
        return new ImagesUrl(serverUrls[0], publicKey, privateKey);
    }

    /**
     * Get the URL to a specific image
     *
     * @param imageIdentifier Image identifier for the wanted image
     * @return URL to the image
     */
    public ImageUrl getImageUrl(String imageIdentifier) {
        String hostname = getHostForImageIdentifier(imageIdentifier);

        return new ImageUrl(hostname, publicKey, privateKey, imageIdentifier);
    }

    /**
     * Get the URL to the meta data of a specific image
     *
     * @param imageIdentifier Image identifier for the wanted image
     * @return URL to the meta data resource
     */
    public MetadataUrl getMetadataUrl(String imageIdentifier) {
        String hostname = getHostForImageIdentifier(imageIdentifier);

        return new MetadataUrl(hostname, publicKey, privateKey, imageIdentifier);
    }

    /**
     * Add a new image to the server
     *
     * @param image File instance to add to the server
     * @return Response from the server
     * @throws IOException
     */
    public Response addImage(File image) throws IOException {
        validateLocalFile(image);

        URI signedUrl = getSignedUrl("POST", getImagesUrl());
        return this.getHttpClient().post(signedUrl, image);
    }

    /**
     * Add a new image to the server
     *
     * @param bytes Byte array of data to add to the server
     * @return Response from the server
     * @throws IOException
     */
    public Response addImage(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("Byte array is empty");
        }

        URI signedUrl = getSignedUrl("POST", getImagesUrl());
        ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);

        return this.getHttpClient().post(signedUrl, buffer);
    }

    /**
     * Add a new image to the server from a given URL
     *
     * @param url URL to the image you want to add
     * @return Response from the server
     * @throws IOException
     */
    public Response addImageFromUrl(URI url) throws IOException {
        Response response = this.getHttpClient().get(url);

        return addImage(response.getRawBody());
    }

    /**
     * Add a new image to the server from a given URL
     *
     * @param url URL to the image you want to add
     * @return Response from the server
     * @throws IOException
     */
    public Response addImageFromUrl(Url url) throws IOException {
        return addImageFromUrl(url.toUri());
    }

    /**
     * Checks if a given image exists on the server already by specifying an
     * image identifier
     *
     * @param imageIdentifier Image identifier you want to check if exists
     * @return True if image exists on server, false otherwise
     * @throws IOException
     */
    public boolean imageIdentifierExists(String imageIdentifier) throws IOException {
        try {
            this.headImage(imageIdentifier);
        } catch (ServerException e) {
            if (e.getErrorCode() == 404) {
                return false;
            }

            throw e;
        }

        return true;
    }

    /**
     * Checks if a given image checksum exists on the server already
     *
     * @param imageChecksum Image checksum you want to check if exists
     * @return True if image exists on server, false otherwise
     * @throws IOException
     */
    public boolean imageWithChecksumExists(String imageChecksum) throws IOException {
        Query query = (new Query()).addChecksum(imageChecksum).limit(1);

        try {
            this.getImages(query);
        } catch (ServerException e) {
            if (e.getErrorCode() == 404) {
                return false;
            }

            throw e;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Request an image using HEAD
     *
     * @param imageIdentifier Image identifier to do the request against
     * @return Response from the server
     * @throws IOException
     */
    public Response headImage(String imageIdentifier) throws IOException {
        ImageUrl url = this.getImageUrl(imageIdentifier);

        return this.getHttpClient().head(url);
    }

    /**
     * Delete an image from the server
     *
     * @param imageIdentifier Image identifier of the image to delete
     * @return Response from the server
     * @throws IOException
     */
    public Response deleteImage(String imageIdentifier) throws IOException {
        ImageUrl url = this.getImageUrl(imageIdentifier);
        URI signedUrl = this.getSignedUrl("DELETE", url);

        return this.getHttpClient().delete(signedUrl);
    }

    /**
     * Edit image meta data
     *
     * @param imageIdentifier Image identifier to edit meta data for
     * @param metadata Actual meta data to add
     * @return Response from the server
     * @throws IOException
     */
    public Response editMetadata(String imageIdentifier, JSONObject metadata) throws IOException {
        MetadataUrl url = this.getMetadataUrl(imageIdentifier);
        URI signedUrl = this.getSignedUrl(HttpClient.POST, url);

        String data = metadata.toString();

        ArrayList<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Content-Type", "application/json"));

        return this.getHttpClient().post(signedUrl, data, headers);
    }

    /**
     * Replace all existing meta data
     *
     * @param imageIdentifier Image identifier to replace meta data for
     * @param metadata Actual meta data to add
     * @return Response from the server
     * @throws IOException
     */
    public Response replaceMetadata(String imageIdentifier, JSONObject metadata) throws IOException {
        MetadataUrl url = this.getMetadataUrl(imageIdentifier);
        URI signedUrl = this.getSignedUrl(HttpClient.PUT, url);

        String data = metadata.toString();

        ArrayList<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Content-Type", "application/json"));

        return this.getHttpClient().put(signedUrl, data, headers);
    }

    /**
     * Delete meta data
     *
     * @param imageIdentifier Image identifier to delete meta data for
     * @return Response from the server
     * @throws IOException
     */
    public Response deleteMetadata(String imageIdentifier) throws IOException {
        MetadataUrl url = this.getMetadataUrl(imageIdentifier);
        URI signedUrl = this.getSignedUrl(HttpClient.DELETE, url);

        return this.getHttpClient().delete(signedUrl);
    }

    /**
     * Get image meta data
     *
     * @param imageIdentifier Image identifier to get meta data for
     * @return Meta data as a JSONObject
     * @throws JSONException
     * @throws IOException
     */
    public JSONObject getMetadata(String imageIdentifier) throws JSONException, IOException {
        MetadataUrl url = this.getMetadataUrl(imageIdentifier);

        Response response = this.getHttpClient().get(url);

        JSONObject body = new JSONObject(response.getBody());

        return body;
    }

    /**
     * Get the number of images currently stored on the server for the current
     * user
     *
     * @return Number of images for the current user
     * @throws IOException
     * @throws JSONException
     */
    public int getNumberOfImages() throws IOException, JSONException {
        UserUrl url = this.getUserUrl();
        Response response = this.getHttpClient().get(url);

        JSONObject body = new JSONObject(response.getBody());

        return body.optInt("numImages", 42);
    }

    /**
     * Get an array of images currently stored on the server
     *
     * @return An images response instance
     * @throws IOException
     * @throws JSONException
     */
    public ImagesResponse getImages() throws IOException, JSONException {
        return this.getImages(null);
    }

    /**
     * Get an array of images currently stored on the server
     *
     * @param query Query to send to the server
     * @return An images response instance
     * @throws IOException
     * @throws JSONException
     */
    public ImagesResponse getImages(Query query) throws IOException, JSONException {
        ImagesUrl url = this.getImagesUrl();
        HashMap<String, String> params = null;

        if (query != null) {
            params = query.toHashMap();

            String key;
            Iterator<String> keyIterator = params.keySet().iterator();
            while (keyIterator.hasNext()) {
                key = keyIterator.next();
                url.addQueryParam(key, params.get(key));
            }
        }

        // Fetch the response
        Response httpResponse = this.getHttpClient().get(url.toUri());
        return new ImagesResponse(new JSONObject(httpResponse.getBody()));
    }

    /**
     * Get the binary data of an image stored on the server
     *
     * @param imageIdentifier The image identifier to get data from
     * @return Image data as byte-array
     * @throws IOException
     */
    public byte[] getImageData(String imageIdentifier) throws IOException {
        return this.getImageData(this.getImageUrl(imageIdentifier).toUri());
    }

    /**
     * Get the binary data of a URL
     *
     * @param url URL to fetch binary data from
     * @return Image data as a byte-array
     * @throws IOException
     */
    public byte[] getImageData(URI url) throws IOException {
        Response response = this.getHttpClient().get(url);

        return response.getRawBody();
    }

    /**
     * Get properties of an image
     *
     * @param imageIdentifier Image identifier to get properties for
     * @return Image instance containing the properties
     * @throws IOException
     */
    public Image getImageProperties(String imageIdentifier) throws IOException {
        Response response = this.headImage(imageIdentifier);
        HashMap<String, String> headers = response.getHeaders();

        JSONObject data = new JSONObject();
        try {
            data.put("imageIdentifier", imageIdentifier);
            data.put("extension", headers.get("X-Imbo-OriginalExtension"));
            data.put("mime", headers.get("X-Imbo-OriginalMimeType"));
            data.put("size", Integer.parseInt(headers.get("X-Imbo-OriginalFileSize")));
            data.put("width", Integer.parseInt(headers.get("X-Imbo-OriginalWidth")));
            data.put("height", Integer.parseInt(headers.get("X-Imbo-OriginalHeight")));

        } catch (NumberFormatException e) {

        } catch (JSONException e) {

        }

        return new Image(data);
    }

    /**
     * Get the server status
     *
     * @return Server status as a JSON object
     * @throws JSONException
     * @throws IOException
     */
    public JSONObject getServerStatus() throws JSONException, IOException {

        StatusUrl url = this.getStatusUrl();
        Response response;

        try {
            response = this.getHttpClient().get(url);
        } catch (ServerException e) {
            if (e.getErrorCode() == 500) {
                response = e.getResponse();
            } else {
                // Re-throw same exception
                throw e;
            }
        }

        return new JSONObject(response.getBody());
    }

    /**
     * Get user information
     *
     * @return User information as a JSON object
     * @throws IOException
     * @throws JSONException
     */
    public JSONObject getUserInfo() throws JSONException, IOException {
        UserUrl url = this.getUserUrl();

        Response response = this.getHttpClient().get(url);

        return new JSONObject(response.getBody());
    }

    /**
     * {@inheritDoc}
     */
    public String getImageChecksum(File file) throws IOException {
        validateLocalFile(file);
        InputStream is = new FileInputStream(file);

        return getImageChecksum(is);
    }

    /**
     * {@inheritDoc}
     */
    public String getImageChecksum(InputStream imageStream) throws IOException {
        MessageDigest complete = null;
        try {
            complete = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "md5-algo-not-defined";
        }

        byte[] buffer = new byte[1024];
        int numRead;

        do {
            numRead = imageStream.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        imageStream.close();

        byte[] bytes = complete.digest();
        String result = "";

        for (int i = 0; i < bytes.length; i++) {
            result += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
        }

        return result;
    }

    /**
     * Set the HTTP client to be used for requests
     *
     * @param client HTTP client to be used
     * @return Returns this instance of the Imbo client
     */
    public ImboClient setHttpClient(HttpClient client) {
        this.httpClient = client;

        return this;
    }

    /**
     * Get the HTTP client to be used for requests
     *
     * @return HTTP client
     */
    public HttpClient getHttpClient() {
        if (this.httpClient == null) {
            this.setHttpClient(new ImboHttpClient());
        }

        return this.httpClient;
    }

    /**
     * Generate a signature that can be sent to the server
     *
     * @param method HTTP method (PUT, POST or DELETE)
     * @param url The URL to send a request to
     * @param timestamp UTC time stamp
     * @return string
     */
    private String generateSignature(String method, String url, String timestamp) {
        String data = method + "|" + url + "|" + publicKey + "|" + timestamp;

        return Crypto.hashHmacSha256(data, this.privateKey);
    }

    /**
     * Get a signed URL
     *
     * @param method HTTP method
     * @param url The URL to send a request to
     * @return Returns a URI with the necessary parts for authenticating
     */
    private URI getSignedUrl(String method, Url url) {
        return getSignedUrl(method, url.toString());
    }

    /**
     * Get a signed URL
     *
     * @param method HTTP method
     * @param url The URL to send a request to
     * @return Returns a string with the necessary parts for authenticating
     */
    private URI getSignedUrl(String method, String url) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        String timestamp = df.format(new Date());
        String signature = generateSignature(method, url, timestamp);

        String[] parts = {
            url,
            (url.contains("?") ? "&" : "?"),
            "signature=",
            TextUtils.urlEncode(signature),
            "&timestamp=",
            TextUtils.urlEncode(timestamp)
        };

        URI signed;
        try {
            signed = new URI(TextUtils.join("", parts));
        } catch (URISyntaxException e) {
            return null;
        }

        return signed;
    }

    /**
     * Parse server host URLs and prepare them for usage
     *
     * @param host URL for the Imbo server
     * @return Array of URLs
     */
    private String[] parseUrls(String host) {
        String[] urls = {host};
        return parseUrls(urls);
    }

    /**
     * Parse server host URLs and prepare them for usage
     *
     * @param hosts URLs for the Imbo server
     * @return Array of URLs
     */
    private String[] parseUrls(String[] hosts) {
        LinkedList<String> urls = new LinkedList<String>();

        for (String host : hosts) {
            host = normalizeUrl(host);

            if (host != null && !urls.contains(host)) {
                urls.add(host);
            }
        }

        return urls.toArray(new String[urls.size()]);
    }

    /**
     * Normalize a URL
     *
     * @param url Input URL
     * @return Normalized URL
     */
    private String normalizeUrl(String url) {
        URI parsedUrl;

        if (!url.matches("^https?://.*")) {
            url = "http://" + url;
        }

        try {
            parsedUrl = new URI(url);
        } catch (URISyntaxException e) {
            return null;
        }

        // Remove the port from the server URL if it's equal to 80 when scheme is http, or if
        // it's equal to 443 when the scheme is https
        if (parsedUrl.getPort() != -1 && ((parsedUrl.getScheme().equals("http") && parsedUrl.getPort() == 80)
                || (parsedUrl.getScheme().equals("https") && parsedUrl.getPort() == 443))) {
            String path = parsedUrl.getPath();

            url = parsedUrl.getScheme() + "://" + parsedUrl.getHost() + path;
        }

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        return url;
    }

    /**
     * Get a predictable hostname for the given image identifier
     *
     * @param imageIdentifier Image identifier to get host for
     * @return Hostname in string format
     */
    private String getHostForImageIdentifier(String imageIdentifier) {
        int dec = Integer.parseInt(imageIdentifier.substring(0, 2), 16);

        return serverUrls[dec % serverUrls.length];
    }

    /**
     * Validate a local file
     *
     * @param file File to validate
     * @throws IllegalArgumentException
     * @throws FileNotFoundException
     */
    private void validateLocalFile(File file) throws IllegalArgumentException, FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("The system cannot find the file specified");
        }

        if (file.length() == 0) {
            throw new IllegalArgumentException("The specified file was empty");
        }
    }

}
