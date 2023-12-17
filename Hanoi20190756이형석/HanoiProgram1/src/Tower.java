import java.awt.*;
import java.util.Iterator;
import java.util.Stack;

public class Tower {
    Stack<Block> blocks;
    int startX;
    Tower(int x) {
        startX = x;
        blocks  = new Stack<>();
    }
    public void initializeTower(Graphics g, int n) {
        blocks.clear();
        for (int i = n; i > 0; i--) {
            Block b = new Block(i);
            blocks.add(b);  // 왜 push가아니지
        }
        drawBlocks(g);
    }
    public void drawBlocks(Graphics g) {        // stack밑에있는것부터 하나씩 그리기
        Iterator<Block> itr = blocks.iterator();
        int y = HanoiPanel.startY + HanoiPanel.towerHeight - 40;
        while (itr.hasNext()) {
            Block tmp = itr.next();
            int width = tmp.width;
            int height = tmp.height;
            int x = (startX+(HanoiPanel.towerWidth/2)) - (width/2);

            g.setColor(Color.white);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);

            y -= height;
        }
    }
}
