import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class HanoiPanel extends JPanel implements ActionListener {
    static int startX = 30;
    static int startY = 30;

    static int towerHeight = 450;
    static int towerWidth = 300;

    static int towerWidthGap = 20;

    static int t1X = startX;
    static int t2X = startX + towerWidth + towerWidthGap;
    static int t3X = startX + 2 * (towerWidth + towerWidthGap);

    static int nextButton = 0;
    static int prevButton = 1;


    Tower t1;
    Tower t2;
    Tower t3;

    String a, b, c;
    String lastCommand;

    StringBox commandBox;
    int commandBoxSize;

    int lastButton;

    int re;

    HanoiPanel(){
        a = "a";
        b = "b";
        c = "c";
        lastCommand = null;
        lastButton = -1;
        re = -1;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTower(g, t1X, startY);
        drawTower(g, t2X, startY);
        drawTower(g, t3X, startY);
    }
    private void drawTower(Graphics g, int x, int y) {
        int siteX = x + (towerWidth / 2) - 5;
        int siteY = y;
        g.fillRect(siteX, siteY, 10, towerHeight);
        g.drawRect(siteX, siteY, 10, towerHeight);

        g.fillRect(x, y + towerHeight, towerWidth, 10);
        g.drawRect(x, y + towerHeight, towerWidth, 10);
    }
    public void startAlgorighm(int n){
        resetAll();

        hanoiTowerN(n, a, b, c);
        commandBox = new StringBox(commandBoxSize);

        hanoiTower(n, a, b, c);
        commandBox.resetPointer();

        setTowers(getGraphics(), n);
    }
    private void setTowers(Graphics g, int n) {
        t1.initializeTower(getGraphics(), n);
        t2.drawBlocks(getGraphics());
        t3.drawBlocks(getGraphics());
    }
    private void resetAll(){
        t1 = new Tower(t1X);
        t2 = new Tower(t2X);
        t3 = new Tower(t3X);

        commandBoxSize = 0;

        paint(getGraphics());
    }
    private void hanoiTowerN(int n, String from, String tmp, String to) {
        if (n == 1) {
            commandBoxSize++;
            return;
        }
        hanoiTowerN(n-1, from, to, tmp);
        hanoiTowerN(1, from, tmp, to);
        hanoiTowerN(n-1, tmp, from, to);
    }
    private void hanoiTower(int n, String from, String tmp, String to) {
        if (n == 1) {
            commandBox.moveNext();
            commandBox.putString(from + " -> " + to);
            return;
        }
        hanoiTower(n-1, from, to, tmp);
        hanoiTower(1, from, tmp, to);
        hanoiTower(n-1, tmp, from, to);
    }
    private void drawTowers(){
        Graphics g = getGraphics();

        paint(getGraphics());
        t1.drawBlocks(g);
        t2.drawBlocks(g);
        t3.drawBlocks(g);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "다음 단계") {
            Tower from = null;
            Tower to = null;

            if (lastButton == nextButton || lastButton == -1) {
                commandBox.moveNext();
            }

            String moveCommand = commandBox.getString();
            /*if (moveCommand.equals(lastCommand)) {
                return;
            }*/
            lastCommand = moveCommand;

            StringTokenizer st = new StringTokenizer(moveCommand);
            String s1 = st.nextToken();
            st.nextToken();
            String s2 = st.nextToken();
            if (s1.equals(a)) {
                from = t1;
            } else if (s1.equals(b)) {
                from = t2;
            } else if (s1.equals(c)) {
                from = t3;
            }
            if (s2.equals(a)) {
                to = t1;
            } else if (s2.equals(b)) {
                to = t2;
            } else if (s2.equals(c)) {
                to = t3;
            }

            Block tmp = from.blocks.pop();
            to.blocks.push(tmp);

            drawTowers();

            lastButton = nextButton;
        } else if (command == "이전 단계") {
            Tower from = null;
            Tower to = null;
            if (lastButton == prevButton) {
                commandBox.movePrev();
            }

            String moveCommand = commandBox.getString();
            /*if (moveCommand.equals(lastCommand)) {
                return;
            }*/
            lastCommand = moveCommand;

            StringTokenizer st = new StringTokenizer(moveCommand);
            String s1 = st.nextToken();
            st.nextToken();
            String s2 = st.nextToken();
            if (s2.equals(a)) {
                from = t1;
            } else if (s2.equals(b)) {
                from = t2;
            } else if (s2.equals(c)) {
                from = t3;
            }
            if (s1.equals(a)) {
                to = t1;
            } else if (s1.equals(b)) {
                to = t2;
            } else if (s1.equals(c)) {
                to = t3;
            }

            Block tmp = from.blocks.pop();
            to.blocks.push(tmp);

            drawTowers();

            lastButton = prevButton;
        }
    }
}
