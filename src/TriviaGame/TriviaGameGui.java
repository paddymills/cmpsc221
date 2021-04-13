package TriviaGame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI implimentation for TriviaGama
 * 
 * inherits JFrame
 * is its own ActionListner
 */
public class TriviaGameGui extends JFrame implements ActionListener {
    
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    
    private JButton checkButton, endButton;
    private JPanel qPanel, aPanel, pPanel;
    private JPanel mainPanel, buttonPanel;
    private JTextArea questionText;
    private JTextField answerText;
    private JLabel pointsText;

    private int questionCount;
    private TriviaGame parentGame;


    /**
     * initializes gui
     */
    public TriviaGameGui(TriviaGame game) {
        super("Trivia Game");
        parentGame = game;
        questionCount = 0;

        // initialize window
        setSize( WIDTH, HEIGHT );
        setLocationRelativeTo( null );  // center on screen
        setLayout( new BorderLayout() );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        
        // initialize panels
        buttonPanel = new JPanel();
        mainPanel = new JPanel( new GridLayout(3, 1) );
        qPanel = new JPanel();
        aPanel = new JPanel( new FlowLayout() );
        pPanel = new JPanel();
        
        // add padding to main panel
        // got this idea from:
        //  https://stackoverflow.com/questions/5328405/jpanel-padding-in-java
        mainPanel.setBorder( new EmptyBorder(10, 10, 10, 10) );

        // initialize gui entities
        checkButton = new JButton( "Check Answer" );
        endButton = new JButton( "Quit" );
        questionText = new JTextArea("");
        answerText = new JTextField("");
        pointsText = new JLabel("");

        // style question text area
        questionText.setLineWrap( true );
        questionText.setWrapStyleWord( true );
        questionText.setColumns(50);
        questionText.setEnabled( false );   // user cannot edit
        questionText.setDisabledTextColor( Color.BLACK );
        answerText.setColumns(10);

        // set event listeners
        answerText.addActionListener( this );
        checkButton.addActionListener( this );
        endButton.addActionListener( this );

        // add items to panels
        qPanel.add( questionText );
        aPanel.add( new JLabel("Answer:") );
        aPanel.add( answerText );
        pPanel.add( pointsText );

        // add sub-panels to main panel
        mainPanel.add( qPanel );
        mainPanel.add( aPanel );
        mainPanel.add( pPanel );
        mainPanel.setSize(WIDTH, 200);
        
        // add buttons
        buttonPanel.add( checkButton );
        buttonPanel.add( endButton );
        
        // add panels to frame
        add( mainPanel, BorderLayout.CENTER );
        add( buttonPanel, BorderLayout.SOUTH );

        updatePointsEarned(0);
    }
    
    /**
     * handle JButtons
     * 
     * @param e the event captured
     */
    public void actionPerformed(ActionEvent e) {

        switch ( e.getActionCommand() ) {
            
            case "Quit":            // quit button
                parentGame.handleQuit();
                break;
                
            case "Check Answer":    // check answer button
            default:                // enter/return from answer box
                parentGame.handleQuestionSubmit( answerText.getText() );

        }
    }

    /**
     * displays question in the gui
     * 
     * @param question to display
     */
    public void displayQuestionPrompt(Question question) {

        setTitle( "Question #" + ++questionCount );
        questionText.setText( question.getQuestion() );
        answerText.setText( "" );
        answerText.grabFocus();

        setVisible( true );
    }

    public void updatePointsEarned(int points) {
        pointsText.setText("Points Earned: " + points);
    }
    
}
