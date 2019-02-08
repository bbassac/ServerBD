package hello.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Livre non trouvé")
public class BookNotFoundException extends RuntimeException  {
}
