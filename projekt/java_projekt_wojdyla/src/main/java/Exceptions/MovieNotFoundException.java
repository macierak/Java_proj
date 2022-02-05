package Exceptions;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
