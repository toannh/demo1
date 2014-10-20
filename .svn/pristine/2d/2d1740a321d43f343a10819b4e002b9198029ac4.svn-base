/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file
 * that was distributed with this source code.
 */
package vn.chodientu.component.imboclient.Http;

import org.apache.http.impl.client.HttpClientBuilder;
import vn.chodientu.component.imboclient.ServerException;
import vn.chodientu.component.imboclient.Url.Url;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

/**
 * Imbo HTTP client
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class ImboHttpClient implements HttpClient {

    /**
     * Apache HTTP client to use for requests
     */
    private org.apache.http.client.HttpClient webClient;

    /**
     * HTTP parameters to use for requests
     */
    private HttpParams httpParams;

    /**
     * HTTP request headers
     */
    private List<Header> requestHeaders = new ArrayList<Header>();

    /**
     * Response handler for the web client
     */
    private ResponseHandler<ImboResponse> defaultHandler = new ResponseHandler<ImboResponse>() {
        public ImboResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            ImboResponse imboResponse = new ImboResponse();

            HttpEntity entity = response.getEntity();
            Header contentType = response.getFirstHeader("Content-Type");
            Header contentLength = response.getLastHeader("Content-Length");

            imboResponse.setStatusCode(response.getStatusLine().getStatusCode());
            imboResponse.setHeaders(response.getAllHeaders());

            if (contentType != null) {
                imboResponse.setContentType(contentType.getValue().split(";")[0].trim());
            }

            if (contentLength != null) {
                imboResponse.setContentLength(Long.parseLong(contentLength.getValue()));
            }

            if (entity != null && contentType.getValue().startsWith("image/")) {
                imboResponse.setRawBody(EntityUtils.toByteArray(entity));
            } else if (entity != null) {
                imboResponse.setBody(EntityUtils.toString(entity));
            }

            return imboResponse;
        }
    };

    /**
     * {@inheritDoc}
     */
    public ImboResponse post(Url url, String data) throws IOException {
        return post(url, data, null);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse post(Url url, String data, List<Header> headers) throws IOException {
        return post(url.toUri(), data, headers);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse post(URI url, String data) throws IOException {
        return post(url, data, null);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse post(URI url, String data, List<Header> headers) throws IOException {
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(data));

        if (headers != null) {
            post.setHeaders(headers.toArray(new Header[0]));
        }

        return this.request(post);
    }

    /**
     * {@inheritDoc}
     */
    public Response post(URI url, InputStream input) throws IOException {
        return this.post(url, input, null);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse post(URI url, InputStream input, List<Header> headers) throws IOException {
        byte[] data = readInputStream(input);

        HttpPost post = new HttpPost(url);
        post.setEntity(new ByteArrayEntity(data));

        if (headers != null) {
            post.setHeaders(headers.toArray(new Header[0]));
        }

        return this.request(post);
    }

    /**
     * {@inheritDoc}
     */
    public Response post(URI url, File file) throws IOException {
        return this.post(url, file, null);
    }

    /**
     * {@inheritDoc}
     */
    public Response post(URI url, File file, List<Header> headers) throws IOException {
        FileInputStream fileStream = new FileInputStream(file);

        boolean contentTypeSpecified = false;
        if (headers != null) {
            Iterator<Header> iterator = headers.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getName().equals("Content-Type")) {
                    contentTypeSpecified = true;
                    break;
                }
            }
        }

        if (contentTypeSpecified == false) {
            String contentType = URLConnection.guessContentTypeFromStream(fileStream);

            if (contentType != null) {
                headers.add(new BasicHeader("Content-Type", contentType));
            }
        }

        return this.post(url, fileStream, headers);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse get(URI url) throws IOException {
        return this.request(new HttpGet(url));
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse get(Url url) throws IOException {
        return this.get(url.toUri());
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse head(URI url) throws IOException {
        return this.request(new HttpHead(url));
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse head(Url url) throws IOException {
        return this.head(url.toUri());
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse delete(URI url) throws IOException {
        return this.request(new HttpDelete(url));
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse delete(Url url) throws IOException {
        return this.delete(url.toUri());
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(URI url, String data) throws IOException {
        return this.put(url, data, null);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(URI url, String data, List<Header> headers) throws IOException {
        HttpPut put = new HttpPut(url);
        put.setEntity(new StringEntity(data));

        if (headers != null) {
            put.setHeaders(headers.toArray(new Header[0]));
        }

        return this.request(put);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(URI url, InputStream input) throws IOException {
        return this.put(url, input, null);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(URI url, InputStream input, List<Header> headers) throws IOException {
        byte[] data = readInputStream(input);

        HttpPut put = new HttpPut(url);
        put.setEntity(new ByteArrayEntity(data));

        if (headers != null) {
            put.setHeaders(headers.toArray(new Header[0]));
        }

        return this.request(put);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(URI url, File file) throws IOException {
        return this.put(url, file, null);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(URI url, File file, List<Header> headers) throws IOException {
        return this.put(url, new FileInputStream(file));
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(Url url, String data) throws IOException {
        return this.put(url.toUri(), data, null);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(Url url, String data, List<Header> headers) throws IOException {
        return this.put(url.toUri(), data, headers);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(Url url, InputStream input) throws IOException {
        return this.put(url.toUri(), input, null);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(Url url, InputStream input, List<Header> headers) throws IOException {
        return this.put(url.toUri(), input, headers);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(Url url, File file) throws IOException {
        return this.put(url, file, null);
    }

    /**
     * {@inheritDoc}
     */
    public ImboResponse put(Url url, File file, List<Header> headers) throws IOException {
        return this.put(url.toUri(), file, headers);
    }

    /**
     * {@inheritDoc}
     */
    public ImboHttpClient setRequestHeaders(List<Header> headers) {
        requestHeaders = headers;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public List<Header> getRequestHeaders() {
        return this.requestHeaders;
    }

    /**
     * Returns a set of HTTP parameters
     *
     * @return HTTP parameters
     */
    public org.apache.http.client.HttpClient getHttpClient() {
        if (webClient == null) {
            this.setHttpClient(getDefaultHttpClient());
        }

        return webClient;
    }

    /**
     * Set HTTP parameters
     *
     * @return HTTP client instance
     */
    public HttpClient setHttpClient(org.apache.http.client.HttpClient httpClient) {
        webClient = httpClient;

        return this;
    }

    /**
     * Get the default response handler
     *
     * @return Default response handler
     */
    public ResponseHandler<ImboResponse> getResponseHandler() {
        return defaultHandler;
    }

    /**
     * Perform a request of the given HTTP method against the given URL
     *
     * @param request Request to perform
     * @return HTTP response
     */
    protected ImboResponse request(HttpRequestBase request) throws IOException {
        // Add request headers to outgoing request
        for (Header header : requestHeaders) {
            request.addHeader(header);
        }

        // Perform request using default handler
        ImboResponse response = getHttpClient().execute(request, defaultHandler);

        // Check for errors and throw exception if encountering any
        if (response.isError()) {
            ServerException exception = new ServerException(
                    response.getImboErrorDescription(),
                    response.getStatusCode()
            );
            exception.setResponse(response);

            throw exception;
        }

        // Return response
        return response;
    }

    /**
     * Get a default HTTP client
     *
     * @return Default HTTP params with some basic options set
     */
    protected org.apache.http.client.HttpClient getDefaultHttpClient() {
        /*
        httpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, "UTF_8");
        HttpProtocolParams.setUseExpectContinue(httpParams, false);
        HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
        HttpConnectionParams.setSoTimeout(httpParams, 20000);

        PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
        cm.setDefaultMaxPerRoute(500);

        DefaultHttpClient client = new DefaultHttpClient(cm);
        client.setParams(httpParams);
        */
        return HttpClientBuilder.create().build();
    }

    /**
     * Read input stream into a byte array
     *
     * @param input Input stream to read from
     * @return Byte array with the contents of the input stream
     * @throws IOException
     */
    protected byte[] readInputStream(InputStream input) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int read;
        byte[] data = new byte[16384];

        while ((read = input.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, read);
        }

        return buffer.toByteArray();
    }

}
