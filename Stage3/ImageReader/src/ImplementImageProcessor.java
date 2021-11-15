import java.awt.Image;  
import java.awt.Toolkit;  
import java.awt.image.FilteredImageSource;  
import java.awt.image.RGBImageFilter;  
import imagereader.IImageProcessor;  

/*
 * ImplementImageProcessor类实现IImageProcessor类的
 * 四个方法showChanelR,showChanelG,showChanelB和showGray
 */
public class ImplementImageProcessor implements IImageProcessor 
{   

    public class RGBFilter extends RGBImageFilter
    {  
        private int color;  //color为1是红色，2是绿色，3是蓝色，4是灰色

        //获取调用哪一个模式
        public RGBFilter(int i)
        {  
            color = i;  
            canFilterIndexColorModel = true;  
        }  
        
        public int filterRGB(int x, int y, int rgb)
        {   
            if (color == 1)
            {  
                return (rgb & 0xffff0000);  
            }
            else if (color == 2)
            {  
                return (rgb & 0xff00ff00);  
            }
            else if (color == 3)
            {  
                return (rgb & 0xff0000ff);  
            }
            else
            {  
                //通过公式0.299 * R + 0.587 * G + 0.114 * B 得到灰度图
                int g = (int)(((rgb & 0x00ff0000) >> 16) * 0.299 + ((rgb & 0x0000ff00) >> 8) * 0.587 + (rgb & 0x000000ff) * 0.114);  
                return (rgb & 0xff000000) + (g << 16) + (g << 8) + g;  
            }  
        }  
    } 

    /*
	 * 实现showChanelR函数
	 */
	public Image showChanelR(Image sourceImage) 
    {
		RGBFilter redFilter = new RGBFilter(1);  
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), redFilter));  
	}

    /*
	 * 实现showChanelG函数
	 */
	public Image showChanelG(Image sourceImage) 
    {
		RGBFilter greenFilter = new RGBFilter(2);  
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), greenFilter));  
	}

    /*
	 * 实现showChanelB函数
	 */
	public Image showChanelB(Image sourceImage) 
    {
		RGBFilter blueFilter = new RGBFilter(3);   
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), blueFilter));  
	}

    /*
	 * 实现showGray函数
	 */
	public Image showGray(Image sourceImage) 
    {
		RGBFilter grayFilter = new RGBFilter(4);  
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), grayFilter));      
	}
}

