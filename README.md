# Sudoku Board Solver
## About This Project
Solving sudoku boards always seemed like an amazing hobby until after hours of writing numbers in tiny little boxes only to erase them seconds later, I realized that maybe I should stick to coding. But then I thought, what if my coding skills could help in my sudoku endeavors, and so this project was born. In the following video, I used a text file to determine which numbers (the ones in blue) the solver had to work around. Here's an example of my solver hard at work.

https://github.com/AKWOK15/Sudoku-Board-Solver/assets/121518425/c089a11b-5a54-46ab-ba5f-cb2bd1c6b717

## Built With
![Java][Java.com]

[Java.com]:https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white

## How It Works
The solver starts in the top left corner, it then moves to the right finding the next zero value and determining which number (1-9) can replace this zero while still satisfying the rules of sudoku. It will continue to add values to the board and therefore the stack until it hits a dead end where the current values on the board cannot solve the puzzle. Consequently, it pops the top most value off the stack to try other combinations of values to find a different solution. The sudoku solver takes a while because in this case, 73 blank squares (91 overall squares - 18 predefined) exist and each square can have a value between 1 and 9. So here's a final picture of the solved board.

<img width="269" alt="Screen Shot 2023-09-05 at 4 12 28 PM" src="https://github.com/AKWOK15/Sudoku-Board-Solver/assets/121518425/b022edfe-aba7-4f63-bcc3-1e0b1a68ef3c">

My code also easilys adapts and can create any size board and specify the number of locked numbers. In the image below, I created a 6 by 6 sudoku board with 10 fixed numbers. 

<img width="269" alt="Screen Shot 2023-09-05 at 5 22 10 PM" src="https://github.com/AKWOK15/Sudoku-Board-Solver/assets/121518425/a1915d08-af21-48a5-b516-21119c1d536c">

## Usage
1. Download all the above files into a folder
2. Open the folder in your favorite IDE or in the terminal
3. Run "javac *.java" to compile all the files
4. Run "java Sudoku text filename" to solve a given text file where filename is the name of your file
5. OR run "java sudoku x" to solve a board a board where x is the number of initial values on the board
