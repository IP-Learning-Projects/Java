import LinkedList.LinkedList;

public class Application {
    public static LinkedList<String> LinkedList;

    public static void  main(String[] args){

        // Default constructor - let's put "0" into head element.
        LinkedList = new LinkedList<String>();

        // add more elements to LinkedList
        LinkedList.add("1");
        LinkedList.add("2");
        LinkedList.add("3");
        LinkedList.add("4");
        LinkedList.add("5");

        /*
         * Please note that primitive values can not be added into LinkedList directly. They must be converted to their
         * corresponding wrapper class.
         */

        System.out.println("Print: LinkedList: \t\t" + LinkedList);
        System.out.println(".size(): \t\t\t\t" + LinkedList.size());
        System.out.println(".get(3): \t\t\t\t" + LinkedList.get(3) + " (get element at index:3 - list starts from 0)");
        System.out.println(".remove(2): \t\t\t\t" + LinkedList.remove(2) + " (element removed)");
        System.out.println(".get(3): \t\t\t\t" + LinkedList.get(3) + " (get element at index:3 - list starts from 0)");
        System.out.println(".size(): \t\t\t\t" + LinkedList.size());
        System.out.println("Print again: LinkedList: \t" + LinkedList);
    }
}
