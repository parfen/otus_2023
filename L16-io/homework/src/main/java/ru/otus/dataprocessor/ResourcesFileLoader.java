package ru.otus.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.otus.model.Measurement;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final String fileName;
    private final Gson gson = new Gson();
    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
       try(InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
           BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
           Type founderListType = new TypeToken<ArrayList<Measurement>>(){}.getType();
           return gson.fromJson(reader, founderListType);
        } catch (Exception e) {
           throw new FileProcessException(e);
        }
    }
}
