package br.com.test.findRestaurants.parameters;

import br.com.test.findRestaurants.beans.ParametersBean;
import com.google.gson.JsonSyntaxException;

public interface ParametersToBean {
    ParametersBean toBean(String[] args) throws JsonSyntaxException;
}
