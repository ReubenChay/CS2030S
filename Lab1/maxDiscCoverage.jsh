

Circle createUnitCircle(Point p, Point q) { 

    Point midPt = p.midPoint(q); 
    double angle = p.angleTo(q) + (Math.PI / 2);

    double dist = p.dist(midPt);
    Point centre = midPt.moveTo(angle, dist);

    return new Circle(centre, 1); 

}


int findMaxDiscCoverage(Point[] points) {
    int maxDiscCoverage = 0;
    int thisMax = 0; 

    for (int i = 0; i < points.length - 1; i++) {
        for (int j = i + 1; j < points.length; j++) {

            if (points[i].distanceTo(points[j]) <= 2) {

                Circle c = createUnitCircle(points[i], points[j]);

                for (int k = 0; k < points.length; k ++) {
                    if (c.contains(points[k])) {
                        thisMax += 1;
                    }
                } 

                if (thisMax > maxDiscCoverage) {
                    maxDiscCoverage = thisMax; 
                } 
            }
            thisMax = 0; 
        }
    }
    return maxDiscCoverage; 
}






