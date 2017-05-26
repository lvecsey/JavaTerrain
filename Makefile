
PROGRAM = JavaTerrain

$(HOME)/public_html/images/output_JavaTerrain.png : RENDER_RES=1920x1080

$(HOME)/public_html/images/output_JavaTerrain.png : img_1920x1080.rgb
	@convert -size $(RENDER_RES) $^ $@

img_1920x1080.rgb : RENDER_RES=1920x1080

img_1920x1080.rgb : $(PROGRAM).class Terrain.class Image.class PlotTerrain.class Point.class Point3d.class DrawLineAntialias.class XPack.class ApplyRot.class PathCfg.class PlotPath.class
	@java $(PROGRAM) > $@

%.class : %.java
	javac -Xlint:cast -Xlint:deprecation -Xlint:divzero -Xlint:empty -Xlint:fallthrough -Xlint:finally -Xlint:overrides -Xlint:path -Xlint:serial -Xlint:unchecked $^





