public class ForEachLoop {
    public static void main(String[] args) {
        String [] university = {"manager" , "student" ,"President" , "teacher"};
        for (String name : university) {
            System.out.println(name);
        }
        int [] numbers = {1,2,3,4,5,6,7,8,9,10};
        for (int number : numbers) {
            System.out.println(number);
        }
        double [] doubles = {1.3 , 1.4 , 1.7};
        for (double s : doubles) {
            System.out.println(s);
        }
        float [] floats = {1.0f, 2.0f, 3.0f};
        for (float f : floats) {
            System.out.println(f);
        }
        char [] chars = {'1', '2', 'c'  };
        for (char ch : chars) {
            System.out.println(ch);
        }
        boolean [] booleans = {true, false,true};
        for (boolean b : booleans) {
            System.out.println(b);
        }
        long [] longs = {1,2,3,4,5,6,7,8,9,10};
        for (long l : longs) {
            System.out.println(l);
        }
        short [] shorts = {1,2,3,4,5,6,7,8,9,10};
        for (short s : shorts) {
            System.out.println(s);
        }
    }
}
