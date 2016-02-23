package Chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Messages {
    List<Message> messages;

    Messages() {
        messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        if (message != null) {
            messages.add(message);
        }
    }

    public List<Message> get() {
        return messages;
    }

    public void print() {
        for (Message item : messages) {
            System.out.print(item.toStringForJSON() + "\n");
        }
    }

    public boolean deleteID(String del) {

        for (Message item : messages) {
            if (item.equalsID(del)) {
                messages.remove(item);
                return true;
            }
        }
        return false;
    }

    public void findAuthor(String author) throws IOException {
        for (Message item : messages) {
            if (item.equalsAuthor(author.trim())) {
                System.out.print(item.toStringForJSON() + "\n");
            }
        }
    }

    public void viewChronological() {
        Collections.sort(messages, new Comparator<Message>() {
            public int compare(Message o1, Message o2) {
                return (int) (o1.getTimestamp() - o2.getTimestamp());
            }
        });
        print();
    }

    public void findPeriod(long start, long finish) throws IOException {
        for (Message item : messages) {
            if (item.getTimestamp() < finish && item.getTimestamp() > start) {
                System.out.print(item.toStringForJSON() + "\n");
            }
        }
    }

    public void findWord (String s) throws IOException {
        for (Message item : messages) {
            if (item.getMessage().contains(s)) {
                System.out.print(item.toStringForJSON() + "\n");
            }
        }
    }
}