<h1 align="center">TP1-2 · Synchronization and Parallel Programming</h1>
<h4 align="center">Mael KERICHARD - Fabien GOARDOU</h4>
<p align="center">
   <img src="https://img.shields.io/badge/-ESIR-orange" alt="ESIR">
   <img src="https://img.shields.io/badge/-Kotlin-red" alt="Kotlin">
   <img src="https://img.shields.io/badge/-Multithreading-blue" alt="Multithreading">
</p>

# Exercise 2

## 🤨 Instructions

The goal of this exercise is to learn to use the rendez-vous mechanism provided by Java.
Rendez-vous synchronisation is provided in Java by the Exchanger<V> class. We will use this class to implement a small
ping-pong program in which two threads (called "Alice" and "Bob") repeatedly exchange two string objects ("Ping" and "
Pong").
First, read the documentation for this class
at http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Exchanger.html.
Then implement a small multithreaded program so that:

- The main program launches two threads called "Alice" and "Bob" (add a field "name" to your Thread or Runnable class
  to store this name);
- "Alice" and "Bob" both hold a reference to a string. "Alice" starts with a reference to a string containing "Ping",
  and "Bob" to a string containing "Pong".
- "Alice" and "Bob" execute the following behaviour 3 times:
    - (1) print the number of the current iteration, followed by their name, followed by the content of the string to
      which they hold a reference;
    - (2) print that they are about to go to sleep;
    - (3) wait a random time between 0 and 5000 ms;
    - (4) print that they are about to use the exchanger;
    - (5) use Java's Exchanger mechanism to exchange the thread's current string with that of the other thread;

## 💡 Explanation

1. Two threads named "Alice" and "Bob" are created and launched in the `main` function. They use
   the `PingPongRunnable` class as their `Runnable` implementation, and their names are passed as arguments to the
   constructor.

2. The `PingPongRunnable` class has a constructor that accepts the `name`, `message`, `exchanger`, and `color`
   parameters. The
   `name` field stores the thread's name, and the message field stores the thread's initial message, which is "Ping" for
   Alice and "Pong" for Bob.

3. The run method in the `PingPongRunnable` class executes a loop three times, implementing the desired behavior:
    1. Printing the iteration number, name, and message content:
    ```kt
    println("Iteration: $i $colorName has $message")
    ```
    2. Printing that the thread is about to go to sleep:
    ```kt
    println("Iteration: $i $colorName going to sleep.")
    ```
    3. Waiting a random time between 0 and 5000 ms:
    ```kt
    Thread.sleep(Random.nextLong(0, 5000))
    ```
    4. Printing that the thread is about to use the exchanger:
    ```kt
    println("Iteration: $i $colorName ready to exchange.")
    ```
    5. Using the `Exchanger` mechanism to exchange the thread's current message with that of the other thread:
    ```kt
    message = exchanger.exchange(message)
    println("Iteration: $i $colorName $message exchange completed")
    ```
