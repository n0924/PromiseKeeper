package persistence;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//CITE: CPSC210 JsonSerializationDemo
//Represents a writer that writes JSON representation of List to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: constructs writer to write to desitnation file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //CITE: CPSC210 JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: opens writer; throws FileNotFoundException
    // if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        File file = new File(destination);
        writer = new PrintWriter(file);
    }

    //CITE: CPSC210 JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: writes JSON representation of list to file
    public void write(Workroom wr) {
        JSONObject json = wr.toJson();
        saveToFile(json.toString(TAB));
    }

    //CITE: CPSC210 JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: writes string to file
    private  void saveToFile(String json) {
        writer.print(json);
    }
}







