import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Stasya on 27.02.2016.
 */

class Element {
    Element father;
    long key;
    long level;
    boolean equals;
    Element left;
    Element right;

    Element(int newKey) {
        father = null;
        key = newKey;
        equals = true;
        level = -1;
        left = null;
        right = null;
    }

    void print(FileWriter fw) throws IOException {
        fw.write(key + "\n");
    }

    void printScreen() {
        System.out.print("\nSEE " + getKey() + ": level " + getLevel() + "\n");
        if (equals == true) {
            System.out.print("true; ");
        } else System.out.print("false; ");
        if (right != null) {
            System.out.print(right.getLevel() + " is right level; ");
        }
        if (left != null) {
            System.out.print(left.getLevel() + " is left level; ");
        }
        System.out.print("Have ");
        if (father != null) System.out.print("father " + father.key);
        if (right != null) System.out.print("right " + right.key);
        if (left != null) System.out.print("left " + left.key);
        System.out.print("\n");
    }

    long getKey() {
        return key;
    }

    long getLevel() {
        return level;
    }

    void setLevel(int n) {
        level = n;
    }
}

class Tree {
    Element top;
    long countMax;
    // long max;

    Tree() {
        top = null;
    }

    void add(int x) {
        Element e = new Element(x);
        if (top != null) {
            Element now = top;
            boolean input = false;
            while (!input) {
                if (x < now.getKey()) {
                    if (now.left != null) {
                        now = now.left;
                    } else {
                        e.father = now;
                        now.left = e;
                        input = true;
                    }
                }
                if (x > now.getKey()) {
                    if (now.right != null) {
                        now = now.right;
                    } else {
                        e.father = now;
                        now.right = e;
                        input = true;
                    }
                }

                if (now.getKey() == x) {
                    input = true;
                }
            }
        } else {
            top = e;
        }
    }

    boolean print(FileWriter fw) throws IOException {
        Element now = top;
        boolean right = true;
        boolean left = true;
        boolean up = false;
        if (now != null) {
            System.out.print("Writing in file is start...\n");
            now.print(fw);
            now.printScreen();

            for (; ; ) {
                if (now.father == null && !right && up) return true;
                System.out.print(":" + now.key + "UP - " + up + '\n');
                if (now.left != null && !up && left) {
                    now = now.left;
                    now.print(fw);
                    now.printScreen();
                    if (now.right != null) {
                        right = true;
                    }
                } else {
                    if (now.right != null && right && !up) {
                        now = now.right;
                        now.print(fw);
                        now.printScreen();
                        left = true;
                    } else {
                        if (!up) {
                            up = true;
                            left = false;
                        }
                    }
                }
                if (now.father == null && !right && up) return true;
                if (up && now.father == null) return true;
                if (up) {
                    if (!right && !left && now.father == null) return true;
                    if (now.father.left != null &&
                            now == now.father.left) {
                        now = now.father;
                        left = false;
                        right = true;
                        if (now.right != null && right) {
                            up = false;
                            right = true;
                        }
                    } else if (now.father.right != null && now == now.father.right) {
                        right = false;
                        left = false;
                        now = now.father;
                    } else {
                        now = now.father;
                    }
                }
                if (!right && !left) {
                    up = true;
                    left = false;
                    if (now.father != null) {
                        if (now == now.father.right) {
                            right = false;
                        }
                    }
                }
            }
        }
        return true;
    }
}

public class Task01 {
    public static void main(String[] args) {
        Tree tree = new Tree();
        try {
            Scanner sc = new Scanner(new File("tst.in"));
            sc.delimiter();
            while (sc.hasNextInt()) {
                int x = sc.nextInt();
                tree.add(x);
            }
            if (tree.top != null) {
                System.out.print("Top is not NULL.\n");
                FileWriter fw = new FileWriter(new File("tst.out"));
                System.out.print("Tree is good;\n");
                tree.print(fw);
                fw.close();
            }


        } catch (FileNotFoundException e) {
            System.out.print("Error file not found!");
        } catch (IOException e) {
            System.out.print("Error");
        }
    }

}
