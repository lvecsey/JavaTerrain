
public class Terrain {

    private int xw, yh;
    
    public double values[];

    int getWidth() { return xw; }
    int getHeight() { return yh; }
    
    Terrain() {

    }

    Terrain(int xw_in, int yh_in) {

	xw = xw_in;
	yh = yh_in;
	
	values = new double[xw*yh];

    }
    
    int randomWithRange(int min, int max) {
	int range = (max - min) + 1;     
	return (int)(Math.random() * range) + min;
    }
    
    int fill_terrain() {

	long rnds;
    
	int xi, yi;

	double avg = 0.0;
  
	for (yi = 0; yi < yh; yi++) {
	    for (xi = 0; xi < xw; xi++) {

		rnds = randomWithRange(0, 2147483647);

		avg += rnds / 2147483647.0;
		avg *= 0.5;
      
		values[yi*xw+xi] = avg;

	    }
	}
  
	return 0;
  
    }
  
}
