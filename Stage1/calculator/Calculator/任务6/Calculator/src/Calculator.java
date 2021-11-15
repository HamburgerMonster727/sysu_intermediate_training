import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

final public class Calculator {
    //两个输入数和一个输出数
    static private double num1 = 12;
    static private double num2 = 2;
    static private double result = 0;
    
    static private JTextField Num1Label = new JTextField(Double.toString(num1));
    static private JLabel OperatorLabel = new JLabel("");
    static private JTextField Num2Label = new JTextField(Double.toString(num2));
    static private JLabel EqualLabel = new JLabel("=");
    static private JLabel ResultLabel = new JLabel("");

    //获取操作符
    static class Operator implements ActionListener {
        public void actionPerformed(ActionEvent action) {
            String operator = ((JButton)action.getSource()).getText();
            ResultLabel.setText("");
            OperatorLabel.setText(operator);
        }
    }

    //计算结果
    static class Equal implements ActionListener {
        public void actionPerformed(ActionEvent action) {
            num1 = Double.parseDouble(Num1Label.getText());
            num2 = Double.parseDouble(Num2Label.getText());
            String operator = OperatorLabel.getText();

            if(operator.equals("+")){
                result = num1 + num2;
            }
            else if(operator.equals("-")){
                result = num1 - num2;
            }
            else if(operator.equals("*")){
                result = num1 * num2;
            }
            else if(operator.equals("/")){
                result = num1 / num2;
            } 
            ResultLabel.setText(Double.toString(result));
        }
    }

    //GUI设计
    static public void main(String[] args) {
        String[] operators = {"+", "-", "*", "/"};
        JFrame frame = new JFrame("Calculator");

        frame.setVisible(true);
        frame.setSize(400,200);
        frame.setLayout(new GridLayout(0,5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(Num1Label);
        frame.add(OperatorLabel);
        frame.add(Num2Label);
        frame.add(EqualLabel);
        frame.add(ResultLabel);

        Num1Label.setHorizontalAlignment(SwingConstants.CENTER);
        Num2Label.setHorizontalAlignment(SwingConstants.CENTER);
        OperatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        EqualLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        Num1Label.setBorder(BorderFactory.createLineBorder(Color.gray));
        Num2Label.setBorder(BorderFactory.createLineBorder(Color.gray));
        OperatorLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
        EqualLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
        ResultLabel.setBorder(BorderFactory.createLineBorder(Color.gray));

        for(String opt: operators){
            JButton button = new JButton(opt);
            button.addActionListener(new Operator());
            frame.add(button);
        }
        JButton equal = new JButton("OK");
        equal.addActionListener(new Equal());
        frame.add(equal);
    }
}
