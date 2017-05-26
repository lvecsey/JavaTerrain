
// Based on Java example from rosettacode.org

public class Bressenham {

    static void plot(Image img, int xpos, int ypos, int fill_color[]) {

	int offset = ypos * img.getWidth() * 3 + xpos * 3;
	
	img.rgb[offset+0] = (char) (fill_color[0] >> 8);
	img.rgb[offset+1] = (char) (fill_color[1] >> 8);
	img.rgb[offset+2] = (char) (fill_color[2] >> 8);	
	
    }
    
    static void drawLine(Image img, int x1, int y1, int x2, int y2, int fill_color[]) {
        // delta of exact value and rounded value of the dependant variable
        int d = 0;
 
        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);
 
        int dy2 = (dy << 1); // slope scaling factors to avoid floating
        int dx2 = (dx << 1); // point
 
        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;
 
        if (dy <= dx) {
            for (;;) {
                plot(img, x1, y1, fill_color);
                if (x1 == x2)
                    break;
                x1 += ix;
                d += dy2;
                if (d > dx) {
                    y1 += iy;
                    d -= dx2;
                }
            }
        } else {
            for (;;) {
                plot(img, x1, y1, fill_color);
                if (y1 == y2)
                    break;
                y1 += iy;
                d += dx2;
                if (d > dy) {
                    x1 += ix;
                    d -= dy2;
                }
            }
        }
    }
}
