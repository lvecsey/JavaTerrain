
class ApplyRot {

    private static Point3d n = new Point3d();

    ApplyRot() {

    }
    
    static void apply_xrot(Point3d p, double rot) {

	n.x = 1.0 * p.x + 0.0 * p.y + 0.0 * p.z;
	n.y = 0.0 * p.x + Math.cos(rot) * p.y + -Math.sin(rot) * p.z;
	n.z = 0.0 * p.x + Math.sin(rot) * p.y + Math.cos(rot) * p.z;

	p.x = n.x;
	p.y = n.y;
	p.z = n.z;

    }

    static void apply_yrot(Point3d p, double rot) {

	n.x = -Math.cos(rot) * p.x + 0.0 * p.y + Math.sin(rot) * p.z;
	n.y = 0.0 * p.x + 1.0 * p.y + 0.0 * p.z;
	n.z = -Math.sin(rot) * p.x + 0.0 * p.y + Math.cos(rot) * p.z;

	p.x = n.x;
	p.y = n.y;
	p.z = n.z;

    }

    static void apply_zrot(Point3d p, double rot) {

	n.x = Math.cos(rot) * p.x + -Math.sin(rot) * p.y + 0.0 * p.z;
	n.y = Math.sin(rot) * p.x + Math.cos(rot) * p.y + 0.0 * p.z;
	n.z = 0.0 * p.x + 0.0 * p.y + 1.0 * p.z;

	p.x = n.x;
	p.y = n.y;
	p.z = n.z;

    }

}
