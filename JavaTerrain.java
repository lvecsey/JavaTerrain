
import java.lang.Math;

public class JavaTerrain extends Terrain {

    private Image img;
    
    Terrain terrain = new Terrain(12, 360);
    
    public static void main(String[] args) {

	try {
	    JavaTerrain jt = new JavaTerrain ();
	    jt.run(args);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public void run(String[] args) throws Exception {

	int input_xres = 1920, input_yres = 1080;
	
	int n;

	int xpos,ypos;

	double x,y;
	double radians;
	double radius;

	img = new Image(input_xres, input_yres);
	
	for (n = 0; n < img.getNumPixels() * 3; n++) {
	    img.rgb[n] = 65535;
	}


	{

	    PlotTerrain pter = new PlotTerrain();

	    PlotPath path = new PlotPath();

	    PathCfg pc = new PathCfg();
	    
	    double depth = 1.75;

	    int black[] = { 0, 0, 0 };

	    int red[] = { 65535, 0, 0 };
	    
	    int debug = 0;
	    
	    terrain.fill_terrain();

	    if (debug >= 1) {
		DrawLineAntialias.draw(img, 5, 5, 100, 100, black);
	    }
		
	    pter.plot_terrain(img, depth, terrain, black);

	    pc.xoffset = 0.70;
	    path.plot_path(terrain, img, depth, red, pc);
	    
	    for (n = 0; n < img.getNumPixels(); n++) {
		img.output[6*n+0] = (byte) (img.rgb[3*n+0]>>8);
		img.output[6*n+1] = (byte) (img.rgb[3*n+0] & 255);
		img.output[6*n+2] = (byte) (img.rgb[3*n+1]>>8);
		img.output[6*n+3] = (byte) (img.rgb[3*n+1] & 255);
		img.output[6*n+4] = (byte) (img.rgb[3*n+2]>>8);
		img.output[6*n+5] = (byte) (img.rgb[3*n+2] & 255);
	    }

	    System.out.write(img.output, 0, img.sz);

	}

    }
}
