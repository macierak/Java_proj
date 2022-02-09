package com.projekt.projekt;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Exceptions.MovieNotFoundException;



public class MovieInfo {
    String title = null;
    String api_key = "977e1980";
   

    public MovieInfo(String title){
        String parsedTitle = title.replaceAll(" ", "+");
        this.title = parsedTitle;
    }


    private JsonNode sendReq() throws MovieNotFoundException{
        URL url = null;
        try{
            url = new URL("http://www.omdbapi.com/?t="+title+"&apikey=977e1980");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");   
            InputStream responseStream = connection.getInputStream();    

            ObjectMapper mapper = new ObjectMapper();
            JsonNode info = mapper.readTree(responseStream);
            if(info.path("Response").toString().equals("False")){
                throw new MovieNotFoundException("Nie znaleziono filmu");
            }
            return info;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();;
        }
        return null;
        
    }

    public String getImg(){
        
        //Ze względu na prawa autorkie, nie mogę używać plakatów filmów
        //Jednakże ta funkcja zwróci plakat
        //try{
        //    JsonNode movie = sendReq();
        //    String poster = movie.path("Poster").toString();
        //    return poster.substring(1, poster.length()-1);
        //}catch(MovieNotFoundException e){
        //    e.printStackTrace();
        //    return "https://via.placeholder.com/800x400?text=Plakat+niedostępny";
        //}
         
        
        
        return "https://via.placeholder.com/800x400?text=Plakat+niedostępny";
    }
    public String getPlot(){
        try{
            JsonNode movie = sendReq();
            String plot = movie.path("Plot").toString();
    
            return plot.substring(1, plot.length()-1);
        }catch( MovieNotFoundException e){
            return e.getMessage();
        }catch(StringIndexOutOfBoundsException e){
            return "Opis niedostępny";
        }
        catch(NullPointerException e){
            return "Opis niedostępny";
        }
        
    }

    //public static void main(String[] args) {
    //    MovieInfo inf = new MovieInfo("A dog's purpose");
//
    //    System.out.println(inf.getImg());
    //    System.out.println(inf.getPlot());
    //}

}
