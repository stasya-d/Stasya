package Chat;

import java.io.*;
import java.util.*;

public class Chat {
    public static void main(String[] args) {
        Messages messages = new Messages();
        try {
            FileWork fw = new FileWork("in.json", "out.json");
            char operation = '0';
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("[\n\r]");
            System.out.print("Press: \'d\' for downloading,\n" +
                    "       \'s\' for saving,\n" +
                    "       \'n\' for add new message,\n" +
                    "       \'h\' view history in chronological order,\n" +
                    "       \'i\' delete the message by ID,\n" +
                    "       \'a\' search in the history of messages by author,\n" +
                    "       \'k\' search in the history by keyword,\n" +
                    "       \'w\' search in the history of regular expression,\n" +
                    "       \'p\' view message history for a certain period,\n" +
                    "       \'e\' exit\n");
            while (operation != 'e') {
                if (sc.hasNext()) {
                    operation = sc.next().charAt(0);
                }
                switch (operation) {
                    case 'd': {
                        try {
                            fw.readJSON(messages);
                            System.out.print("Downloading:\n");
                            messages.print();
                        } catch (FileNotFoundException e) {
                            System.out.print("File not found!\n");
                        }
                        break;
                    }
                    case 's': {
                        fw.wr(messages);
                        System.out.print("Saving:\n");
                        messages.print();
                        break;
                    }
                    case 'n': {
                        messages.addMessage((new Message()).enter());
                        break;
                    }
                    case 'h': {
                        messages.viewChronological();
                        break;
                    }
                    case 'i': {
                        System.out.print("Enter ID: ");
                        if (messages.deleteID(sc.next())) {
                            System.out.print("Good.");
                        } else System.out.print("Bad.");
                        break;
                    }
                    case 'a': {
                        messages.findAuthor(sc.next());
                        break;
                    }
                    case 'k': {
                        messages.findWord(sc.next());
                        break;
                    }
                    case 'w': {
                        break;
                    }
                    case 'p': {
                        messages.findPeriod(sc.nextLong(), sc.nextLong());
                        break;
                    }
                    case 'e': {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (InputMismatchException e) {
            System.out.print("InputMismatchException!");
        } catch (NoSuchElementException e) {
            System.out.print("NoSuchElementException!");
        }
    }
}