package terry.kong.naceservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import terry.kong.naceservice.entity.Nace;
@Data
public class NaceException extends Exception{
    private String message;
    private HttpStatus status;
    public NaceException(HttpStatus status, String message){
        super();
        this.message= message;
        this.status = status;
    }
}
