package Chat;

import javax.json.*;
import java.util.Scanner;

public class Message {
    private String id;
    private String author;
    private long timestamp;
    private String message;

    public String getId(){
        return id;
    }
    public String getAuthor(){
        return author;
    }
    public long getTimestamp(){
        return timestamp;
    }
    public String getMessage(){
        return message;
    }

    public Message enter() {
        Scanner sc = new Scanner(System.in);
        System.out.print("{\nid:");
        id = sc.next();
        System.out.print("\nauthor:");
        author = sc.next();
        System.out.print("\ntimestamp:");
        timestamp = sc.nextLong();
        System.out.print("\nmessage:");
        message = sc.next();
        System.out.print("}\n");
        return this;
    }

    public Message() {
    }

    public Message(String struct) {
        id = this.read(struct, "id");
        System.out.print(id + "\n");
        author = this.read(struct, "author");
        System.out.print(author + "\n");
        timestamp = new Scanner(this.read(struct, "timestamp").trim()).nextLong();
        System.out.print(timestamp + "\n");
        message = this.read(struct, "message");
        System.out.print(message + "\n");
    }

    public String toStringForJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n  \"id\": \"").append(id)
                .append("\",\n  \"author\": \"").append(author)
                .append("\",\n  \"timestamp\": ").append(timestamp)
                .append(",\n  \"message\": \"").append(message)
                .append("\"");

        return sb.toString();
    }

    public String read(String struct, String find) {
        Scanner sc = new Scanner(struct);
        sc.useDelimiter("[\":,]");
        String s = new String();
        do {
            if (sc.hasNext()) {
                s = new String(sc.next());
            }
        } while (!s.equals(find) && sc.hasNext());
        do {
            if (sc.hasNext()) {
                s = new String(sc.next().trim());
            }
        } while (s.equals("") && sc.hasNext());
        return s;
    }

    public boolean equalsID(String s) {
        if (id.equals(s)) {
            return true;
        }
        return false;
    }

    public boolean equalsAuthor(String s) {
        if (s.equals(this.author)) {
            return true;
        }
        return false;
    }

    public Message parseFromJson(JsonObject jsonObject) {
        author = jsonObject.getString("author");
        message = jsonObject.getString("message");
        timestamp = jsonObject.getJsonNumber("timestamp").longValue();
        id = new String(jsonObject.getString("id"));
        return this;
    }
}
