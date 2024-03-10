
class square{

    int length, breadth;
    public void get(int x, int y)
    {
        length = x;
        breadth = y;
    }
    int area()
    {
        return (length*breadth);
    }
}

class cube extends square
{
    int height;
    public void getdate(int x, int y, int z)
    {
        get(x,y);
        height = z;
    }
    int volume()
    {
        return(length*breadth*height);
    }
}

public class RunInheritance {

    public static void main(String a[])
    {
        cube C = new cube();
        C.getdate(10,20,30);

        int b1 = C.area();
        System.out.println("Area of square: "+ b1);

        int b2 = C.volume();
        System.out.println("Volume of cube: "+ b2);
    }
}
