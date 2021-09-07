package notepad;

public class Driver {
    public static void main(String[] args) {
        Notepad notepad = new Notepad("At the starting of the week\nAt summit talks you'll hear them speak\nIt's only Monday");
        notepad.display();
        System.out.println("**************************** 0 ***********************************");
        System.out.println("** Displaying content: only first two lines **");
        notepad.display(1, 2);
        System.out.println("**************************** 1 ***********************************");
        System.out.println("** Inserting yeah to the first line **");
        notepad.insert(1, "Yeah");
        notepad.display();
        System.out.println("**************************** 2 ***********************************");
        System.out.println("** Undoing last move **");
        notepad.undo();
        notepad.display();
        System.out.println("***************************** 3 *********************************");
        System.out.println("** Redoing last move **");
        notepad.redo();
        notepad.display();
        System.out.println("****************************** 4 *******************************");
        System.out.println("** Redoing last move **");
        notepad.redo();
        System.out.println("******************************** 5 ****************************");
        System.out.println("** Deleting first line **");
        notepad.delete(1);
        notepad.display();
        System.out.println("******************************* 6 ****************************");
        System.out.println("** Undoing last move **");
        notepad.undo();
        notepad.display();
        System.out.println("******************************* 7 ***************************");
        System.out.println("** Undoing last move **");
        notepad.undo();
        notepad.display();
        System.out.println("**************************** 8 ***********************************");
        System.out.println("** After deletion of lines 1 to 2 **");
        notepad.delete(1, 2);
        notepad.display();
        System.out.println("***************************** 9 ****************************");
        System.out.println("** Undoing last move **");
        notepad.undo();
        notepad.display();
        System.out.println("***************************** 10 ***************************");
        System.out.println("** Copying lines 1 to 2 and pasting them on 3rd line **");
        notepad.copy(1, 2);
        notepad.paste(3);
        notepad.display();
        System.out.println("***************************** 11 **************************");
        System.out.println("** Undoing last move **");
        notepad.undo();
        notepad.display();
        System.out.println("****************************** 12 ************************");
        System.out.println("** Redoing last move **");
        notepad.redo();
        notepad.display();
    }
}
