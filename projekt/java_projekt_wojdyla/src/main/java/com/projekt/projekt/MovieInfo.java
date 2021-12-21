package com.projekt.projekt;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class MovieInfo {
    String title = null;
    String api_key = "977e1980";
   

    public MovieInfo(String title){
        String parsedTitle = title.replaceAll(" ", "+");
        this.title = parsedTitle;
    }


    private JsonNode sendReq(){
        URL url = null;
        try{
            url = new URL("http://www.omdbapi.com/?t="+title+"&apikey=977e1980");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");   
            InputStream responseStream = connection.getInputStream();    

            ObjectMapper mapper = new ObjectMapper();
            JsonNode info = mapper.readTree(responseStream);
            
            return info;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();;
        }
        return null;
        
    }

    public String getImg(){
        JsonNode movie = sendReq();
        
        return movie.path("Poster").toString();
    }
    public String getPlot(){
        JsonNode movie = sendReq();
        
        return movie.path("Plot").toString();
    }

    //public static void main(String[] args) {
    //    MovieInfo inf = new MovieInfo("A dog's purpose");
//
    //    System.out.println(inf.getImg());
    //    System.out.println(inf.getPlot());
    //}

}
