import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuessTheNumberGame extends JFrame {
    
    private int answer, guess;
    private int ceiling, floor, last;
    private JTextField guessInput;
    private JTextField message;
    private JLabel first, second;
    private JButton reset;
    
    
    public GuessTheNumberGame(){
        
        super("Guess the Number");
        first = new JLabel("I have a number between 1 and 1000. Can you guess my number?");
        second = new JLabel("Please enter your first guess.");
        
        Frame[] frames = Frame.getFrames();
        for(Frame f: frames){
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
            }

        });
    }
        
        ceiling = 0;
        floor = 1000;
        last = 0;
        guessInput = new JTextField(5);
        guessInput.addActionListener(new Game());
        message = new JTextField("Enter a guess.");
        message.setEditable(false);
        
        reset = new JButton("New Game");
        reset.addActionListener(
                new ActionListener(){
                    public void ActionPerformed(ActionEvent e){
                        message.setText("Enter a guess.");
                        guessInput.setText("");
                        guessInput.setEditable(true);
                        getContentPane().setBackground(Color.LIGHT_GRAY);
                        theGame();
                        
                    }

            @Override
            public void actionPerformed(ActionEvent ae) {
                message.setText("Enter a guess.");
                guessInput.setText("");
                guessInput.setEditable(true);
                getContentPane().setBackground(Color.LIGHT_GRAY);
                theGame();                
            }
                });
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(first);
        c.add(second);
        c.add(guessInput);
        c.add(reset);
        c.add(message);
        setSize(600,400);
        show();
        theGame();
    }
    
    public void theGame(){
        answer = (int)(Math.random()*1000+1);
    }
    
    
    
    public static void main(String[] args) {
        
        GuessTheNumberGame app = new GuessTheNumberGame();
        
    }
    
    class Game implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            
            try{
                Integer.parseInt(guessInput.getText());
    } 
            catch (NumberFormatException ex){
                message.setText("Invalid Input");
                getContentPane().setBackground(Color.BLACK);
    }

            guess = Integer.parseInt(guessInput.getText());
            if ((guess < 0) || (guess > 1000)){
                message.setText("Invalid Guess");
                getContentPane().setBackground(Color.orange);
            }
            
            else if (guess>answer){
                message.setText("Too High");
                if (Math.abs(answer-guess) < Math.abs(answer-last)){
                    last = guess;
                    getContentPane().setBackground(Color.red);
                    
                }
                
                else if (Math.abs(answer-guess) >= Math.abs(answer-last)){
                    last = guess;
                    getContentPane().setBackground(Color.blue);
                }   
            }
            else if (guess<answer){
                message.setText("Too Low");
                if (Math.abs(answer-guess) < Math.abs(answer-last)){
                    last = guess;
                    getContentPane().setBackground(Color.red);
                }
                
                else if (Math.abs(answer-guess) >= Math.abs(answer-last)){
                    getContentPane().setBackground(Color.blue);
                    last = guess;
                }
            }
            else{
                message.setText("Correct");
                getContentPane().setBackground(Color.white);
                guessInput.setEditable(false);
                floor = 1000;
                ceiling = 0;
            }
        }
    }
    
}