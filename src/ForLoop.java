public class ForLoop  {
    public static void main(String[] args) {
        // using for loop
        System.out.println("==== Using For Loop ====");
        for (int  i = 1  ; i < 5 ; i++ ) {
            System.out.println(i);
            System.out.println("==== Break Loop ====");
            for (double  s = 1.3 ;  s <=15.3 ; s++){
                System.out.println(s);
                break ;
                }
            System.out.println("==== Continue Loop ====");
            String name = "heng" ;
            for ( i = 0 ; i <10 ; i++  ) {
                System.out.println("print 10 name = " + name );
            }
                System.out.println("==== Print ID Of Student  ====");
            for  ( i = 0 ; i <= 20 ;  i++) {
                System.out.println("Student ID is : " + i);
            
            }
                System.out.println("==== Print Name of Student is ending ====");
        }
    }
}
