package CarmineGargiulo.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String isbn){
        super("Volume with isbn " + isbn + " has not been found" );
    }
}
