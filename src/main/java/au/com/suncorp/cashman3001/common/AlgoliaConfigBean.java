package au.com.suncorp.cashman3001.common;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.algolia.search.APIClient;
import com.algolia.search.ApacheAPIClientBuilder;
import com.algolia.search.Index;

import au.com.suncorp.cashman3001.model.Contact;

@Component
public class AlgoliaConfigBean{

	public static APIClient getAlgoliaClient() {
		APIClient client = (APIClient)session().getAttribute("client");
		if (client==null) {
			client = new ApacheAPIClientBuilder("595MEXSHJJ", "bd9c1364ca19dc9dc6fe83a6ccd09d30").build();
	        session().setAttribute("client", client);
		}
		return client;
	}
	
	public static Index<Contact> getAlgoliaIndex() {
		Index<Contact> index = (Index<Contact>)session().getAttribute("index");
		if (index==null) {
			index = getAlgoliaClient().initIndex("getstarted_actors", Contact.class);
	        session().setAttribute("index", index);
		}
		return index;
	}
	
	public static Index<Contact> getAlgoliaWriteableIndex() {
		Index<Contact> index = (Index<Contact>)session().getAttribute("writeableIndex");
		if (index==null) {
			APIClient client = new ApacheAPIClientBuilder("595MEXSHJJ", "e6c4f318a55c20e2837395d7681de73b").build();
			index = client.initIndex("getstarted_actors", Contact.class);
	        session().setAttribute("writeableIndex", index);
		}
		return index;
	}
	
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

}
