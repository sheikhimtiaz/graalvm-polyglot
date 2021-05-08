factorial = -> (num) {
    puts "Ruby => Find factorial of the number"
    (1..num).inject {|product, num| product * num }
}