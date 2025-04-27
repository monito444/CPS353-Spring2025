# CPS353 Project Spring 2025
## Sum of even numbers in fibonacci.

-The computation in this repo will ask the user for a positive integer from a specified file. It will then show the fibonacci numbers up to the specificed integer.
It will then compute sum of all even numbers in the sequence.

-(assignment 8) The Benchmark Test had an original time of 3376400 ns while the improved version had a time of 82200 ns.
-The following link is where the benchmark test is located specifically in this repo: https://github.com/monito444/CPS353-Spring2025/blob/main/test/project/BenchmarkTest.java  .(period not apart of link)
-The issue with the original compute engine is that it used a recursive appraoch that had it being much slower since it 
    had to keep going through and keeping track of multiple method calls. The imporved approach is from the iterative approach
    of our for loop that does what used to be done recursively, all in the for loop. ELiminating the inefficiency of the recursive 
    implementation improved the speed 41.0754 times.

![picture of diagram of computation in the project](IMG_6860.jpg)
