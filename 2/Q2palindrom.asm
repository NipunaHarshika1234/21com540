.data
    prompt:       .asciiz "Enter an integer to check palindrome: "
    pal_msg:      .asciiz "Palindrome\n"
    not_pal_msg:  .asciiz "Not Palindrome\n"

.text
.globl main
main:
    # 1. Prompt and read an integer
    la $a0, prompt
    jal prompt_read_int
    move $t0, $v0        # $t0 = Original Number

    # 2. Reverse the integer logic
    move $t1, $t0        # $t1 = working copy
    li $t2, 0            # $t2 = reversed number register

rev_loop:
    beq $t1, $zero, check_match
    li $t3, 10
    div $t1, $t3         # Divide working number by 10
    mflo $t1             # Shift number right (remove last digit)
    mfhi $t4             # $t4 = extracted remainder digit

    mul $t2, $t2, $t3    # reverse = reverse * 10
    add $t2, $t2, $t4    # reverse = reverse + digit
    j rev_loop

check_match:
    beq $t0, $t2, is_palindrome

    # If not equal
    la $a0, not_pal_msg
    jal print_string
    j end_q2

is_palindrome:
    la $a0, pal_msg
    jal print_string

end_q2:
    jal exit_program

# Include utility blocks
.include "Q1_util.asm"