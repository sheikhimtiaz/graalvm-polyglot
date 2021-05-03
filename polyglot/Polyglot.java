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

class Polyglot {
    public static void main(String[] args) {
        Context context = Context.newBuilder().allowIO(true).build();
        Value array = context.eval("python", "[1,2,42,4]");
        int result = array.getArrayElement(2).asInt();
        out.println(result);
    }
}
