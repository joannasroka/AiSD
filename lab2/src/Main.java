public class Main {
    public static void main(String[] args) {
/*
        Stack <Integer> stack = new Stack<>();
        stack.push(1);
        System.out.println(stack.peek());

        stack.push(2);
        System.out.println(stack.peek());

        stack.pop();
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());

        stack.clear();
        System.out.println(stack.isEmpty());

        stack.push(1);
        stack.set(0,2);
        System.out.println(stack.peek());
*/

        Queue<Integer> queue = new Queue<>();

        System.out.println(queue.isEmpty());

        queue.enqueue(1);
        System.out.println(queue.isEmpty());

        queue.enqueue(2);
        System.out.println(queue.peek());

        queue.dequeue();
        System.out.println(queue.peek());

        queue.clear();
        System.out.println(queue.size());
        queue.enqueue(10);
        System.out.println(queue.peek());


    }
}
