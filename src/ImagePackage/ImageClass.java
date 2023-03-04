package ImagePackage;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import DebuggingPackage.DebuggingClass1;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import MainPackage.MainClass;
//import java.util.logging.Level;
//import java.util.logging.Logger;
/**
 *
 * @author Ahmed Hassan
 */
public class ImageClass {
    static JFrame imageFrame;
    static JPanel imagePanel;
    static Image loadedImage = null;
    static JLabel toolsBoxLabel, filtersBoxLabel,widthlabel,heightlabel;
    static JComboBox<?> toolsBox, filtersBox;
    static JButton imageCropBtn, filtersApplyBtn, saveBtn, backBtn, exitBtn, undoBtn, redoBtn, resize,mirror,flip,rotate;
    static ActionListenerClass listener;
    static Stack<Image> undoStack, redoStack;
    static boolean editFlag = false, imageCropped = false;
//    static MouseListenerClass mouseListener;
    static ToolsClass drawTool;
    static Dimension screenSize;
    static int screenHeight, screenWidth;
    static ChangeListener  c;
    static JSlider slider;
    static JTextField textfield1,textfield2;
      
    public ImageClass(String filepath)
    {
        SwingUtilities.invokeLater(() -> {
            try {
                undoStack = new Stack<Image>();
                redoStack = new Stack<Image>();
                imageFrame = new JFrame("Image Editor");
                imageFrame.pack();
                imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Color color=new Color(153, 153, 255);
                imageFrame.getContentPane().setBackground(color);
                screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                screenHeight = (int) screenSize.getHeight();
                screenWidth = (int) screenSize.getWidth();
                int w = 5*screenWidth/100;
                int h = 5*screenHeight/100;
                imageFrame.setLocation(w, h);
                imageFrame.setLayout(null);
                imageFrame.setResizable(false);
                imageFrame.setVisible(true);
                imageFrame.setSize(1000, 700);
                
                imagePanel = new JPanel();
                imagePanel.setBounds(0, 0, 500, 500);
                imageFrame.add(imagePanel);
                
                loadedImage = ImageIO.read(new File(filepath));
                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                drawTool = new ToolsClass(loadedImage);
                imagePanel.add(drawTool);
                
                
                toolsBoxLabel = new JLabel("CROP: ");
                toolsBoxLabel.setBounds(550, 10, 150, 30);
                imageFrame.add(toolsBoxLabel);
                
                String[] tools = {"None", "Rectangle", "Circle"};
                toolsBox = new JComboBox<>(tools);
                toolsBox.setSelectedIndex(0);
                toolsBox.setBounds(590, 10, 150, 30);
                imageFrame.add(toolsBox);
                listener = new ActionListenerClass();
                toolsBox.addActionListener(listener);
                
                imageCropBtn = new JButton("Crop Image");
                imageCropBtn.setBounds(750, 10, 110, 30);
                imageFrame.add(imageCropBtn);
                imageCropBtn.addActionListener(listener);
                
                filtersBoxLabel = new JLabel("FILTERS: ");
                filtersBoxLabel.setBounds(550, 100, 150, 30);
                imageFrame.add(filtersBoxLabel);
                
                String[] filters = {"None", "Classic", "RedMoon", "Blur", "Invert","Sephia","Black_N_white","Grayscale"};
                filtersBox = new JComboBox<>(filters);
                filtersBox.setSelectedIndex(0);
                filtersBox.setBounds(590, 100, 150, 30);
                imageFrame.add(filtersBox);
                
                filtersApplyBtn = new JButton("Apply Filter");
                filtersApplyBtn.setBounds(750, 100, 110, 30);
                imageFrame.add(filtersApplyBtn);
                filtersApplyBtn.addActionListener(listener);
                
                mirror = new JButton("Mirror");
                mirror.setBounds(550, 250, 110, 30);
                imageFrame.add(mirror);
                mirror.addActionListener(listener);
                
                flip = new JButton("Flip");
                flip.setBounds(680, 250, 110, 30);
                imageFrame.add(flip);
                flip.addActionListener(listener);
                
                rotate = new JButton("Rotate");
                rotate.setBounds(820, 250, 110, 30);
                imageFrame.add(rotate);
                rotate.addActionListener(listener);
                
                
                
                saveBtn = new JButton("Save");
                saveBtn.setBounds(620, 590, 90, 30);
                imageFrame.add(saveBtn);
                saveBtn.addActionListener(listener);
                drawTool.setImage(loadedImage);
                undoBtn = new JButton("Undo"); 
                undoBtn.setBounds(70, 590, 90, 30);
                imageFrame.add(undoBtn);
                undoBtn.addActionListener(listener);
                
                redoBtn = new JButton("Redo");
                redoBtn.setBounds(200, 590, 90, 30);
                
                
                imageFrame.add(redoBtn);
                redoBtn.addActionListener(listener);
                
                backBtn = new JButton("Back");
                backBtn.setBounds(490, 590, 90, 30);
                imageFrame.add(backBtn);
                backBtn.addActionListener(listener);
                slider = new JSlider(0,20,1);
                slider.setBounds(600,350,150,20);

        		slider.setMinorTickSpacing(1);
        		slider.setMajorTickSpacing(2);
        		

        		slider.addChangeListener(new ChangeListener() {
        			
        			Image b = drawTool.getImage();	
@Override
public void stateChanged(ChangeEvent ce) {
	// TODO Auto-generated method stub
	
	
	FiltersClass filterTool = new FiltersClass();
	loadedImage = filterTool.brightenImage(toBufferedImage(b),(float)slider.getValue()/2);
//  loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
  drawTool.setImage(loadedImage);
	System.out.println(((JSlider)ce.getSource()).getValue());
}
        		});
        		//slider.setPaintTicks(true);
        		//slider.setPaintLabels(true);
        		
        		
        		JLabel brightness = new JLabel("BRIGHTNESS");
        		brightness.setBounds(780,335,150,50);
        		imageFrame.add(brightness);
        		
        		imageFrame.add(slider);
        		//imageFrame.getContentPane().setBackground(Color.GREEN);
        		resize = new JButton("Resize");
        		resize.setBounds(850, 450, 90, 30);
        		imageFrame.add(resize);
        		resize.addActionListener(listener);
        		widthlabel = new JLabel("WIDTH: ");
                widthlabel.setBounds(550, 450, 90, 30);
                imageFrame.add(widthlabel);
        		 textfield1 = new JTextField();
        		textfield1.setBounds(590,450,90,30);
        		imageFrame.add(textfield1); 
        		heightlabel = new JLabel("HEIGHT:");
                heightlabel.setBounds(690,450,90, 30);
                imageFrame.add(heightlabel);
        		 textfield2 = new JTextField();
        		textfield2.setBounds(730,450,90,30);
        		imageFrame.add(textfield2);
        		
                
                
                exitBtn = new JButton("Exit");
                exitBtn.setBounds(750, 590, 90, 30);
                imageFrame.add(exitBtn);
                exitBtn.addActionListener(listener);
                
            }
            catch(HeadlessException | IOException ex){
                DebuggingClass1 err =  new DebuggingClass1();
                err.logException(ex.toString()); //Store exception in error ArrayList
            }
        });
        
    }
    public static BufferedImage toBufferedImage(Image img)
    {
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    

    static class ActionListenerClass implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == toolsBox)
            {
                try
                {
                    if(toolsBox.getSelectedIndex() != 0)
                        drawTool.setToolType(toolsBox.getSelectedIndex());
                }
                catch(Exception ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            if (actionEvent.getSource() == imageCropBtn)
            {
                try
                {
                    if(toolsBox.getSelectedIndex() != 0)
                    {
                            undoStack.push(drawTool.getImage());
                            drawTool.setImage(drawTool.cropImage(toBufferedImage(drawTool.getImage())));
                            toolsBox.setSelectedIndex(0);
                            editFlag = true;
                    }
                    else
                        JOptionPane.showMessageDialog(imageFrame, "Please Choose a tool to cut with");
                }
                catch(HeadlessException | RasterFormatException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == filtersApplyBtn)
            {
                FiltersClass filterTool = new FiltersClass();
                try
                {
                    
                        switch (filtersBox.getSelectedIndex()) {
                            case 1:
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.Classic(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break;
                            case 2:
                            	
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.Redmoon(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break;
                            case 3:
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.blurImage(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break;
                            case 4:
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.invertImage(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break;
                            case 5:
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.Sephia(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break; 
                            case 6:
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.blackNwhite(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break;  
                            case 7:
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.grayscaleImage(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break; 
                            default:
                                JOptionPane.showMessageDialog(imageFrame, "Please Choose a filter to apply");
                                break;
                        }
                    
                }
                catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == resize)
            {
                FiltersClass filterTool = new FiltersClass();
                try
                {
                    
                        int w=Integer.parseInt(textfield1.getText());
                        		int h = Integer.parseInt(textfield2.getText());
                            
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.resizeImage(toBufferedImage(drawTool.getImage()),w,h);
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                
                                editFlag = true;
                }catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == mirror)
            {
                FiltersClass filterTool = new FiltersClass();
                try
                {
                    
                      
                            
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.MirrorImage(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                
                                editFlag = true;
                }catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == flip)
            {
                FiltersClass filterTool = new FiltersClass();
                try
                {
                    
                      
                            
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.flipImage(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                
                                editFlag = true;
                }catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == rotate)
            {
                FiltersClass filterTool = new FiltersClass();
                try
                {
                    
                      
                            
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.rotateImage(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                
                                editFlag = true;
                }catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
                
            else if (actionEvent.getSource() == saveBtn)
            {
                try
                {
                    if(editFlag)
                    {
                        String filePath = null;
                        JFileChooser fileChooser = new JFileChooser(filePath);
                        imageFrame.setVisible(false);
                        int choosenBtn = fileChooser.showSaveDialog(fileChooser);
                        if(choosenBtn == JFileChooser.APPROVE_OPTION)
                        {
                            File tempFile = new File(fileChooser.getSelectedFile().toString()+".png");
                            ImageIO.write(toBufferedImage(drawTool.getImage()), "png", tempFile);
                            imageFrame.setVisible(true);
                            
                        } else {
                            imageFrame.setVisible(true);
                        }
                    }
                    else JOptionPane.showMessageDialog(imageFrame, "You can NOT do this right now!");
                }
                catch(HeadlessException | IOException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == undoBtn)
            {
                try
                {
                    if(!undoStack.empty() && editFlag == true)
                    {
                        redoStack.push(drawTool.getImage());
                        loadedImage = toBufferedImage((Image)undoStack.pop());
//                        loadedImage = loadedImage.getScaledInstance(700, 700, Image.SCALE_DEFAULT);
                        drawTool.setImage(loadedImage);
                        drawTool.repaint();
                    }
                    else
                        JOptionPane.showMessageDialog(imageFrame, "You Can NOT do this right now!");
                }
                catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == redoBtn)
            {
                try
                {
                    if(!redoStack.empty() && editFlag == true)
                    {
                        undoStack.push(drawTool.getImage());
                        loadedImage = toBufferedImage((Image)redoStack.pop());
//                        loadedImage = loadedImage.getScaledInstance(700, 700, Image.SCALE_DEFAULT);
                        drawTool.setImage(loadedImage);
                        drawTool.repaint();
                    }
                    else
                        JOptionPane.showMessageDialog(imageFrame, "You Can NOT do this right now!");
                }
                catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == backBtn)
            {
                try
                {
                    if (JOptionPane.showConfirmDialog( imageFrame,"Are you sure?","Query", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        imageFrame.setVisible(false);
                        MainClass.main(null); // To back to main frame
                    }
                }
                catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == exitBtn)
            {
                try
                {
                    if (JOptionPane.showConfirmDialog( imageFrame,"Are you sure?","Query", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        System.exit(0);
                    }
                }
                catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
        }
    }
}
