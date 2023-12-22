package foo;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.api.server.spi.auth.EspAuthenticator;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;

@Api(name = "myApi",
     version = "v1",
     audiences = "342296679137-k0ae0p329shp6tcj6k3r2d3khuo7cjq9.apps.googleusercontent.com",
  	 clientIds = {"342296679137-k0ae0p329shp6tcj6k3r2d3khuo7cjq9.apps.googleusercontent.com",
        "927375242383-jm45ei76rdsfv7tmjv58tcsjjpvgkdje.apps.googleusercontent.com"},
     namespace =
     @ApiNamespace(
		   ownerDomain = "helloworld.example.com",
		   ownerName = "helloworld.example.com",
		   packagePath = "")
     )

public class ScoreEndpoint {


	Random r = new Random();

	@ApiMethod(name = "addPetition", httpMethod = HttpMethod.POST)
	public Entity addPetition(Petition pet) {
		Date date = new Date();

		Entity e = new Entity("Petition", r.nextInt(8)+"_"+date);
		e.setProperty("title", pet.title);
		e.setProperty("url", pet.url);
		e.setProperty("owner", pet.owner);
		e.setProperty("body", pet.body);
		e.setProperty("date", date);
		e.setProperty("signature", 0);
		e.setProperty("tags", pet.tags);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		datastore.put(e);
		txn.commit();
		return e;
	}

	@ApiMethod(name = "topPetition", httpMethod = HttpMethod.POST)
	public List<Entity> topPetition() {
		Query q = new Query("Petition").addSort("signature", SortDirection.DESCENDING);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = datastore.prepare(q);
		List<Entity> result = pq.asList(FetchOptions.Builder.withLimit(10));
		return result;
	}

	// @ApiMethod(name = "signPetition", httpMethod = ApiMethod.HttpMethod.POST)
	// public Petition signPetition(UserP user, Petition petition)
	// 		throws UnauthorizedException {

	// 	if (user == null) {
	// 		return null;
	// 	}
	// 	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	// 	 // Récupérer l'entité utilisateur du Datastore
	// 	 Key userKey = KeyFactory.createKey("User", user.credentials); // Assurez-vous d'utiliser la clé appropriée
	// 	 Entity userEntity;
	// 	 try {
	// 		 userEntity = datastore.get(userKey);
	// 	 } catch (Exception e) {
	// 		 // Gérer le cas où l'entité utilisateur n'est pas trouvée
	// 		 e.printStackTrace();
	// 		 return null; // Ou renvoyez une réponse appropriée
	// 	 }
	 
	// 	 // Ajouter dans la liste des petitions signées si la pétition ne l'est pas déjà
	// 	 List<String> signedPetitions = (List<String>) userEntity.getProperty("signedPetitions");
	 
	// 	 if (!signedPetitions.contains(petition.key)) {
	// 		 signedPetitions.add(petition.key);
	 
	// 		 userEntity.setProperty("signedPetitions", signedPetitions);
	// 		 datastore.put(userEntity);
	// 	 }
	// 	 return petition;
		
	// }

	// public boolean userExists(UserP user) {
	// 	Query q = new Query("User")
    //             .setFilter(new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, user.credentials));

	// 	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	//     PreparedQuery pq = datastore.prepare(q);
	    
	//     QueryResultList<Entity> results = pq.asQueryResultList(FetchOptions.Builder.withLimit(1));
    //     if (!results.isEmpty())
	// 		return true;
	// 	else
	// 		return false;
    // }

    // public void addUser(UserP user) {
    //     if (!userExists(user)) {
	// 		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	// 		Entity e = new Entity("User", user.credentials);
	// 		e.setProperty("name", user.name);
	// 		e.setProperty("signedPetitions", user.signedPetitions);
    //         datastore.put(e);
    //     }
    // }
}
