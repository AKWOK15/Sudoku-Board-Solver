# Sudoku Board Solver
Solving sodoku boards always seemed like an amazing hobby until after hours of writing numbers in tiny little boxes only to erase them seconds later, I realized that maybe I should stick to coding. But then I realized that my coding skills could supply me with some help in my sudoku endeavors, and so this project was born. In the following video, I used a text file to determine which numbers (the ones in blue) the solver had to work around. Here's an example of my solver hard at work.

https://github.com/AKWOK15/Sudoku-Board-Solver/assets/121518425/c089a11b-5a54-46ab-ba5f-cb2bd1c6b717

The solver starts in the top left corner, it then moves to the right finding the next zero value and determining which number (1-9) can replace this zero while satisfying the rules of sudoku. It will continue to add values to the board and therefore the stack until it hits a dead end where the current values on the board cannot solve the puzzle. Consequently, it pops the top most value off the stack to try other combinations of values to find a different solution. The sudoku solver takes a while because in this case, 73 blank squares (91 overall squares - 18 predefined) exist and each square can have a value between 1 and 9. So here's a final picture of the solved board.

<img width="269" alt="Screen Shot 2023-09-05 at 4 12 28 PM" src="https://github.com/AKWOK15/Sudoku-Board-Solver/assets/121518425/b022edfe-aba7-4f63-bcc3-1e0b1a68ef3c">
