package Chat;

import javax.json.*;
import java.util.Scanner;
import java.io.*;

public class FileWork {
    private File in;
    private PrintStream out;
    boolean delitel;

    public FileWork(String inName, String outName) throws IOException {
        this.in = new File(inName);
        out = new PrintStream(new File(outName));
        delitel = false;
    }

    public void readJSON(Messages messages) throws FileNotFoundException {
        String content = new Scanner(new File(in.getName())).useDelimiter("\\Z").next();
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonArray items = reader.readArray();
        reader.close();
        for (JsonValue item : items) {
            messages.addMessage(new Message().parseFromJson((JsonObject) item));
        }
    }

    public void wr(Messages messages) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Message item : messages.get()) {
            JsonObject personObject = Json.createObjectBuilder()
                    .add("id", item.getId())
                    .add("author", item.getAuthor())
                    .add("timestamp", item.getTimestamp())
                    .add("message", item.getMessage())
                    .build();
            jsonArrayBuilder.add(personObject);
        }
        JsonArray jsonArray = Json.createArrayBuilder().add(jsonArrayBuilder).build();
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        writer.writeArray(jsonArray);
        writer.close();
        out.print(stringWriter.getBuffer().toString());
    }
}