package br.com.test.findRestaurants.parameters;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.exceptions.ParametersException;

public interface ParametersToBean {
    ParametersBean toBean(String[] args) throws ParametersException;
}
