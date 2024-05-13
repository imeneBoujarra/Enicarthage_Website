package tn.enicarthage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 si la data base n'existe pas lexception se déclanche
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RessourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RessourceNotFoundException(String msg) {
		super(msg);
	}
}
