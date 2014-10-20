/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient.Http;

import java.util.HashMap;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Client response
 * 
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class ImboResponse implements Response {

	/**
	 * Headers for the response
	 */
	private HashMap<String, String> headers;
	
	/**
	 * Body of the response
	 */
	private String body = "";
	
	/**
	 * Content type for this response
	 */
	private String contentType;
	
	/**
	 * Content length for this response
	 */
	private long contentLength;
	
	/**
	 * Raw body of the response
	 */
	private byte[] rawBody;
	
	/**
	 * Status code of the response
	 */
	private int statusCode;
	
	/**
	 * {@inheritDoc}
	 */
	public HashMap<String, String> getHeaders() {
		return headers;
	}

	/**
	 * {@inheritDoc}
	 */
	public Response setHeaders(Header[] headers) {
		HashMap<String, String> resHeaders = new HashMap<String, String>();
		for (Header header : headers) {
			resHeaders.put(header.getName(), header.getValue());
		}
		
		this.headers = resHeaders;
		
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getBody() {
		return this.body;
	}

	/**
	 * {@inheritDoc}
	 */
	public Response setBody(String body) {
		this.body = body;
		
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public byte[] getRawBody() {
		return this.rawBody;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Response setRawBody(byte[] body) {
		this.rawBody = body;
		
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getStatusCode() {
		return this.statusCode;
	}

	/**
	 * {@inheritDoc}
	 */
	public Response setStatusCode(int code) {
		this.statusCode = code;
		
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getImboErrorCode() {
		if (body == null) {
			return 0;
		}
		
		try {
			JSONObject body = new JSONObject(this.body);
			if (!body.has("error")) {
				return 0;
			}
			
			JSONObject error = body.getJSONObject("error");
			if (!error.has("imboErrorCode")) {
				return 0;
			}
			
			return error.optInt("imboErrorCode", 0);
		} catch (JSONException e) {
			return 0;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getImboErrorDescription() {
		if (body == null || body == "") {
			return "Empty body";
		}
		
		try {
			JSONObject body = new JSONObject(this.body);
			if (!body.has("error")) {
				return "Error not specified";
			}
			
			JSONObject error = body.getJSONObject("error");
			
			return error.optString("message", "Error message not specified");
		} catch (JSONException e) {
			return e.getMessage();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSuccess() {
		int code = getStatusCode();
		
		return (code < 300 && code >= 200);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isError() {
		return getStatusCode() >= 400;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getImageIdentifier() {
		if (headers == null) {
			return null;
		}
		
		return headers.get("X-Imbo-ImageIdentifier");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getContentType() {
		return this.contentType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Response setContentType(String contentType) {
		this.contentType = contentType;
		
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public long getContentLength() {
		return this.contentLength;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Response setContentLength(long contentLength) {
		this.contentLength = contentLength;
		
		return this;
	}
	
	/**
	 * Returns the response as a string (only the response body)
	 * 
	 * @return The response body
	 */
	public String toString() {
		return body;
	}
	
	/**
	 * Returns the response body as a JSON object
	 * 
	 * @return Response body as a JSON object
	 */
	public JSONObject asJsonObject() {
		JSONObject body;
		try {
			body = new JSONObject(this.body);
		} catch (Exception e) {
			return null;
		}
		
		return body;
	}

}