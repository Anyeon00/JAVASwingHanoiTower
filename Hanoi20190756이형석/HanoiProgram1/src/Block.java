import java.awt.*;

public class Block {
    static int deltaW = 30;
    int height = 40;
    int width;

    Color color;

    Block(){
        width = 0;
    }
    Block(int n){
        width = n * deltaW;
    }
}
