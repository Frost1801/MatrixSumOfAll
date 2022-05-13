import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI implements ActionListener {
    JFrame mainFrame;
    ImageIcon matrixIcon;
    JPanel mainPanel;
    JLabel title;
    JLabel description;

    JButton submit;
    JRadioButton option3;
    JRadioButton option4;

    JPanel info;
    JPanel interact;
    JPanel titleAndButtons;
    JPanel operations;

    JLabel possibleCombinations;
    JLabel status;
    JLabel testedCombinations;

    JPanel matrixHolder;




    ButtonGroup options;
    GUI ()  {
        mainFrame = new JFrame();
        mainFrame.setSize(500,500);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Matrix Solver");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        matrixIcon = new ImageIcon("/home/sergio/JavaProjects/ProblemaSessione/Images/icon.png");
        mainFrame.setIconImage(matrixIcon.getImage());



        addText();
        addButtons();

        mainFrame.setVisible(true);

    }

    private void addText (){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(105, 165, 227));

        titleAndButtons = new JPanel();
        mainPanel.add(titleAndButtons);
        titleAndButtons.setLayout(new BoxLayout(titleAndButtons,BoxLayout.Y_AXIS));



        title = new JLabel("<html>Matrix solver<html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Pagul",Font.BOLD,40));
        title.setBackground(new Color(89, 130, 255));

        description = new JLabel("<html>This program finds a matrix where the sum of rows,<br/>columns and diagonals is a determined number<html>");
        description.setHorizontalAlignment(JLabel.CENTER);
        description.setFont(new Font("Pagul",Font.BOLD,15));
        description.setBackground(new Color(89, 130, 255));

        info = new JPanel();

        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));




        status = new JLabel("<html>Selected:<html>");
        status.setHorizontalAlignment(JLabel.CENTER);
        status.setFont(new Font("Pagul",Font.BOLD,30));
        Border statusBorder = BorderFactory.createLineBorder(new Color(26, 43, 98),10);
        status.setBorder(statusBorder);
        info.add(status);

        info.add (description);

        titleAndButtons.add(title, BorderLayout.CENTER);

        mainPanel.add(titleAndButtons, BorderLayout.NORTH);
        mainPanel.add(info,BorderLayout.SOUTH);
        mainFrame.add(mainPanel);
    }
    private void addButtons (){
        option3 = new JRadioButton("3x3");
        option4 = new JRadioButton("4x4");
        option3.addActionListener(this);
        option3.setFont(new Font("Pagul",Font.BOLD,20));
        option4.addActionListener(this);
        option4.setFont(new Font("Pagul",Font.BOLD,20));
        options = new ButtonGroup();
        options.add(option3);
        options.add(option4);

        submit = new JButton("submit");
        submit.addActionListener(this);
        submit.setFont(new Font("Pagul",Font.BOLD,20));
        submit.setFocusable(false);

        interact = new JPanel();
        interact.setLayout(new FlowLayout());
        interact.add(option3,FlowLayout.LEFT);
        interact.add(option4);
        interact.add(submit, FlowLayout.CENTER);
        interact.setBackground(new Color(26, 43, 98));

        titleAndButtons.add(interact);
    }

    private void displayOperations() {
        operations = new JPanel();
        operations.setLayout(new BorderLayout());
        operations.setBackground(new Color(105, 165, 227));


        possibleCombinations = new JLabel("<html>Possible combinations:<html>");
        possibleCombinations.setHorizontalAlignment(JLabel.CENTER);
        possibleCombinations.setFont(new Font("Pagul",Font.BOLD,15));

        testedCombinations = new JLabel("<html>Tested combinations:<html>");
        testedCombinations.setFont(new Font("Pagul",Font.BOLD,30));
        testedCombinations.setHorizontalAlignment(JLabel.CENTER);

        operations.add(testedCombinations,BorderLayout.NORTH);
        operations.add(possibleCombinations, BorderLayout.SOUTH);

        mainPanel.add(operations, BorderLayout.CENTER);
    }

    private void displaySolution(int []matrix, int n){
        matrixHolder = new JPanel(new GridLayout(n,n));
        for(int item: matrix){
            JLabel toAdd = new JLabel(String.valueOf(item));
            toAdd.setFont(new Font("Pagul",Font.BOLD,20));
            toAdd.setHorizontalAlignment(JLabel.CENTER);
            Border border = BorderFactory.createMatteBorder(10,20,10,15,new Color(105, 165, 227));
            toAdd.setBorder(border);
            matrixHolder.add(toAdd);
        }

        operations.add(matrixHolder, BorderLayout.CENTER);

    }
    
    private void displayResults(int optionN){
        MatrixTester tester = new MatrixTester();
        status.setText("<html>Selected:" + optionN + "<html>");
        displayOperations();
        int[] matrix = tester.startTest(optionN,this);
        displaySolution(matrix,optionN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit){
            if (option3.isSelected()) {
                displayResults(3);
            }
            else if (option4.isSelected()) {
                displayResults(4);
            }
        }

    }

    public void setCombinations(double combinations) {
        testedCombinations.setText("<html>Tested combinations: <html>" + combinations);
        operations.repaint();
        operations.revalidate();
    }
}
