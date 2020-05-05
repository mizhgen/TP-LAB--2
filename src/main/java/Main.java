import model.Point;
import model.Triangle;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    private static void readDataFromFiles(Triangle triangle, Point point, Serializer serializer) {
        try {
            File trianglefile = new File("triangle.xml");
            File pointfile = new File("point.xml");
            if(trianglefile.exists())
                serializer.read(triangle, trianglemizfile);
            else System.out.println("File not exists");
            if(pointfile.exists())
                serializer.read(point, pointfile);
            else System.out.println("File not exists");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void writeTriangle(Triangle triangle, Serializer serializer) {
        try {
            File trianglefile = new File("triangle.xml");
            if(!trianglefile.exists())
                trianglefile.createNewFile();
            serializer.write(triangle, trianglefile);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void writePoint(Point point, Serializer serializer) {
        try {
            File pointfile = new File("point.xml");
            if(!pointfile.exists())
                pointfile.createNewFile();
            serializer.write(point, pointfile);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void main(String [] args) {
        scanner.useLocale(Locale.US); // double delimiter is point "."

        Triangle triangle = new Triangle(new Point(0.0, 0.0), new Point(0.0, 0.0), new Point(0.0, 0.0));
        Point point = new Point(0.0, 0.0);
        System.out.println("" +
                "Ershov Lab 2 App." +
                "\nInput: 3 Points of Triangle and Point" +
                "\nOutput: Point in Triangle?\n");
        String command;
        // load data from file
        Serializer serializer = new Persister();
        readDataFromFiles(triangle, point, serializer);

        System.out.println("" +
                "commands:\n" +
                "finish - close the program\n" +
                "triangle - set triangles points\n" +
                "point - set point\n" +
                "run - calculate result\n" +
                "print - print current data\n" +
                "help - print this text again");
        do {
            System.out.println("Enter command: ");
            command = scanner.nextLine();
            if(command.isEmpty()) command = scanner.nextLine();
            if(command.equals("triangle")) {
                try {
                    Point [] points = new Point[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Point " + (i+1) + ":");
                        System.out.println("x: ");
                        double x = scanner.nextDouble();
                        System.out.println("y: ");
                        double y = scanner.nextDouble();
                        points[i] = new Point(x, y);
                    }
                    triangle = new Triangle(points[0], points[1], points[2]);
                    // write triangle data into xml
                    writeTriangle(triangle, serializer);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            else if(command.equals("point")) {
                try {
                    System.out.println("Point:");
                    System.out.println("x: ");
                    double x = scanner.nextDouble();
                    System.out.println("y: ");
                    double y = scanner.nextDouble();
                    point = new Point(x, y);
                    // write point data into xml
                    writePoint(point, serializer);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            else if(command.equals("print")) {
                System.out.println(triangle.toString() + "\n" + point.toString());
            }
            else if(command.equals("run")) {
                if(triangle.pointInTriangle(point)) System.out.println("Point inside of Triangle");
                else System.out.println("Point outside of Triangle");
            }
            else if(command.equals("help")) {
                System.out.println("" +
                        "commands:\n" +
                        "finish - close the program\n" +
                        "triangle - set triangles points\n" +
                        "point - set point\n" +
                        "run - calculate result\n" +
                        "print - print current data\n" +
                        "help - print this text again");
            }
            System.out.println("\n");
        } while(!command.equals("finish"));
        scanner.close();
    }
}
