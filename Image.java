
public class Image {

    private int xres, yres;
    private int num_pixels;

    public char[] rgb;
    
    public byte[] output;

    public int sz;
    
    int getNumPixels() { return num_pixels; }
    int getWidth() { return xres; }
    int getHeight() { return yres; }
    
    Image(int input_xres, int input_yres) {

	xres = input_xres;
	yres = input_yres;
	
	num_pixels = xres * yres;
	
	sz = num_pixels * 6;

	rgb = new char[num_pixels * 3];
	
	output = new byte[sz];

    }
    
}
