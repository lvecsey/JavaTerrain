
public class DrawLineAntialias {

    void _dla_changebrightness(int[] from, int[] to, float br) {
	if ( br > 1.0f ) br = 1.0f;
	/* linear... Maybe something more complex could give better look */
	to[0] = (int) (br * (float)from[0]);
	to[1] = (int) (br * (float)from[1]);
	to[2] = (int) (br * (float)from[2]);
    }

    static int plot_(Image img, int xpos, int ypos, double D, int fill_color[]) {

	int xres = img.getWidth();

	img.rgb[ypos*xres*3+xpos*3+0] = (char) (fill_color[0]>>8);
	img.rgb[ypos*xres*3+xpos*3+1] = (char) (fill_color[1]>>8);
	img.rgb[ypos*xres*3+xpos*3+2] = (char) (fill_color[2]>>8);

	return 0;
	
    }

    void _dla_plot(Image img, int x, int y, int[] col, float br) {
	int[] oc = new int[3];
	_dla_changebrightness(col, oc, br);
	img.rgb[y*img.getWidth()*3+x*3+0] = (char) (oc[0]>>8);
	img.rgb[y*img.getWidth()*3+x*3+1] = (char) (oc[1]>>8);
	img.rgb[y*img.getWidth()*3+x*3+2] = (char) (oc[2]>>8);	
    }

    static int ipart_(double X) { return (int) X; }
    static double fpart_(double X) { return ((X)-(double)ipart_(X)); }
    static double rfpart_(double X) { return (1.0-fpart_(X)); }
 
    static void draw(Image img, int x1, int y1, int x2, int y2, int fill_color[]) {
  
	double dx = (double)x2 - (double)x1;
	double dy = (double)y2 - (double)y1;

	int tmp;
  
	if ( Math.abs(dx) > Math.abs(dy) ) {
	    if ( x2 < x1 ) {
		tmp=x1; x1=x2; x2=tmp;
		tmp=y1; x1=y2; y2=tmp;	
	    }
	    double gradient = dy / dx;
	    double xend = Math.round(x1);
	    double yend = y1 + gradient*(xend - x1);
	    double xgap = rfpart_(x1 + 0.5f);
	    int xpxl1 = (int) xend;
	    int ypxl1 = ipart_( (float) yend);
	    plot_(img, xpxl1, ypxl1, (float) rfpart_( (float) yend)*xgap, fill_color);
	    plot_(img, xpxl1, ypxl1+1, (float) fpart_( (float) yend)*xgap, fill_color);
	    double intery = yend + gradient;
 
	    xend = Math.round(x2);
	    yend = y2 + gradient*(xend - x2);
	    xgap = fpart_(x2+0.5);
	    int xpxl2 = (int) xend;
	    int ypxl2 = ipart_(yend);
	    plot_(img, xpxl2, ypxl2, rfpart_(yend) * xgap, fill_color);
	    plot_(img, xpxl2, ypxl2 + 1, fpart_(yend) * xgap, fill_color);
 
	    int x;
	    for(x=xpxl1+1; x <= (xpxl2-1); x++) {
		plot_(img, x, ipart_(intery), rfpart_(intery), fill_color);
		plot_(img, x, ipart_(intery) + 1, fpart_(intery), fill_color);
		intery += gradient;
	    }
	} else {
	    if ( y2 < y1 ) {
		tmp=x1; x1=x2; x2=tmp;
		tmp=y1; x1=y2; y2=tmp;	
	    }
	    double gradient = dx / dy;
	    double yend = Math.round(y1);
	    double xend = x1 + gradient*(yend - y1);
	    double ygap = rfpart_(y1 + 0.5);
	    int ypxl1 = (int) yend;
	    int xpxl1 = ipart_(xend);
	    plot_(img, xpxl1, ypxl1, rfpart_(xend)*ygap, fill_color);
	    plot_(img, xpxl1, ypxl1+1, fpart_(xend)*ygap, fill_color);
	    double interx = xend + gradient;
 
	    yend = Math.round(y2);
	    xend = x2 + gradient*(yend - y2);
	    ygap = fpart_(y2+0.5);
	    int ypxl2 = (int) yend;
	    int xpxl2 = ipart_(xend);
	    plot_(img, xpxl2, ypxl2, rfpart_(xend) * ygap, fill_color);
	    plot_(img, xpxl2, ypxl2 + 1, fpart_(xend) * ygap, fill_color);
 
	    int y;
	    for(y=ypxl1+1; y <= (ypxl2-1); y++) {
		plot_(img, ipart_(interx), y, rfpart_(interx), fill_color);
		plot_(img, ipart_(interx) + 1, y, fpart_(interx), fill_color);
		interx += gradient;
	    }
	}
    }

}
