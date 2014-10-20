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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Query class for the images resource
 *
 * @author Espen Hovlandsdal <espen@hovlandsdal.com>
 */
public class Query {
	
	/**
	 * Sort descending
	 */
	public static final String SORT_DESC = "desc";
	
	/**
	 * Sort ascending
	 */
	public static final String SORT_ASC  = "asc";

    /**
     * The page to get
     */
    private int page = 1;

    /**
     * The maximum number of images to get
     */
    private int limit = 20;

    /**
     * Whether to return meta data for the images or not
     */
    private boolean returnMetadata = false;

    /**
     * Date to start fetching from
     */
    private Date from;

    /**
     * Date to stop fetching at
     */
    private Date to;
    
    /**
     * Ids to filter on
     */
    private ArrayList<String> ids;
    
    /**
     * Checksums to filter on
     */
    private ArrayList<String> checksums;
    
    /**
     * Fields to include
     */
    private ArrayList<String> fields;
    
    /**
     * Sorting details
     */
    private ArrayList<String> sort;

    /**
     * Get the page property
     *
     * @return Page number
     */
    public int page() {
        return page;
    }

    /**
     * Set the page property
     *
     * @param pageNum Page number
     * @return Returns this query instance
     */
    public Query page(int pageNum) {
        this.page = pageNum;
        return this;
    }

    /**
     * Get the limit property
     *
     * @return Max number of items
     */
    public int limit() {
        return limit;
    }

    /**
     * Set the limit property
     *
     * @param limit Max number of items
     * @return Returns this query instance
     */
    public Query limit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Get whether to return the meta data for each image along with the base image data
     *
     * @return Whether to return meta data or not
     */
    public boolean returnMetadata() {
        return returnMetadata;
    }

    /**
     * Set whether to return the meta data for each image along with the base image data
     *
     * @param returnMetadata True if meta data is wanted, false otherwise
     * @return Returns this query instance
     */
    public Query returnMetadata(boolean returnMetadata) {
        this.returnMetadata = returnMetadata;
        return this;
    }

    /**
     * Gets the lower date limit to fetch from
     *
     * @return Date to fetch from
     */
    public Date from() {
        return from;
    }

    /**
     * Sets the date to fetch from when querying
     *
     * @param from The lower date limit to fetch from
     * @return Returns this query instance
     */
    public Query from(Date from) {
        this.from = from;
        return this;
    }

    /**
     * Gets the upper date limit to fetch from
     *
     * @return Date to fetch to
     */
    public Date to() {
        return to;
    }

    /**
     * Sets the date to fetch to when querying
     *
     * @param to The upper date limit to fetch to
     * @return Returns this query instance
     */
    public Query to(Date to) {
        this.to = to;
        return this;
    }
    
    /**
     * Gets the specified IDs to fetch
     * 
     * @return List of IDs
     */
    public List<String> ids() {
    	return ids;
    }
    
    /**
     * Sets the list of IDs to fetch
     * 
     * @param ids The list of IDs to fetch
     * @return Returns this query instance
     */
    public Query ids(List<String> ids) {
    	this.ids = (ArrayList<String>) ids;
    	return this;
    }
    
    /**
     * Adds a set of IDs to the list of IDs to fetch
     * 
     * @param ids Additional IDs to fetch
     * @return Returns this query instance
     */
    public Query addIds(List<String> ids) {
    	if (this.ids == null || this.ids.isEmpty()) {
    		return this.ids(ids);
    	}
    	
    	this.ids.addAll(ids);
    	return this;
    }
    
    /**
     * Add an IDs to the list of IDs to fetch
     * 
     * @param ids Additional ID to fetch
     * @return Returns this query instance
     */
    public Query addId(String id) {
    	if (this.ids == null) {
    		this.ids = new ArrayList<String>();
    	}
    	
    	this.ids.add(id);
    	return this;
    }
    
    /**
     * Gets the specified checksums to fetch
     * 
     * @return List of checksums
     */
    public List<String> checksums() {
    	return ids;
    }
    
    /**
     * Sets the list of checksums to fetch
     * 
     * @param checksums The list of checksums to fetch
     * @return Returns this query instance
     */
    public Query checksums(List<String> checksums) {
    	this.checksums = (ArrayList<String>) checksums;
    	return this;
    }
    
