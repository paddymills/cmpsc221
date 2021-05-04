// BorderLayoutProblem.java

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class BorderLayoutProblem extends JFrame {

   public static final int WIDTH = 500;
   public static final int HEIGHT = 400;

   public BorderLayoutProblem( ) {

      super("BorderLayout Problem");
      setSize(WIDTH, HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setLayout(new BorderLayout( ));

      JLabel label1 = new JLabel("Label one");
      add(label1, BorderLayout.SOUTH);

      JLabel label2 = new JLabel("Label two");
      add(label2, BorderLayout.CENTER);

      JLabel label3 = new JLabel("Label three");
      add(label3, BorderLayout.NORTH);
   }

   public static void main(String[] args) {

      BorderLayoutProblem gui = new BorderLayoutProblem( );
      gui.setVisible(true);
   }
}