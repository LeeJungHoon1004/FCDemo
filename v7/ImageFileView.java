import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.filechooser.FileView;

public class ImageFileView extends FileView
{
   private Icon bmpicon, gificon, jpgicon, pngicon;

   // Create ImageFileView to serve as a viewer for file icons.

   ImageFileView()
   {
      // Preload icons for the file view. The getClass().getResource()
      // construct is used so that an application can be packaged into a JAR 
      // file and still obtain these images.

      bmpicon = new ImageIcon(getClass().getResource("images/bmpicon.gif")); 
      gificon = new ImageIcon(getClass().getResource("images/gificon.gif"));
      jpgicon = new ImageIcon(getClass().getResource("images/jpgicon.gif"));
      pngicon = new ImageIcon(getClass().getResource("images/pngicon.gif"));
   }

   // Return a description of the file's type. The look and feel determines
   // what to do with this description (including a null description).

   @Override
   public String getTypeDescription(File f)
   {
      String s = f.getName();
      int i = s.lastIndexOf('.');
      if (i > 0 && i < s.length() - 1)
      {
         String ext = s.substring(i + 1).toLowerCase();
         if (ext.equals("bmp"))
            return "BMP Image";
         else
         if (ext.equals("gif"))
            return "GIF Image";
         else
         if (ext.equals("jpeg") || ext.equals("jpg"))
            return "JPEG Image";
         else
         if (ext.equals("png"))
            return "PNG Image";
      }
      return null;
   }

   // Return the icon that associates with the file's type. If null returns, a
   // default icon is used.

   @Override
   public Icon getIcon(File f)
   {
      String s = f.getName();
      int i = s.lastIndexOf('.');

      if (i > 0 && i < s.length() - 1)
      {
         String ext = s.substring(i + 1).toLowerCase();
         if (ext.equals("bmp"))
            return bmpicon;
         else
         if (ext.equals("gif"))
            return gificon;
         else
         if (ext.equals("jpeg") || ext.equals("jpg"))
            return jpgicon;
         else
         if (ext.equals("png"))
            return pngicon;
      }
      return null;
   }

   // Return the file's name minus its extension for files with the bmp, gif,
   // jpeg, jpg, or png extensions.
   
   @Override
   public String getName(File f)
   {         
      String s = f.getName();
      int i = s.lastIndexOf('.');

      if (i > 0 && i < s.length() - 1)
      {
         String ext = s.substring(i + 1).toLowerCase();
         if (ext.equals("bmp") || ext.equals("gif") ||
            ext.equals("jpeg") || ext.equals("jpg") || ext.equals("png"))
            return s.substring(0, i);
      }
      return null;
   }

   // Return an individual file's description.

   @Override
   public String getDescription(File f)
   {
      // Let the look and feel figure out the description.

      return null;
   }

   // Determine if a directory is traversable.

   @Override
   public Boolean isTraversable(File f)
   {
      // Let the look and feel determine if the directory is traversable.

      return null;
   }
}