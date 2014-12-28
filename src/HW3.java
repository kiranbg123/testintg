
import java.io.*;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.FileManager;

public class HW3 {


	static String defaultNameSpace = "http://org.semwebprogramming/chapter2/people#";
	static Model model = null;

	public static void main(String[] args) {

		InputStream in = null;

		try {

		   in = new FileInputStream(new File("seinfeld.rdf"));


		// Create an empty in-memory model and populate it from the graph
		//model = ModelFactory.createMemModelMaker().createModel(null);
		model = ModelFactory.createOntologyModel();
		model.read(in,defaultNameSpace); // null base URI, since model URIs are absolute
		in.close();


	   } catch (Exception e) {

          e.printStackTrace();
       }


		// ADD YOUR QUERY IN THE LINES BELOW. YOU SHOULD NOT HAVE TO CHANGE THE NAMESPACE PREFIXES
		String queryString =
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
						"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
					    "PREFIX dc: <http://purl.org/dc/elements/1.1/> " +
					    "PREFIX seinfeld: <http://www.seinfeld.com/seinfeld/> " +
						"PREFIX ppl: <http://www.randompeople.com/ppl/> " +
						"PREFIX job: <http://www.job.com/job/> " +

						"SELECT ?uri ?age " +
						"WHERE {" +
						"      ?uri ppl:hasAge ?age . " +
						"      FILTER(xsd:integer(?age)>30)" +
						"      }";

        System.out.println("Issuing query #1....");

		//call this method to execute your SPARQL query
		issueSPARQL(queryString);


	    //Add your second query
		String queryString2 =
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
						"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
					    "PREFIX dc: <http://purl.org/dc/elements/1.1/> " +
					    "PREFIX seinfeld: <http://www.seinfeld.com/seinfeld/> " +
						"PREFIX ppl: <http://www.randompeople.com/ppl/> " +
						"PREFIX job: <http://www.job.com/job/> " +

						"SELECT ?surname ?person " +
						"WHERE {" +
						"      <seinfeld:George> foaf:knows ?person . " +
						"       ?person foaf:surname ?surname ." +
						"      }";

        System.out.println("Issuing query #2....");

		//call this method to execute your SPARQL query
		issueSPARQL(queryString2);

		String queryString3 =
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
						"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
					    "PREFIX dc: <http://purl.org/dc/elements/1.1/> " +
					    "PREFIX seinfeld: <http://www.seinfeld.com/seinfeld/> " +
						"PREFIX ppl: <http://www.randompeople.com/ppl/> " +
						"PREFIX job: <http://www.job.com/job/> " +
						"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +

						"SELECT ?name ?surname ?age " +
						"WHERE {" +
						"      ?name rdf:type <foaf:Person> . " +
						"      ?name  foaf:surname ?surname ." +
						"      ?name ppl:hasAge ?age ." +
						"      }";

        System.out.println("Issuing query #3....");

		//call this method to execute your SPARQL query
		issueSPARQL(queryString3);
		
		String queryString4 =
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
						"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
					    "PREFIX dc: <http://purl.org/dc/elements/1.1/> " +
					    "PREFIX seinfeld: <http://www.seinfeld.com/seinfeld/> " +
						"PREFIX ppl: <http://www.randompeople.com/ppl/> " +
						"PREFIX job: <http://www.job.com/job/> " +
						"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +

						"SELECT ?surname " +
						"WHERE {" +
						"      <seinfeld:Elaine> ppl:hasDated ?bn . " +
						"		?bn ?pred ?name ." +
						"		?name foaf:surname ?surname ." +
						"      }";

        System.out.println("Issuing query #4....");

		//call this method to execute your SPARQL query
		issueSPARQL(queryString4);
		
		String queryString5 =
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
						"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
					    "PREFIX dc: <http://purl.org/dc/elements/1.1/> " +
					    "PREFIX seinfeld: <http://www.seinfeld.com/seinfeld/> " +
						"PREFIX ppl: <http://www.randompeople.com/ppl/> " +
						"PREFIX job: <http://www.job.com/job/> " +
						"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +

						"SELECT  ?name ?job ?salary ?company ?boss " +
						"WHERE {" +
						"      ?name rdf:type <foaf:Person> ; " +
						"             ppl:hasJob ?job ." +
						"       ?job job:salary ?salary ." +
						"       OPTIONAL {?job job:boss ?boss ;" +
						"      			       job:organization ?company }" +
						"      }";

        System.out.println("Issuing query #5....");

		//call this method to execute your SPARQL query
		issueSPARQL(queryString5);
		String queryString6 =
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
						"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
					    "PREFIX dc: <http://purl.org/dc/elements/1.1/> " +
					    "PREFIX seinfeld: <http://www.seinfeld.com/seinfeld/> " +
						"PREFIX ppl: <http://www.randompeople.com/ppl/> " +
						"PREFIX job: <http://www.job.com/job/> " +
						"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +

						"SELECT  DISTINCT ?name ?mother ?father ?cousin " +
						"WHERE {" +
						"{" +
							"?name  ppl:hasFather ?father . " +
	                         "OPTIONAL {?name ppl:hasMother ?mother .}" +
	                         "OPTIONAL {?name ppl:hasCousin ?cousin .}" +
	                                
						"}" +
						
						"FILTER(?name =<seinfeld:George> || ?name =<seinfeld:Jerry> ||?name =<seinfeld:Elaine>)" +
						"      }";

        System.out.println("Issuing query #6....");

		//call this method to execute your SPARQL query
		issueSPARQL(queryString6);
		
        //...fill in the rest of your queries
		


	}


    public static void issueSPARQL(String queryString) {

        Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet response = qe.execSelect();

		// Output query results
		ResultSetFormatter.out(System.out, response, query);

		// Important - free up resources used running the query
		qe.close();

    }//method issueSPARQL


}

