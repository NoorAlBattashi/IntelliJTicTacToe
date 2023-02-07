package org.newtictactoe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class StateManager {
    private static final String FILE_PATH = "data/TicTacToe.json";

    public HashMap<String, String[][]> getPreviousGame() {
// Read json file
        File dataFile = new File(FILE_PATH);
        // create hashmaps to store the values
        HashMap<String, String[][]> storeValues = new HashMap<String, String[][]>();

        try {
            Scanner scanFile = new Scanner(dataFile);
            while (scanFile.hasNextLine()) {
                // System.out.println(scanFile.nextLine());
                // read from json file, convert json to hash map
                Gson gson = new GsonBuilder().create();
                Type type = new TypeToken<Map<String, String[][]>>() {
                }.getType();
                Map<String, String[][]> myMap = gson.fromJson(scanFile.nextLine(), type);

                for (String iString : myMap.keySet()) {
                    storeValues.put(iString, myMap.get(iString));
                }
            }
            scanFile.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return storeValues;
    }


    public void clearFile() {
        try (FileWriter filrWriter = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder().create();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void storeTheGame(String name, String[][] array) {
        HashMap<String, String[][]> store = new HashMap<String, String[][]>();
        store.put(name, array);
        try (FileWriter filrWriter = new FileWriter(FILE_PATH, true)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(store, filrWriter);
            filrWriter.write("\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
