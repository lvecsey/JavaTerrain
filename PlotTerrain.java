
import java.lang.Math;

public class PlotTerrain {

    private double M_PI_2 = Math.PI * 0.5;

    private Point pt;

    private Point3d pnta, pntb;

    private XPack xp1, xp2;

    PlotTerrain() {

	pt = new Point();

	pnta = new Point3d();
	pntb = new Point3d();

	xp1 = new XPack();
	xp2 = new XPack();
	
    }
    
    int plot_terrain(Image img, double depth, Terrain ter, int fill_color[]) {

	long xpos, ypos;

	int xi, yi;

	double aspect = ((double) img.getWidth()) / img.getHeight();

	double sf = 0.50;
  
	for (yi = 1; yi < ter.getHeight(); yi++) {
	    for (xi = 1; xi < ter.getWidth(); xi++) {

		pnta.x = 2.0 * (xi-1) / ter.getWidth() - 1.0;
		pnta.y = 2.0 * (yi-1) / ter.getWidth() - 1.0;	
		pnta.z = sf * ter.values[(yi-1)*ter.getWidth()+xi-1];

		ApplyRot.apply_xrot(pnta, 0.5 * M_PI_2);
      
		pt.x = pnta.x / (pnta.z + depth);
		pt.y = pnta.y / (pnta.z + depth);      

		pt.x /= aspect;
		pt.y *= -1.0;
      
		xpos = (long) (pt.x * (img.getWidth()>>1)); xpos += img.getWidth()>>1;
		ypos = (long) (pt.y * (img.getHeight()>>1)); ypos += img.getHeight()>>1;      

		if (xpos<0) {
		    xpos=-xpos;
		}

		if (ypos<0) {
		    ypos=-ypos;
		}

		xpos %= img.getWidth() - 1;
		ypos %= img.getHeight() - 1;
      
		xp1.x0 = (int) xpos;
		xp1.y0 = (int) ypos;

		pnta.x = 2.0 * xi / ter.getWidth() - 1.0;
		pnta.y = 2.0 * (yi-1) / ter.getWidth() - 1.0;	
		pnta.z = sf * ter.values[(yi-1)*ter.getWidth()+xi];

		ApplyRot.apply_xrot(pnta, 0.5 * M_PI_2);
      
		pt.x = pnta.x / (pnta.z + depth);
		pt.y = pnta.y / (pnta.z + depth);      

		pt.x /= aspect;
		pt.y *= -1.0;
      
		xpos = (long) (pt.x * (img.getWidth()>>1)); xpos += img.getWidth()>>1;
		ypos = (long) (pt.y * (img.getHeight()>>1)); ypos += img.getHeight()>>1;      

		if (xpos<0) {
		    xpos=-xpos;
		}

		if (ypos<0) {
		    ypos=-ypos;
		}

		xpos %= img.getWidth() - 1;
		ypos %= img.getHeight() - 1;
      
		xp1.x1 = (int) xpos;
		xp1.y1 = (int) ypos;

		pnta.x = 2.0 * (xi-1) / ter.getWidth() - 1.0;
		pnta.y = 2.0 * yi / ter.getWidth() - 1.0;	
		pnta.z = sf * ter.values[yi*ter.getWidth()+xi-1];

		ApplyRot.apply_xrot(pnta, 0.5 * M_PI_2);
      
		pt.x = pnta.x / (pnta.z + depth);
		pt.y = pnta.y / (pnta.z + depth);      

		pt.x /= aspect;
		pt.y *= -1.0;
      
		xpos = (long) (pt.x * (img.getWidth()>>1)); xpos += img.getWidth()>>1;
		ypos = (long) (pt.y * (img.getHeight()>>1)); ypos += img.getHeight()>>1;      

		if (xpos<0) {
		    xpos=-xpos;
		}

		if (ypos<0) {
		    ypos=-ypos;
		}

		xpos %= img.getWidth() - 1;
		ypos %= img.getHeight() - 1;
      
		xp2.x0 = (int) xpos;
		xp2.y0 = (int) ypos;

		pnta.x = 2.0 * xi / ter.getWidth() - 1.0;
		pnta.y = 2.0 * yi / ter.getWidth() - 1.0;	
		pnta.z = sf * ter.values[yi*ter.getWidth()+xi];

		ApplyRot.apply_xrot(pnta, 0.5 * M_PI_2);
      
		pt.x = pnta.x / (pnta.z + depth);
		pt.y = pnta.y / (pnta.z + depth);      

		pt.x /= aspect;
		pt.y *= -1.0;
      
		xpos = (long) (pt.x * (img.getWidth()>>1)); xpos += img.getWidth()>>1;
		ypos = (long) (pt.y * (img.getHeight()>>1)); ypos += img.getHeight()>>1;      

		if (xpos<0) {
		    xpos=-xpos;
		}

		if (ypos<0) {
		    ypos=-ypos;
		}

		xpos %= img.getWidth() - 1;
		ypos %= img.getHeight() - 1;
      
		xp2.x1 = (int) xpos;
		xp2.y1 = (int) ypos;

		System.err.println("xp1 " + xp1.x0 + " " + xp1.y0);
		System.err.println("xp1 " + xp1.x1 + " " + xp1.y1);

		System.err.println("xp2 " + xp2.x0 + " " + xp2.y0);
		System.err.println("xp2 " + xp2.x1 + " " + xp2.y1);				
		//		if (xp1.x0 != xp1.x1)
		//		    DrawLineAntialias.draw(img, xp1.x0, xp1.y0, xp1.x1, xp1.y1, fill_color);

		//		if ( (xp2.x0 != xp2.x1) && (xp2.y0 != xp2.y1) )
		//		    DrawLineAntialias.draw(img, xp2.x0, xp2.y0, xp2.x1, xp2.y1, fill_color);

		//		if (xp1.x0 != xp2.x0)
		//		    DrawLineAntialias.draw(img, xp1.x0, xp1.y0, xp2.x0, xp2.y0, fill_color);

		if (xp2.x0 != xp1.x0)
		    DrawLineAntialias.draw(img, xp2.x0, xp2.y0, xp1.x1, xp1.y1, fill_color);
		
	    }
	}

	return 0;

    }

}
