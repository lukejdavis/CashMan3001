package au.com.suncorp.cashman3001.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.algolia.search.APIClient;
import com.algolia.search.ApacheAPIClientBuilder;
import com.algolia.search.Index;
import com.algolia.search.exceptions.AlgoliaException;
import com.algolia.search.objects.Query;
import com.algolia.search.objects.tasks.sync.TaskSingleIndex;
import com.algolia.search.responses.SearchResult;

import au.com.suncorp.cashman3001.Application;
import au.com.suncorp.cashman3001.common.AlgoliaConfigBean;
import au.com.suncorp.cashman3001.model.AlgoliaSearchRequest;
import au.com.suncorp.cashman3001.model.Contact;
import au.com.suncorp.cashman3001.model.Product;

@RestController
public class AlgoliaController {
	   @Autowired
	   RestTemplate restTemplate;
	   
	   @RequestMapping(value = "/template/algolia")
	   public SearchResult<Contact> textSearch(@RequestBody AlgoliaSearchRequest searchString) {
		   try {
			   Index<Contact> index = AlgoliaConfigBean.getAlgoliaIndex();
			   return index.search(new Query(searchString.getQuery()),searchString.getRequestOptions());
		   } catch (AlgoliaException e) {
			   e.printStackTrace();
			   return null;
		   }
	   }

	   @RequestMapping(value = "/template/algolia",method=RequestMethod.POST)
	   public TaskSingleIndex addContacts(@RequestBody List<Contact> contacts) {
		   try {
			   Index<Contact> index = AlgoliaConfigBean.getAlgoliaWriteableIndex();
			   return index.addObjects(contacts);
		   } catch (AlgoliaException e) {
			   e.printStackTrace();
			   return null;
		   }
	   }
	   
	   @RequestMapping(value = "/template/algolia",method=RequestMethod.PUT)
	   public TaskSingleIndex updateContacts(@RequestBody List<Object> contacts) {
		   try {
			   Index<Contact> index = AlgoliaConfigBean.getAlgoliaWriteableIndex();
			   return index.partialUpdateObjects(contacts);
		   } catch (AlgoliaException e) {
			   e.printStackTrace();
			   return null;
		   }
	   }
	   
	   @RequestMapping(value = "/template/algolia",method=RequestMethod.DELETE)
	   public TaskSingleIndex deleteContacts(@RequestBody List<String> contacts) {
		   try {
			   Index<Contact> index = AlgoliaConfigBean.getAlgoliaWriteableIndex();
			   return index.deleteObjects(contacts);
		   } catch (AlgoliaException e) {
			   e.printStackTrace();
			   return null;
		   }
	   }
	   
}


