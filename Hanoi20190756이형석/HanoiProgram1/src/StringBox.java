import javax.swing.*;

public class StringBox {
    String[] stringArray;
    int pointer;
    StringBox(int n){
        stringArray = new String[n];
        pointer = -1;
    }

    public String getString(){
        return stringArray[pointer];
    }

    public void putString(String s){
        stringArray[pointer] = s;
    }

    public void moveNext(){
        if (pointer >= stringArray.length - 1) {

            JOptionPane.showMessageDialog(null, "It's the last state of all", "Exception", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pointer++;
    }

    public void movePrev(){
        if (pointer <= 0) {
            JOptionPane.showMessageDialog(null, "It's the first state of all", "Exception", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pointer--;
    }
    public void resetPointer(){
        pointer = -1;
    }
}
