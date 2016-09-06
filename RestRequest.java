package localhost.googlemaps.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

/**
 * Class that makes request to Google Maps Directions
 * 
 * @author paul
 * @since 2016-06-09
 *
 */

public class RestRequest {
	/* 
	 * URL of the API we want to connect to
	 */
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	
	/*
	 * CHaracter set to use when encoding URL parameters
	 */
	protected static String charset = "UTF-8";
			
	/*
	 * API key		
	 */
	protected static String key = "AIzaSyAI-b0OwKFzq2tHeLht0JiYzgN2kF6k_l8";
	
	public static void main(String[] args) {
		
		System.out.println("Enter the starting location:");
		Scanner sc = new Scanner(System.in);
		
		String start = sc.nextLine();
		
		System.out.println("Enter your destination:");
		String end = sc.nextLine();
		
		try {
		
			//the origin or starting point for directions
			String origin = start;
			
			//destination or end point for directions
			String destination = end;
			
			//avoid tolls on route
			String avoid = "tolls";
			
			//return type of the response xml / json
			String returnType = "json";
			
			//provides alternate routes
			String alternatives = "true";
			
			//creates the url parameters as a string encoding them the defined charset
			String queryString = String.format("origin=%s&destination=%s&avoid=%s&alternatives=%s&key=%s",
					URLEncoder.encode(origin, charset),
					URLEncoder.encode(destination, charset),
					URLEncoder.encode(avoid, charset),
					URLEncoder.encode(alternatives, charset),
					URLEncoder.encode(key, charset)
			);
			
			//creates a new URL out of the endpoint, returnType and querystring
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");
			
			//if do not get 200 (success) throw exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));			
			
			//loop of buffer line by line until it return null meaning there are no more lines
			while (br.readLine() != null) {
				//print out each line to the screen
				System.out.println(br.readLine());
			}
			
			//close connection to API
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	} // main
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
}//restrequest