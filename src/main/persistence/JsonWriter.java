package persistence;

import model.BoughtWantList;
import model.NeedList;
import model.WantList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//CITE: CPSC210 JsonSerializationDemo
//Represents a writer that writes JSON representation of need/want/boughtWant lists to file
public class JsonWriter {
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
    //EFFECTS: writes JSON representation of need list to file
    public void writeNeed(NeedList needs) {
        JSONObject json = needs.toJson();
        saveToFile(json.toString());
    }

    //CITE: CPSC210 JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: writes JSON representation of want list to file
    public void writeWant(WantList wants) {
        JSONObject json = wants.toJson();
        saveToFile(json.toString());
    }

    //CITE: CPSC210 JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: writes JSON representation of boughtWant list to file
    public void writeBoughtWant(BoughtWantList boughtWants) {
        JSONObject json = boughtWants.toJson();
        saveToFile(json.toString());
    }


    //CITE: CPSC210 JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }


    //CITE: CPSC210 JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}







