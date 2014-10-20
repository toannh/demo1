/**
 * This file is part of the imboclient-java package
 *
 * (c) Espen Hovlandsdal <espen@hovlandsdal.com>
 *
 * For the full copyright and license information, please view the LICENSE file that was
 * distributed with this source code.
 */
package vn.chodientu.component.imboclient.Images;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Images response helper
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class ImagesResponse {

    private ArrayList<Image> images;
    private int totalHits = 0;
    private int hits = 0;
    private int pageNumber = 0;
    private int limit = 0;
    
    public ImagesResponse(JSONObject response) throws JSONException {
    	JSONObject search = response.getJSONObject("search");
    	JSONArray images  = response.getJSONArray("images");
    	
    	totalHits   = search.getInt("hits");
    	hits        = search.getInt("count");
    	pageNumber  = search.getInt("page");
    	limit       = search.getInt("limit");
    	this.images = new ArrayList<Image>();
    	
    	int numImages = images.length();
    	for (int i = 0; i < numImages; i++) {
    		this.images.add(new Image(images.getJSONObject(i)));
    	}
    }
    
    public int getTotalHits() {
    	return totalHits;
    }
    
    public int getHits() {
    	return hits;
    }    
    
    public int getPageNumber() {
    	return pageNumber;
    }
    
    public int getLimit() {
    	return limit;
    }
    
    public List<Image> getImages() {
    	return images;
    }

}