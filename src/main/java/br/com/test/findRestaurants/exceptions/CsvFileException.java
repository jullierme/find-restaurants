package br.com.test.findRestaurants.exceptions;

public class CsvFileException extends RuntimeException {
    public CsvFileException(String errorMessage) {
        super(errorMessage);
    }
}
