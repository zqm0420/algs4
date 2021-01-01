package ex.chapter1_2;

import edu.princeton.cs.algs4.Point2D;

public class ex1 {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        Point2D[] points = new Point2D[num];
        for (int i = 0;i < num; i++){
            points[i] = new Point2D(Math.random(), Math.random());
            points[i].draw();
        }

        double minDistance = points[0].distanceTo(points[1]);
        for (int i = 0;i < num-1; i++){
            for (int j = i + 1;j < num;j++){
                double distance = points[i].distanceTo(points[j]);
                if (minDistance>distance){
                    minDistance = distance;
                }
            }
        }
        System.out.println("min distance:"+minDistance);
    }
}
