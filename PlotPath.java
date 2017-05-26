
import java.lang.Math;

public class PlotPath {

    private double M_PI_2 = Math.PI * 0.5;

    Point3d pnta, pntb, pntc, pntd;

    Point3d pnt_avg1, pnt_avg2;

    Point3d pnt_avg;

    Point pt;
  
    void normalize_2d(double vec[]) {

	double magnitude = Math.sqrt(vec[0] * vec[0] + vec[1] * vec[1]);

	vec[0] /= magnitude;
	vec[1] /= magnitude;

    }

    PlotPath() {

	pnta = new Point3d();
	pntb = new Point3d();
	pntc = new Point3d();
	pntd = new Point3d();

	pnt_avg1 = new Point3d();
	pnt_avg2 = new Point3d();

	pnt_avg = new Point3d();	

	pt = new Point();
	
    }
    
    int plot_path(Terrain ter, Image img, double depth, int fill_color[], PathCfg pc) {

	int xpos, ypos;

	int xi, yi;

	double aspect = ((double) img.getWidth()) / img.getHeight();

	XPack xp1, xp2;

	double sf = 0.50;

	long n;
	long nend = 125000;

	double v;

	double vec[] = { 0.45, 0.30 };

	normalize_2d(vec);
  
	yi = 1;

	xi = (int) (pc.xoffset * (ter.getWidth() >> 1));
	xi += ter.getWidth() >> 1;
  
	for ( ; yi < 20; ) {

	    v = 0.5;
    
	    {

		pnta.x = 2.0 * (xi-1) / ter.getWidth() - 1.0;
		pnta.y = 2.0 * (yi-1) / ter.getWidth() - 1.0;	
		pnta.z = sf * ter.values[(yi-1)*ter.getWidth()+xi-1];

		
		pntb.x = 2.0 * xi / ter.getWidth() - 1.0;
		pntb.y = 2.0 * (yi-1) / ter.getWidth() - 1.0;	
		pntb.z = sf * ter.values[(yi-1)*ter.getWidth()+xi];

		pntc.x = 2.0 * (xi-1) / ter.getWidth() - 1.0;
		pntc.y = 2.0 * yi / ter.getWidth() - 1.0;
		pntc.z = sf * ter.values[yi*ter.getWidth()+xi-1];

		pntd.x = 2.0 * xi / ter.getWidth() - 1.0;
		pntd.y = 2.0 * yi / ter.getWidth() - 1.0;	
		pntd.z = sf * ter.values[yi*ter.getWidth()+xi];

		pnt_avg1.x = v * (pnta.x + pntc.x) + v * (pntb.x + pntd.x);
		pnt_avg1.x *= 0.50;
      
		pnt_avg1.y = v * (pnta.y + pntc.y) + v * (pntb.y + pntd.y);
		pnt_avg1.y *= 0.50;
      
		pnt_avg1.z = v * (pnta.z + pntc.z) + v * (pntb.z + pntd.z);
		pnt_avg1.z *= 0.50;
      
	    }

	    yi++;
    
	    {
    
		pnta.x = 2.0 * (xi-1) / ter.getWidth() - 1.0;
		pnta.y = 2.0 * (yi-1) / ter.getWidth() - 1.0;	
		pnta.z = sf * ter.values[(yi-1)*ter.getWidth()+xi-1];

		pntb.x = 2.0 * xi / ter.getWidth() - 1.0;
		pntb.y = 2.0 * (yi-1) / ter.getWidth() - 1.0;	
		pntb.z = sf * ter.values[(yi-1)*ter.getWidth()+xi];

		pntc.x = 2.0 * (xi-1) / ter.getWidth() - 1.0;
		pntc.y = 2.0 * yi / ter.getWidth() - 1.0;	
		pntc.z = sf * ter.values[yi*ter.getWidth()+xi-1];

		pntd.x = 2.0 * xi / ter.getWidth() - 1.0;
		pntd.y = 2.0 * yi / ter.getWidth() - 1.0;	
		pntd.z = sf * ter.values[yi*ter.getWidth()+xi];

		pnt_avg2.x = v * (pnta.x + pntc.x) + v * (pntb.x + pntd.x);
		pnt_avg2.x *= 0.50;
      
		pnt_avg2.y = v * (pnta.y + pntc.y) + v * (pntb.y + pntd.y);
		pnt_avg2.y *= 0.50;
      
		pnt_avg2.z = v * (pnta.z + pntc.z) + v * (pntb.z + pntd.z);
		pnt_avg2.z *= 0.50;

	    }
  
	    for (n = 0; n < nend; n++) {

		v = n; v /= nend;
      
		pnt_avg.x = pnt_avg1.x * v + pnt_avg2.x * (1.0 - v);
		pnt_avg.y = pnt_avg1.y * v + pnt_avg2.y * (1.0 - v);
		pnt_avg.z = pnt_avg1.z * v + pnt_avg2.z * (1.0 - v);      
      
		ApplyRot.apply_xrot(pnt_avg, 0.5 * M_PI_2);
      
		pt.x = pnt_avg.x / (pnt_avg.z + depth);
		pt.y = pnt_avg.y / (pnt_avg.z + depth);      

		pt.x /= aspect;
		pt.y *= -1.0;
      
		xpos = (int) (pt.x * (img.getWidth()>>1)); xpos += img.getWidth()>>1;
		ypos = (int) (pt.y * (img.getHeight()>>1)); ypos += img.getHeight()>>1;      

		if (xpos<0) {
		    xpos=-xpos;
		}

		if (ypos<0) {
		    ypos=-ypos;
		}

		xpos %= img.getWidth();
		ypos %= img.getHeight();
      
		img.rgb[ypos*img.getWidth()+xpos+0] = (char) (fill_color[0] >> 8);
		img.rgb[ypos*img.getWidth()+xpos+1] = (char) (fill_color[1] >> 8);
		img.rgb[ypos*img.getWidth()+xpos+2] = (char) (fill_color[2] >> 8);		
      
	    }

	}

	return 0;

    }

}
    
