import java.awt.EventQueue;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FCDemo4 extends JFrame
{
   JFileChooser fc = new JFileChooser();

   public FCDemo4(String title)
   {
      super(title);
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      JPanel pnl = new JPanel();
      pnl.setLayout(new GridLayout(2, 1));

      JButton btn = new JButton("JFileChooser.showOpenDialog() Demo");
      ActionListener al;
      al = new ActionListener()
           {
              @Override
              public void actionPerformed(ActionEvent ae)
              {
                 fc.setDialogType(JFileChooser.OPEN_DIALOG);
                 fc.setDialogTitle("Open");
                 switch (fc.showDialog(FCDemo4.this, "OPEN..."))
                 {
                    case JFileChooser.APPROVE_OPTION:
                       JOptionPane.showMessageDialog(FCDemo4.this, "Selected: " +
                                                     fc.getSelectedFile(),
                                                     "FCDemo4",
                                                     JOptionPane.OK_OPTION);
                       break;

                    case JFileChooser.CANCEL_OPTION:
                       JOptionPane.showMessageDialog(FCDemo4.this, "Cancelled",
                                                     "FCDemo4",
                                                     JOptionPane.OK_OPTION);
                       break;
                 
                    case JFileChooser.ERROR_OPTION:
                       JOptionPane.showMessageDialog(FCDemo4.this, "Error",
                                                     "FCDemo4",
                                                     JOptionPane.OK_OPTION);
                 }
              }
           };
      btn.addActionListener(al);
      pnl.add(btn);

      btn = new JButton("JFileChooser.showSaveDialog() Demo");
      al = new ActionListener()
           {
              @Override
              public void actionPerformed(ActionEvent ae)
              {
                 fc.setDialogType(JFileChooser.SAVE_DIALOG);
                 fc.setDialogTitle("Save");
                 switch (fc.showDialog(FCDemo4.this, "SAVE..."))
                 {
                    case JFileChooser.APPROVE_OPTION:
                       JOptionPane.showMessageDialog(FCDemo4.this, "Selected: " +
                                                     fc.getSelectedFile(),
                                                     "FCDemo4",
                                                     JOptionPane.OK_OPTION);
                       break;

                    case JFileChooser.CANCEL_OPTION:
                       JOptionPane.showMessageDialog(FCDemo4.this, "Cancelled",
                                                     "FCDemo4",
                                                     JOptionPane.OK_OPTION);
                       break;
                 
                    case JFileChooser.ERROR_OPTION:
                       JOptionPane.showMessageDialog(FCDemo4.this, "Error",
                                                     "FCDemo4",
                                                     JOptionPane.OK_OPTION);
                 }
              }
           };
      btn.addActionListener(al);
      pnl.add(btn);

      setContentPane(pnl);

      pack();
      setVisible(true);
   }

   public static void main(String[] args)
   {
      Runnable r = new Runnable()
                   {
                      @Override
                      public void run()
                      {
                         new FCDemo4("FileChooser Demo");
                      }
                   };
      EventQueue.invokeLater(r);
   }
}