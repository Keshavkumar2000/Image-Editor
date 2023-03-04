/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImagePackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;

/**
 *
 * 
 */
public class FiltersClass {
    
    public BufferedImage blurImage(BufferedImage BI)
    {
        Kernel k = new Kernel(3, 3, new float[]{1f/(3*3),1f/(3*3),1f/(3*3),
                                                1f/(3*3),1f/(3*3),1f/(3*3),
                                                1f/(3*3),1f/(3*3),1f/(3*3)});
        ConvolveOp op =new ConvolveOp(k);
         return op.filter(BI, null);
    }

    /**I     *
     * @param BI
     * @return
     */
    
    public Image Classic(BufferedImage BI)
    {     
    	
    	for (int x = 0; x < BI.getWidth(); x++) {
            for (int y = 0; y < BI.getHeight(); y++) {
                int rgba = BI.getRGB(x, y);
                
                Color col = new Color(rgba, true);
                
                col = new Color(col.getRed(),(col.getBlue()),(col.getGreen()));

                BI.setRGB(x, y, col.getRGB());
            }
    	}
    	return BI;
    
    }
    
    public Image Redmoon(BufferedImage BI)
    {     
    	RescaleOp op = new RescaleOp(1.2f, 0, null);
        BI=op.filter(BI, null);
       
    	for (int x = 0; x < BI.getWidth(); x++) {
            for (int y = 0; y < BI.getHeight(); y++) {
                int rgba = BI.getRGB(x, y);
                
                Color col = new Color(rgba, true);
               
                col = new Color((col.getBlue()),(col.getGreen()),col.getRed());

                BI.setRGB(x, y, col.getRGB());
              
            }
    	}
    	return BI;
    }
    public Image brightenImage(BufferedImage BI,float b) {
    	RescaleOp op = new RescaleOp(b, 0, null);
        return op.filter(BI, null);
    
    }
    public Image rotateImage(BufferedImage BI) {
    	int width = BI.getWidth();
        int height = BI.getHeight();
        	BufferedImage newimage = new BufferedImage(BI.getWidth(),BI.getHeight(),BI.getType());
        	Graphics2D g2 = newimage.createGraphics();
        	g2.rotate(Math.toRadians(90),width/2,height/2);
        	g2.drawImage(BI,null,0,0);
        	return newimage;
    }
    public Image MirrorImage(BufferedImage BI) {
    	int width = BI.getWidth();
        int height = BI.getHeight();
        	BufferedImage newimage = new BufferedImage(BI.getWidth(),BI.getHeight(),BI.getType());
        	Graphics2D g2 = newimage.createGraphics();
        	//g2.rotate(Math.toRadians(90),width/2,height/2);
        	g2.drawImage(BI,0,height,width,-height,null);
        	return newimage;
    }
    public Image flipImage(BufferedImage BI) {
    	int width = BI.getWidth();
        int height = BI.getHeight();
        	BufferedImage newimage = new BufferedImage(BI.getWidth(),BI.getHeight(),BI.getType());
        	Graphics2D g2 = newimage.createGraphics();
        	//g2.rotate(Math.toRadians(90),width/2,height/2);
        	g2.drawImage(BI,width,0,-width,height,null);
        	return newimage;
    }
    
    //public Image darkenImage(BufferedImage BI,int width)
    //{   
     //   RescaleOp op = new RescaleOp(.5f, 0, null);
      //  return op.filter(BI, null);
    	//Image resultingImage = BI.getScaledInstance(300, width, Image.SCALE_SMOOTH);
    	//return resultingImage;
    //}
    public Image resizeImage(BufferedImage BI,int width,int height) {
    	Image resultingImage = BI.getScaledInstance(width,height, Image.SCALE_SMOOTH);
    	
    	return resultingImage;
    }
    public Image invertImage(BufferedImage BI)
    {
       //AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
       //tx.translate(-BI.getWidth(null), 0);
      // AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
      // return op.filter(BI, null);
       
       
        
        for (int x =0; x < BI.getWidth(); x++) {
            for (int y = 0; y < BI.getHeight(); y++) {
                int rgba = BI.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color((255-col.getRed()),
                                (255-col.getGreen()),
                                (255-col.getBlue()));
                BI.setRGB(x, y, col.getRGB());
            }
        }
        return BI;
    }
    public Image blackNwhite(BufferedImage BI) {
    	for (int x = 0; x < BI.getWidth(); x++) {
            for (int y = 0; y < BI.getHeight(); y++) {
                int rgba = BI.getRGB(x, y);
                
                Color col = new Color(rgba, true);
                int a = (col.getRed()>127?255:0+col.getGreen()>127?255:0+col.getBlue()>127?255:0)/3;
                col = new Color(a,a,a);

                BI.setRGB(x, y, col.getRGB());
            }
        }
    	return BI;
    }
    public Image grayscaleImage(BufferedImage BI) {
    	for (int x = 0; x < BI.getWidth(); x++) {
            for (int y = 0; y < BI.getHeight(); y++) {
                int rgba = BI.getRGB(x, y);
                
                Color col = new Color(rgba, true);
                int a = (col.getRed()+col.getGreen()+col.getBlue())/3;
                col = new Color(a,a,a);

                BI.setRGB(x, y, col.getRGB());
            }
        }
    	return BI;
    }
    public Image Sephia(BufferedImage BI) {
    	for (int x = 0; x < BI.getWidth(); x++) {
            for (int y = 0; y < BI.getHeight(); y++) {
                int rgba = BI.getRGB(x, y);
                Color col = new Color(rgba, true);
                int newred = (int)(0.393*col.getRed()+0.769*col.getGreen()+0.189*col.getBlue());
                int newgreen = (int)(0.349*col.getRed()+0.686*col.getGreen()+0.168*col.getBlue());
                int newblue = (int)(0.272*col.getRed()+0.534*col.getGreen()+0.131*col.getBlue());
                col = new Color(newred>255?255:newred,
                                newgreen>255?255:newgreen,
                                newblue>255?255:newblue);
                BI.setRGB(x, y, col.getRGB());
            }
        }
    	return BI;
    }
}

