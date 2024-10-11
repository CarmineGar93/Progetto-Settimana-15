package CarmineGargiulo.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String id){
        super("Element with id " + id + " has not been found" );
    }
}
