function findCubeOfNumbers(array) {
    console.log("JS => Getting cube of numbers in the array");

    return Array.prototype.map.call(array, it => Math.pow(it, 3));
}