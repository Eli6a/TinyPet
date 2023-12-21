package foo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Arrays;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.server.spi.config.Named;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@WebServlet(name = "PetServlet", urlPatterns = { "/petition" })
public class PetitionServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// response.setContentType("text/html");
		// response.setCharacterEncoding("UTF-8");

		// String thisUrl = request.getRequestURI();

		// // s'il n'y a pas de petition dans l'url, retourne à la page d'accueil.
		// if (request.getUserPrincipal() == null) {
		// 	response.getWriter()
		// 		.println("Error : could not create petition");
		// 	response.sendRedirect("homaPage.html");

		// } 

		// DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		// // Create petition
		// 	Random r = new Random();
		// 	Entity e = new Entity("etition", r + "_" + new Date());
		// 	e.setProperty("title", request.getPetitionTitle());
		// 	e.setProperty("owner", request.getUserName());
		// 	e.setProperty("body", request.getBody());			
		// 	e.setProperty("signature", 0);
			
		// // Get tags

		// 	List<String> tags = request.GetTags();
		// 	e.setProperty("tags", tags);

		// 	datastore.put(e);
			
		// 	response.getWriter().print("<li> created post:" + e.getKey() + "<br>");

        // // Create users
		// for (int i = 0; i < ulist.length; i++) {
		// 	Entity e = new Entity("D2User", "U" + i );
        //     e.setProperty("name", "U"+i);

        //     // Sign Random Petition
		// 	HashSet<String> pets = new HashSet<String>();
		// 	while (pets.size() < 5) {
        //         int rpet=r.nextInt(plist.length);
		// 		pets.add("P" + rpet);
        //         plist[rpet].setProperty("nb", (int)plist[rpet].getProperty("nb")+1);
		// 	}
        //     e.setProperty("signed", pets);

        //     ulist[i]=e;
        //     datastore.put(e);
        //     response.getWriter().print("<li> created user:" + e.getKey() + "<br>");
        // }
        // for (int i=0;i<plist.length;i++) {
        //     datastore.put(plist[i]);
        // }
	}

	public Entity addScore(@Named("title") String title, @Named("owner") String owner, @Named("body") String body, @Named("tags") List<String> tags) {
		
		// Create petition
		Random r = new Random();
		Entity e = new Entity("Petition", r);
		e.setProperty("title", title);
		e.setProperty("owner", owner);
		e.setProperty("body", body);
		e.setProperty("date", new Date());			
		e.setProperty("signature", 0);
		
		// Get tags

		e.setProperty("tags", tags);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(e);

		return e;
	}
}