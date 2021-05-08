import static java.lang.System.*;
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;

// class Polyglot {
//     public static void main(String[] args) {
//         Context polyglot = Context.create();
//         Value array = polyglot.eval("js", "[1,2,42,4]");
//         int result = array.getArrayElement(2).asInt();
//         out.println(result);
//     }
// }

// class Polyglot {
//     public static void main(String[] args) {
//         Context context = Context.newBuilder().allowIO(true).build();
//         Value array = context.eval("python", "[1,2,42,4]");
//         int result = array.getArrayElement(2).asInt();
//         out.println(result);
//     }
// }

class Polyglot {
    // We create a polyglot context to evaluate source files
    static Context polyglotCtx = Context.newBuilder().allowAllAccess(true).build();

    // Utility method to load and evaluate a source file
    static Value loadSource(String language, String fileName) throws IOException {
        File file = new File(fileName);
        Source source = Source.newBuilder(language, file).build();
        return polyglotCtx.eval(source);
    }

    // Utility method to convert arrays between languages
    static int[] getIntArrayFromValue(Value val) {
        int[] out = new int[(int) val.getArraySize()];
        if (val.hasArrayElements()) {
            for (int i = 0; i < val.getArraySize(); i++) {
                out[i] = val.getArrayElement(i).asInt();
            }
        }
        return out;
    }

    public static void main(String[] args) throws IOException {

        int[] input = new int[] { 4, 2, 8, 5, 20, 1, 40, 13, 23 };

        /* PYTHON: Get the Fibonacci numbers from the array */
        loadSource("python", "pythonpart.py");
        Value getFibonacciNumbersFn = polyglotCtx.getBindings("python").getMember("getFibonacciNumbers");
        int[] fibNumbers = getIntArrayFromValue(getFibonacciNumbersFn.execute(input));

        /* JAVASCRIPT: Find cube of numbers in the output array */
        loadSource("js", "jspart.js");
        Value findCubeOfNumbersFn = polyglotCtx.getBindings("js").getMember("findCubeOfNumbers");
        int[] sqrNumbers = getIntArrayFromValue(findCubeOfNumbersFn.execute(fibNumbers));

        /* C++: Get the sum of the numbers in the output array */
        loadSource("llvm", "cpppart");
        Value getSumOfArrayFn = polyglotCtx.getBindings("llvm").getMember("getSumOfArray");
        int sum = getSumOfArrayFn.execute(sqrNumbers, sqrNumbers.length).asInt();

        /* Rust: Find the cube root of sum */
        Value cubeRootFn = loadSource("llvm", "rustpart.bc").getMember("cube_root");
        // println! macro does not work from Rust when embedded, some issue with mangling
        System.out.println("Rust => Find cube root of the number");
        Double cubeRoot = cubeRootFn.execute(sum).asDouble();

        /* RUBY: Find factorial of the number */
        Value factorialFn = loadSource("ruby", "rubypart.rb");
        long out = factorialFn.execute(cubeRoot).asLong();

        System.out.println("Sum: " + sum);
        System.out.println("Cube Root: " + cubeRoot);
        System.out.println("Factorial: " + out);
    }
}