import java.util.*;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int ast : asteroids) {
            boolean alive = true;

            // Only negative asteroids can collide with positive ones on the stack
            while (alive && ast < 0 && !stack.isEmpty() && stack.peek() > 0) {
                if (stack.peek() < -ast) {
                    stack.pop(); // Positive asteroid destroyed, keep checking
                } else if (stack.peek() == -ast) {
                    stack.pop(); // Both destroyed
                    alive = false;
                } else {
                    alive = false; // Current negative asteroid destroyed
                }
            }

            if (alive) {
                stack.push(ast);
            }
        }

        // Convert stack to array
        int n = stack.size();
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}
