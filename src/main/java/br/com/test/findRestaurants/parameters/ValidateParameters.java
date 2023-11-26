package br.com.test.findRestaurants.parameters;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.exceptions.ParametersException;

public interface ValidateParameters {
    void validate(ParametersBean parametersBean) throws ParametersException;
}
