import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * 编写测试类ImageProcessorTest
 */
public class ImageProcessorTest
{
    private File file1, file2, red1, red2, green1, green2, blue1, blue2, gray1, gray2;
    private ImplementImageProcessor imageProcessor;
    private ImplementImageIO imageIO;
    private Image img1, img2;
    
    /*
     * 读取bmptest文件夹中的图片
     */
    @Before
    public void getImage() throws Exception {
        file1 = new File("/home/lgx/eclipse-workspace/ImageReader/bmptest/1.bmp");
        file2 = new File("/home/lgx/eclipse-workspace/ImageReader/bmptest/2.bmp"); 
        red1 = new File("/home/lgx/eclipse-workspace/ImageReader/bmptest/goal/1_red_goal.bmp");
        red2 = new File("/home/lgx/eclipse-workspace/ImageReader/bmptest/goal/2_red_goal.bmp");
        green1 = new File("/home/lgx/eclipse-workspace/ImageReader/bmptest/goal/1_green_goal.bmp");
        green2 = new File("/home/lgx/eclipse-workspace/ImageReader/bmptest/goal/2_green_goal.bmp");
        blue1 = new File("/home/lgx/eclipse-workspace/ImageReader/bmptest/goal/1_blue_goal.bmp");
        blue2 = new File("/home/lgx/eclipse-workspace/ImageReader/bmptest/goal/2_blue_goal.bmp");
        gray1 = new File("/home/lgx/eclipse-workspace/ImageReader/bmptest/goal/1_gray_goal.bmp");
        gray2 = new File("/home/lgx/eclipse-workspace/ImageReader/bmptest/goal/2_gray_goal.bmp");
        imageProcessor = new ImplementImageProcessor();
        imageIO = new ImplementImageIO();
        img1 = imageIO.myRead(file1.toString());
    	img2 = imageIO.myRead(file2.toString());
    }

    //比较两张图片是否相同
    private boolean compareImage(Image imgA, Image imgB) 
    {
        //比较宽度和高度
		if(imgA.getWidth(null) != imgB.getWidth(null) || imgA.getHeight(null) != imgB.getHeight(null)) 
        {
			return false;
		}

		BufferedImage bufferedImageA = new BufferedImage(imgA.getWidth(null), imgA.getHeight(null), BufferedImage.TYPE_INT_RGB);
	    Graphics2D BGrA = bufferedImageA.createGraphics();
	    BGrA.drawImage(imgA, 0, 0, null);
	    BGrA.dispose();
	    BufferedImage bufferedImageB = new BufferedImage(imgA.getWidth(null), imgA.getHeight(null), BufferedImage.TYPE_INT_RGB);
	    Graphics2D BGrB = bufferedImageB.createGraphics();
	    BGrB.drawImage(imgA, 0, 0, null);
	    BGrB.dispose();
	    
	    //比较每一个像素
	    for(int i = 0; i < imgA.getWidth(null); ++i) 
        {
	    	for(int j = 0; j < imgA.getHeight(null); ++j) 
            {
	    		if(bufferedImageA.getRGB(i, j) != bufferedImageB.getRGB(i, j)) 
                {
	    			return false;
	    		}
	    	}
	    }
		return true;
	}
    
    @Test
    public void redTest() throws IOException 
    {
        Image newimg1 = imageProcessor.showChanelR(img1);
    	Image newimg2 = imageProcessor.showChanelR(img2);
    	Image goal1 = imageIO.myRead(red1.toString());
    	Image goal2 = imageIO.myRead(red2.toString());
    	assertEquals(true, compareImage(newimg1, goal1));
        assertEquals(true, compareImage(newimg2, goal2));
    }
    
    @Test
    public void greenTest() throws IOException 
    {   
        Image newimg1 = imageProcessor.showChanelG(img1);
    	Image newimg2 = imageProcessor.showChanelG(img2);
    	Image goal1 = imageIO.myRead(green1.toString());
    	Image goal2 = imageIO.myRead(green2.toString());
    	assertEquals(true, compareImage(newimg1, goal1));
        assertEquals(true, compareImage(newimg2, goal2));
    }
    
    @Test
    public void blueTest() throws IOException 
    {
        Image newimg1 = imageProcessor.showChanelB(img1);
    	Image newimg2 = imageProcessor.showChanelB(img2);
    	Image goal1 = imageIO.myRead(blue1.toString());
    	Image goal2 = imageIO.myRead(blue2.toString());
    	assertEquals(true, compareImage(newimg1, goal1));
        assertEquals(true, compareImage(newimg2, goal2));
    }
    
    @Test
    public void grayTest() throws IOException 
    {
        Image newimg1 = imageProcessor.showGray(img1);
    	Image newimg2 = imageProcessor.showGray(img2);
    	Image goal1 = imageIO.myRead(gray1.toString());
    	Image goal2 = imageIO.myRead(gray2.toString());
    	assertEquals(true, compareImage(newimg1, goal1));
        assertEquals(true, compareImage(newimg2, goal2));
    }
}

