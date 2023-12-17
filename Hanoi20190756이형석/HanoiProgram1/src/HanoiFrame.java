import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HanoiFrame extends JFrame  {
    JPanel menuPanel;
    HanoiPanel mainPanel;
    JLabel floorNLabel;
    JTextField floorNTF;
    JButton startButton;
    JButton prevButton;
    JButton nextButton;
    int floorN;
    HanoiFrame(){
        setTitle("HanoiFrame");
        setSize(1000, 600);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container contentPane = getContentPane();

        mainPanel = new HanoiPanel();
        contentPane.add(mainPanel, "Center");

        menuPanel = new JPanel();
        floorNLabel = new JLabel("문제 범위 : ");
        floorNTF = new JTextField("", 5);
        floorNTF.setHorizontalAlignment(JTextField.RIGHT);

        startButton = new JButton("시작");
        startButton.addActionListener((e)->{
            try {
                floorN = Integer.parseInt(floorNTF.getText());
                if (floorN > 10 || floorN < 1) {
                    throw new Exception();
                }
                mainPanel.startAlgorighm(floorN);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Please write integer value between 1 to 10.", "Exception", JOptionPane.ERROR_MESSAGE);
            }
        });
        prevButton = new JButton("이전 단계");
        prevButton.addActionListener(mainPanel);
        nextButton = new JButton("다음 단계");
        nextButton.addActionListener(mainPanel);

        menuPanel.add(floorNLabel);
        menuPanel.add(floorNTF);
        menuPanel.add(startButton);
        menuPanel.add(prevButton);
        menuPanel.add(nextButton);

        contentPane.add(menuPanel, "South");
    }


}
