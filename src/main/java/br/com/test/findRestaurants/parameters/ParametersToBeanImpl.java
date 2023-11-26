package br.com.test.findRestaurants.parameters;

import br.com.test.findRestaurants.beans.ParametersBean;
import br.com.test.findRestaurants.exceptions.ParametersException;
import br.com.test.findRestaurants.util.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.springframework.stereotype.Service;

@Service
public class ParametersToBeanImpl implements ParametersToBean {
    @Override
    public ParametersBean toBean(String[] args) throws ParametersException {
        if (args.length == 0 || args[0].isEmpty()) {
            throw new ParametersException(Constants.NO_ARGS_MESSAGE);
        }

        try {
            String jsonString = args[0];
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .create();
            return gson.fromJson(jsonString, ParametersBean.class);
        } catch (JsonSyntaxException e) {
            throw new ParametersException(Constants.INVALID_ARGS_MESSAGE);
        }
    }
}
