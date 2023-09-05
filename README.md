# Sudoku-Board-Solver
In this project, I created a sudoku solver using stacks. Here's an example of my solver hard at work.

https://github.com/AKWOK15/Sudoku-Board-Solver/assets/121518425/c089a11b-5a54-46ab-ba5f-cb2bd1c6b717

The solver finds the next 0 value on the board and determine which number (1-9) can replace this zero. It will continue doing this until it either finishes, but most likely, it will run into a scenario where there aren’t any more possibilities to try with the current values on the board. So, it will pop a value off the board to try the other possibilities, if that still doesn’t work, it will continue the process of popping and pushing values (cells) onto the stack until the puzzle is either solved or has no solution.

<img width="269" alt="Screen Shot 2023-09-05 at 4 27 05 PM" src="https://github.com/AKWOK15/Sudoku-Board-Solver/assets/121518425/a77024be-51fc-49d4-b4d8-6bfa01b34bef">




<img width="269" alt="Screen Shot 2023-09-05 at 4 12 28 PM" src="https://github.com/AKWOK15/Sudoku-Board-Solver/assets/121518425/b022edfe-aba7-4f63-bcc3-1e0b1a68ef3c">
