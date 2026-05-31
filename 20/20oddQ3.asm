# File Name: Q3.asm

.data
prompt_msg: .asciiz "Enter a positive integer: "
even_msg:   .asciiz "The number is even.\n"
odd_msg:    .asciiz "The number is odd.\n"

.text
.globl main
main:
    # 1. Prompt user and read integer using Q1 utility functions
    la $a0, prompt_msg
    jal prompt_integer          # Returns input in $v0
    move $t0, $v0               # $t0 = user input

    # 2. Check if even or odd (Calculate remainder of input / 2)
    li $t1, 2
    div $t0, $t1                # Divide input by 2
    mfhi $t2                    # $t2 = remainder (hi register holds the remainder)

    # 3. Branch based on the remainder value
    beq $t2, $zero, is_even     # If remainder == 0, branch to is_even

is_odd:
    la $a0, odd_msg
    jal print_string
    j end_program

is_even:
    la $a0, even_msg
    jal print_string

end_program:
    # 4. Safe exit using Q1 utility function
    jal exit_program

# Include utility library functions from Question 1
.include "20Q1.asm"