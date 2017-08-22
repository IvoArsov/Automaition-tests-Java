import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        List<String> catNamesArr = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            String catName = "cat" + i;
            catNamesArr.add(catName);
        }

        System.out.println(catNamesArr);


    }

    public static class Cat{
        //fields
        private String name;
        private String color;

        //getter Name
        public String getName(){
            return this.name;
        }

        //setter Name
        public void setName(String name){
            this.name = name;
        }

        //getter Color
        public String getColor(){
            return this.color;
        }

        //setter Color
        public void setColor(String color){
            this.color = color;
        }

        //default constructor
        public Cat(){
            this.name = "Unnamed";
            this.color = "Grey";
        }

        //constructor with parameters
        public Cat(String name, String color){
            this.name = name;
            this.color = color;
        }

        public void sayMiauu(){
            System.out.printf("Cat %s say: Mali kak sa napih! %n", name);
        }
    }
}
