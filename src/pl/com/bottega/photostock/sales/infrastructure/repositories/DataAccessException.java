package pl.com.bottega.photostock.sales.infrastructure.repositories;

/**
 * Created by maciuch on 14.05.16.
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(Exception e) {
        super(e);
    }

    public DataAccessException(String s) {
        super(s);
    }
}
