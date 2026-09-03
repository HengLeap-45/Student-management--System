public class nestloop {
    public static void main(String[] args) {
        // first we learn about outer loop
        System.out.println("========Outer loop=======");
        for (int i = 1; i <= 2; i++) {
            System.out.println("The Result is : " + i);
            // Execute 2 time
            // and now we use the inner loop
            for (int j = 0; j <= 5; j++) {
                System.out.println("The Result is : " + j);
                //Execute 5 time
            }
        }
    }
}