    /**
     * Adds a set of checksums to the list of checksums to fetch
     * 
     * @param checksums Additional checksums to fetch
     * @return Returns this query instance
     */
    public Query addChecksums(List<String> checksums) {
    	if (this.checksums == null || this.checksums.isEmpty()) {
    		return this.checksums(checksums);
    	}
    	
    	this.checksums.addAll(checksums);
    	return this;
    }
    
    /**
     * Add an checksums to the list of checksums to fetch
     * 
     * @param checksums Additional checksum to fetch
     * @return Returns this query instance
     */
    public Query addChecksum(String checksum) {
    	if (this.checksums == null) {
    		this.checksums = new ArrayList<String>();
    	}
    	
    	this.checksums.add(checksum);
    	return this;
    }
    
    /**
     * Gets the specified fields to include
     * 
     * @return List of fields
     */
    public List<String> fields() {
    	return fields;
    }
    
    /**
     * Sets the list of fields to fetch
     * 
     * @param fields The list of fields to include
     * @return Returns this query instance
     */
    public Query fields(List<String> fields) {
    	this.fields = (ArrayList<String>) fields;
    	return this;
    }
    
    /**
     * Adds a set of fields to the list of fields to fetch
     * 
     * @param fields Additional fields to fetch
     * @return Returns this query instance
     */
    public Query addFields(List<String> fields) {
    	if (this.fields == null || this.fields.isEmpty()) {
    		return this.fields(fields);
    	}
    	
    	this.fields.addAll(fields);
    	return this;
    }
    
    /**
     * Add an fields to the list of fields to fetch
     * 
     * @param fields Additional ID to fetch
     * @return Returns this query instance
     */
    public Query addField(String field) {
    	if (this.fields == null) {
    		this.fields = new ArrayList<String>();
    	}
    	
    	this.fields.add(field);
    	return this;
    }
    
    /**
     * Returns a list of the current sort parameters
     * 
     * @return List of current sort parameters (null if not set)
     */
    public List<String> sort() {
    	return this.sort;
    }
    
    /**
     * Set sort parameters
     * 
     * @param sort Sort parameters
     * @return Returns this query instance
     */
    public Query sort(List<String> sort) {
    	this.sort = (ArrayList<String>) sort;
    	return this;
    }
    
    /**
     * Add sort parameters to the list of defined sorts
     * 
     * @param sort List of sort parameters to add
     * @return Returns this query instance
     */
    public Query addSorts(List<String> sort) {
    	if (this.sort == null || this.sort.isEmpty()) {
    		return this.sort(sort);
    	}
    	
    	this.sort.addAll(sort);
    	return this;
    }
    
    /**
     * Add sort parameter to list of defined sorts
     * 
     * @param field Field to sort on
     * @return Returns this query instance
     */
    public Query addSort(String field) {
    	return this.addSort(field, null);
    }
    
    /**
     * Add sort parameter to list of defined sorts
     * 
     * @param field Field to sort on
     * @param direction Direction to sort (use SORT_DESC / SORT_ASC constants)
     * @return Returns this query instance
     */
    public Query addSort(String field, String direction) {
    	if (this.sort == null) {
    		this.sort = new ArrayList<String>();
    	}
    	
    	String sort = field;
    	if (direction != null) {
    		sort = sort + ":" + direction;
    	}
    	
    	this.sort.add(sort);
    	return this;
    }
    
    /**
     * Returns the parameters as a HashMap
     * 
     * @return HashMap of key => values
     */
    public HashMap<String, String> toHashMap() {
    	HashMap<String, String> params = new HashMap<String, String>();
    	
    	if (this.page() > 0) {
    		params.put("page", Integer.toString(this.page()));
    	}
    	
    	if (this.limit() > 0) {
    		params.put("limit", Integer.toString(this.limit()));
    	}
    	
    	if (this.returnMetadata()) {
    		params.put("metadata", "1");
    	}
    	
    	if (this.from() != null) {
    		params.put("from", Long.toString(this.from().getTime()));
    	}
    	
    	if (this.to() != null) {
    		params.put("to", Long.toString(this.to().getTime()));
    	}
    	
    	return params;
    }

}