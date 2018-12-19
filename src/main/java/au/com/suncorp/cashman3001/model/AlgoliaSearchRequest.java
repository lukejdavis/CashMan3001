package au.com.suncorp.cashman3001.model;

import java.util.Hashtable;

import com.algolia.search.objects.RequestOptions;

public class AlgoliaSearchRequest {

	private String query;
	private RequestOptions requestOptions = new RequestOptions();
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public RequestOptions getRequestOptions() {
		return requestOptions;
	}
	public void setRequestOptions(RequestOptions requestOptions) {
		this.requestOptions = requestOptions;
	}
	
	
	
}
