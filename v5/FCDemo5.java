import java.awt.EventQueue;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.filechooser.FileFilter;

public class FCDemo5 extends JFrame
{
   JFileChooser fc = new JFileChooser();

   public FCDemo5(String title)
   {
      super(title);
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      fc.setAcceptAllFileFilterUsed(false);
      fc.setFileFilter(new FileFilter()
                       {
                          @Override
                          public boolean accept(File file)
                          {
                             return file.getName().toUpperCase().equals(".TXT");
                          }

                          @Override
                          public String getDescription()
                          {
                             return ".txt files";
                          }
                       });

      JPanel pnl = new JPanel();
      pnl.setLayout(new GridLayout(2, 1));

      JButton btn = new JButton("JFileChooser.showOpenDialog() Demo");
      ActionListener al;
      al = new ActionListener()
           {
              @Override
              public void actionPerformed(ActionEvent ae)
              {
                 switch (fc.showOpenDialog(FCDemo5.this))
                 {
                    case JFileChooser.APPROVE_OPTION:
                       JOptionPane.showMessageDialog(FCDemo5.this, "Selected: " +
                                                     fc.getSelectedFile(),
                                                     "FCDemo",
                                                     JOptionPane.OK_OPTION);
                       break;

                    case JFileChooser.CANCEL_OPTION:
                       JOptionPane.showMessageDialog(FCDemo5.this, "Cancelled",
                                                     "FCDemo",
                                                     JOptionPane.OK_OPTION);
                       break;
                 
                    case JFileChooser.ERROR_OPTION:
                       JOptionPane.showMessageDialog(FCDemo5.this, "Error",
                                                     "FCDemo",
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
                 switch (fc.showSaveDialog(FCDemo5.this))
                 {
                    case JFileChooser.APPROVE_OPTION:
                       JOptionPane.showMessageDialog(FCDemo5.this, "Selected: " +
                                                     fc.getSelectedFile(),
                                                     "FCDemo",
                                                     JOptionPane.OK_OPTION);
                       break;

                    case JFileChooser.CANCEL_OPTION:
                       JOptionPane.showMessageDialog(FCDemo5.this, "Cancelled",
                                                     "FCDemo",
                                                     JOptionPane.OK_OPTION);
                       break;
                 
                    case JFileChooser.ERROR_OPTION:
                       JOptionPane.showMessageDialog(FCDemo5.this, "Error",
                                                     "FCDemo",
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
                         new FCDemo5("FileChooser Demo");
                      }
                   };
      EventQueue.invokeLater(r);
   }
}