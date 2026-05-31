# File Name: Q4.asm

.data
prompt_num1:    .asciiz "Enter Number 1: "
prompt_num2:    .asciiz "Enter Number 2: "
menu_title:     .asciiz "Menu\n"
menu_add:       .asciiz "  1 - Addition\n"
menu_sub:       .asciiz "  2 - Subtraction\n"
menu_mul:       .asciiz "  3 - Multiplication\n"
menu_div:       .asciiz "  4 - Division (Quotient)\n"
menu_mod:       .asciiz "  5 - Modulus (Reminder)\n"
prompt_op:      .asciiz "Select the operation from the Menu: "
err_div_zero:   .asciiz "Error: Division by zero is undefined.\n"

# Mathematical operator symbol strings
sym_add:        .asciiz " + "
sym_sub:        .asciiz " - "
sym_mul:        .asciiz " x "
sym_div:        .asciiz " / "
sym_mod:        .asciiz " % "
sym_equal:      .asciiz " = "
newline:        .asciiz "\n"

.text
.globl main
main:
    # 1. Prompt and input Number 1
    li $v0, 4
    la $a0, prompt_num1
    syscall
    li $v0, 5
    syscall
    move $s0, $v0               # $s0 = Number 1

    # 2. Prompt and input Number 2
    li $v0, 4
    la $a0, prompt_num2
    syscall
    li $v0, 5
    syscall
    move $s1, $v0               # $s1 = Number 2

    # 3. Print the Operations Menu
    li $v0, 4
    la $a0, menu_title
    syscall
    la $a0, menu_add
    syscall
    la $a0, menu_sub
    syscall
    la $a0, menu_mul
    syscall
    la $a0, menu_div
    syscall
    la $a0, menu_mod
    syscall

    # 4. Prompt and input operation selection code
    la $a0, prompt_op
    syscall
    li $v0, 5
    syscall
    move $s2, $v0               # $s2 = operation code (1 to 5)

    # 5. Pre-check for Division/Modulus by zero errors
    li $t0, 4
    blt $s2, $t0, process_op    # Skip check if operation is less than 4 (Addition/Subtraction/Multiplication)
    beq $s1, $zero, div_zero_err # If denominator is 0 and operation >= 4, raise error

process_op:
    # Branching logic for calculation types
    li $t0, 1
    beq $s2, $t0, do_add
    li $t0, 2
    beq $s2, $t0, do_sub
    li $t0, 3
    beq $s2, $t0, do_mul
    li $t0, 4
    beq $s2, $t0, do_div
    li $t0, 5
    beq $s2, $t0, do_mod
    j exit                      # Unsupported instruction handles clean exit

do_add:
    add $s3, $s0, $s1           # $s3 = $s0 + $s1
    la $s4, sym_add             # Set operation string character symbol representation
    j print_result

do_sub:
    sub $s3, $s0, $s1           # $s3 = $s0 - $s1
    la $s4, sym_sub
    j print_result

do_mul:
    mul $s3, $s0, $s1           # $s3 = $s0 * $s1
    la $s4, sym_mul
    j print_result

do_div:
    div $s0, $s1                # Divide $s0 by $s1
    mflo $s3                    # Move Quotient result to $s3
    la $s4, sym_div
    j print_result

do_mod:
    div $s0, $s1                # Divide $s0 by $s1
    mfhi $s3                    # Move Remainder result to $s3
    la $s4, sym_mod

print_result:
    # Output structure: [Num1][Operator][Num2][ = ][Result]
    li $v0, 1
    move $a0, $s0               # Print Number 1
    syscall

    li $v0, 4
    move $a0, $s4               # Print dynamic mathematical operator symbol
    syscall

    li $v0, 1
    move $a0, $s1               # Print Number 2
    syscall

    li $v0, 4
    la $a0, sym_equal           # Print " = "
    syscall

    li $v0, 1
    move $a0, $s3               # Print the final calculated solution
    syscall

    li $v0, 4
    la $a0, newline             # Line break
    syscall
    j exit

div_zero_err:
    li $v0, 4
    la $a0, err_div_zero        # Output safe handling notification
    syscall

exit:
    li $v0, 10                  # Graceful program termination
    syscall