

// R context
var array = Polyglot.eval("R", "c(1,2,42,4)")
console.log(array[2]);



// Java context
// var array = new (Java.type("int[]"))(4);
// array[2] = 42;
// console.log(array[2])