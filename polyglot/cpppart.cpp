#include<iostream>
using namespace std;

// Function to find the sum of integer array
// extern "C" is required to suppress mangling
extern "C" int getSumOfArray(int array[], int size) {
    printf("C++ => Find sum of numbers in an array\n");

    int i, sum = 0;
    for(i = 0; i < size; i++) {
        sum += array[i];
    }
    return sum;
}