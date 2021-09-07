package notepad;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Notepad {
    List<String> allContent;
    Stack<List<String>> undoStack;
    Stack<List<String>> redoStack;
    List<String> buffer;

    public Notepad(String text) {
        allContent = new ArrayList<>();
        String[] lines = text.split("\n");
        for (String line : lines) {
            allContent.add(line);
        }
        undoStack = new Stack<>();
        undoStack.push(new ArrayList<>(allContent));
        redoStack = new Stack<>();
    }

    public void display() {
        allContent.forEach(row -> System.out.println(row));
    }

    public boolean display(int fromLine, int toLine) {
        if (fromLine < 1) {
            System.out.println("The value of start index is less tha 1");
            return false;
        }
        if (fromLine > allContent.size()) {
            System.out.println("The value of start index is greater than total lines in document");
            return false;
        }
        if (toLine > allContent.size()) {
            System.out.println("The value of end index is greater than total lines in document");
            return false;
        }
        if (toLine < fromLine) {
            System.out.println("The value of end index is smaller than start index");
            return false;
        }
        for (int i = fromLine; i <= toLine; i++) {
            System.out.println(allContent.get(i - 1));
        }
        return true;
    }

    public boolean insert(int n, String text) {
        if (n < 1 || n > allContent.size()) {
            System.out.println("The value of n is greater than total lines in document or less than one.");
            return false;
        }
        allContent.add(n - 1, text);
        undoStack.push(new ArrayList<>(allContent));
        return true;
    }

    public boolean delete(int n) {
        if (n < 1 || n > allContent.size()) {
            System.out.println("Line number is not proper");
            return false;
        }
        allContent.remove(n - 1);
        undoStack.push(new ArrayList<>(allContent));
        return true;
    }

    public boolean delete(int fromLine, int toLine) {
        if (toLine < fromLine || toLine > allContent.size() || fromLine > allContent.size()) {
            System.out.println("Input Line numbers are not proper");
            return false;
        }
        int j = 0;
        for (int i = fromLine; i <= toLine; i++) {
            allContent.remove(i - j - 1);
            j++;
        }
        undoStack.push(new ArrayList<>(allContent));
        return true;
    }

    public boolean copy(int fromLine, int toLine) {
        if (toLine < fromLine || toLine > allContent.size() || fromLine > allContent.size()) {
            System.out.println("Input Line numbers are not proper");
            return false;
        }
        buffer = new ArrayList<>();
        for (int i = fromLine; i <= toLine; i++) {
            buffer.add(allContent.get(i - 1));
        }
        return true;
    }

    public boolean paste(int n) {
        if (n < 0 || n > allContent.size()) {
            System.out.println("Wrong cursor input");
            return false;
        }
        if (buffer == null || buffer.size() == 0) {
            System.out.println("Nothing present in clipboard");
            return false;
        }
        allContent.addAll(n, buffer);
        undoStack.push(new ArrayList<>(allContent));
        return true;
    }

    public boolean undo() {
        if (undoStack.empty()) {
            System.out.println("Nothing to undo!!!");
            return false;
        }
        redoStack.push(new ArrayList<>(allContent));
        undoStack.pop();

        allContent = undoStack.isEmpty() ? new ArrayList<>() : new ArrayList<>(undoStack.peek());
        return true;
    }

    public boolean redo() {
        if (redoStack.empty()) {
            System.out.println("Nothing to redo!!!");
            return false;
        }
        allContent = redoStack.pop();
        undoStack.push(new ArrayList<>(allContent));
        return true;
    }
}
