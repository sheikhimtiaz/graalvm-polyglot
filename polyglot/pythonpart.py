import math

def isPerfectSquare(num):
    n = int(math.sqrt(num))
    return (n * n == num)

# Function to check if the number is in Fibonacci or not
def getFibonacciNumbers(array):
    print("Python => Filtering Fibonacci number from the array");

    out = []
    n = len(array)
    count = 0
    for i in range(n):
        if (isPerfectSquare(5 * array[i] * array[i] + 4) or
            isPerfectSquare(5 * array[i] * array[i] - 4)):

            out.append(array[i]);
            count = count + 1

    if (count == 0):
        print("None present");

    return out