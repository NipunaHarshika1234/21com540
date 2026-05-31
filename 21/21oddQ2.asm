# Q2_even_odd.asm
  # Brings in the subprograms from Q1

.data
    prompt_msg: .asciiz "Enter a positive integer: "
    even_msg:   .asciiz "The number is even.\n"
    odd_msg:    .asciiz "The number is odd.\n"

.text
.globl main

main:
    # 1. Print the prompt message
    la $a0, prompt_msg  # Load address of the prompt string
    jal print_string    # Call subprogram to print it
    
    # 2. Read the integer from user
    jal prompt_integer  # Call subprogram (returns integer in $v0)
    move $t0, $v0       # Save the integer from $v0 into safe temporary register $t0
    
    # 3. Check if even or odd (Divide by 2)
    li $t1, 2
    div $t0, $t1        # Divide $t0 by 2. Remainder goes to 'hi' register.
    mfhi $t2            # Move From hi: copy the remainder into $t2
    
    # 4. Branching logic (if-else)
    beq $t2, $zero, print_even  # If remainder ($t2) == 0, jump to print_even
    
    # Otherwise, it's odd:
    la $a0, odd_msg
    jal print_string
    j end_program       # Jump over the even section to finish

print_even:
    la $a0, even_msg
    jal print_string

end_program:
    # 5. Safely exit
    jal exit_program
    
.include "21Q1.asm"