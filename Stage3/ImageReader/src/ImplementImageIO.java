import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import imagereader.IImageIO;

/*
 * ImplementImageIO类实现IImageIO类的
 * 两个方法myRead和myWrite
 */
public class ImplementImageIO implements IImageIO 
{
	/* 
	 * 位图头:
	 * #0-1	保存位图文件的标识符，这两个字节的典型数据是BM
     * #2-5	使用一个dword保存位图文件大小
     * #6-9	是保留部分，留做以后的扩展使用,对实际的解码格式没有影响
     * #10-13 保存位图数据位置的地址偏移，也就是起始地址
	 */
	public class ImageHead 
	{
		private int bitFileType;       //位图类型
		private int bitFileSize;       //位图文件大小
		private int bitFileReserved;   //位图保留部分
		private int bitFileStart;      //位图起始地址

		public int getbitFileType() 
		{
			return bitFileType;
		}

		public int getbitFileSize() 
		{
			return bitFileSize;
		}

		public int getbitFileReserved() 
		{
			return bitFileReserved;
		}
		
		public int getbitFileStart() 
		{
			return bitFileStart;
		}	

		/*
		 *  构造一个文件输入流
		 */
		public ImageHead(FileInputStream file) 
		{
			int size = 14;
			byte imageHead[] = new byte[size];
			try 
			{
				bitFileType = 16973;   
				file.read(imageHead, 0, size);
				//8位改为32位
				bitFileSize = (((int)imageHead[5] & 0xff) << 24) | (((int)imageHead[4] & 0xff) << 16)
							| (((int)imageHead[3] & 0xff) << 8) | ((int)imageHead[2] & 0xff);
				bitFileReserved = 0;
			}
			catch(IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/*
	* 位图信息：
	* #14-17 定义以下用来描述影像的区块（BitmapInfoHeader）的大小。它的值是：40 - Windows 3.2、95、NT、12 - OS/2 1.x、240 - OS/2 2.x
	* #18-21 保存位图宽度（以像素个数表示）
	* #22-25 保存位图高度（以像素个数表示）
	* #26-27 保存所用彩色位面的个数。不经常使用
	* #28-29 保存每个像素的位数，它是图像的颜色深度。常用值是1、4、8（灰阶）和24（彩色）
	* #30-33 定义所用的压缩算法。允许的值是0、1、2、3、4、5，见下表
	* #34-37 保存图像大小。这是原始 （:en:raw）位图数据的大小，不要与文件大小混淆。
	* #38-41 保存图像水平方向分辨率
	* #42-45 保存图像竖值方向分辨率
	* #46-49 保存所用颜色数目
	* #50-53 保存所用重要颜色数目。当每个颜色都重要时这个值与颜色数目相等
	*/
	public class ImageInfo {
		private int bitSize;      //位图大小
		private int bitWidth;     //位图宽度
		private int bitHeight;    //位图高度
		private int bitPixel;     //位图每个像素位数
		private int bitDataSize;   //位图数据大小
        private int bitColor[]; //位图颜色
        
        public int getbitSize() 
		{
        	return bitSize;
        }

        public int getbitWidth() 
		{
        	return bitWidth;
        }

        public int getbitHeight() 
		{
        	return bitHeight;
        }
        
        public int getbitPixel() 
		{
        	return bitPixel;
        }
		
		public int[] getbitColor() 
		{
        	return bitColor;
        }
		
		/*
		 *  构造位图信息
		 */
        public ImageInfo(FileInputStream file) 
		{
        	int size = 40;
        	byte imageInfo[] = new byte[size];
        	try 
			{
                file.read(imageInfo, 0, size);
				bitWidth = (((int)imageInfo[7] & 0xff) << 24) | (((int)imageInfo[6] & 0xff) << 16)
							| (((int)imageInfo[5] & 0xff) << 8) | ((int)imageInfo[4] & 0xff);

				bitHeight = (((int)imageInfo[11] & 0xff) << 24) | (((int)imageInfo[10] & 0xff) << 16)
							| (((int)imageInfo[9] & 0xff) << 8) | ((int)imageInfo[8] & 0xff);

				bitPixel = (((int)imageInfo[15] & 0xff) << 8) | ((int)imageInfo[14] & 0xff);

				bitDataSize = (((int)imageInfo[23] & 0xff) << 24) | (((int)imageInfo[22] & 0xff) << 16)
							| (((int)imageInfo[21] & 0xff) << 8) | ((int)imageInfo[20] & 0xff);
							
				bitColor = new int[bitHeight * bitWidth];	

				if (bitPixel == 24) 
				{
					int size1 = (bitDataSize / bitHeight) - bitWidth * 3;
					if (size1 == 4) 
					{
						size1 = 0;
					}
					
					byte bitDataArray[] = new byte[bitDataSize];
					file.read(bitDataArray, 0, bitDataSize);
					int index = 0;
					for (int i = 0; i < bitHeight; i ++) 
					{
						for (int j = 0; j < bitWidth; j ++) 
						{
							bitColor[bitWidth * (bitHeight - i - 1) + j] = ((255 & 0xff) << 24) | (((int)bitDataArray[index + 2] & 0xff) << 16)
							                                           | (((int)bitDataArray[index + 1] & 0xff) << 8) | ((int)bitDataArray[index] & 0xff);
			                index += 3;
						}
						index += size1;
					}
				}
        	}
        	catch (IOException e) 
			{
                e.printStackTrace();
        	}
        }

		//获取图像
        public Image getImage() 
		{
        	Image img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(bitWidth, bitHeight, bitColor, 0, bitWidth));
            return img;
        }
	}

	/*
	 * 实现myRead函数
	 */
	public Image myRead(String filePath) 
	{
		Image image = null;
		try 
		{
			FileInputStream file = new FileInputStream(filePath);
            ImageHead imageHead = new ImageHead(file);
            ImageInfo imageInfo = new ImageInfo(file);
            image = imageInfo.getImage();
            file.close();
            return image;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 实现myWrite函数,调用BufferedImage实现
	 */
	public Image myWrite(Image img, String filePath) 
	{
		try 
		{
			File file = new File(filePath + ".bmp");
            BufferedImage buffImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics grap = buffImage.getGraphics();
            grap.drawImage(img, 0, 0, null);
            grap.dispose();
            ImageIO.write(buffImage, "bmp", file);
		}
		catch (Exception e) 
		{
			e.printStackTrace(System.out);
		}
		return img;
	}
}
