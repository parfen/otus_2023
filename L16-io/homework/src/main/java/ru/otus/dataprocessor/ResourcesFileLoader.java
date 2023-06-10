package ru.otus.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.otus.model.Measurement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final String fileName;
    private final Gson gson = new Gson();
    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
       try(FileReader reader = new FileReader(fileName)) {
           Type founderListType = new TypeToken<ArrayList<Measurement>>(){}.getType();
           List<Measurement>  measurements = gson.fromJson(reader, founderListType);
           return measurements;
        } catch (Exception e) {
           throw new FileProcessException(e);
        }
    }
}
