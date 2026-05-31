# Save this file strictly as: Q1_util.asm

.text

# a. Exit from program
exit_program:
    li $v0, 10
    syscall

# b. Print a String (Address of string must be in $a0)
print_string:
    li $v0, 4
    syscall
    jr $ra

# c. Prompt and read an Integer (Address of prompt string must be in $a0)
prompt_read_int:
    li $v0, 4
    syscall              # Print prompt
    li $v0, 5
    syscall              # Read Integer into $v0
    jr $ra

# d. Print an Integer (Integer value must be in $a0)
print_int:
    li $v0, 1
    syscall
    jr $ra

# e. Extract a digit from an Integer
# Arguments: $a0 = Number, $a1 = Position (0 for units, 1 for tens, 2 for hundreds, etc.)
# Returns: $v0 = extracted digit value
extract_digit:
    li $t3, 1            # Multiplier loop base
    li $t4, 10           # Base 10 divisor
    move $t5, $zero      # Loop counter

scale_loop:
    beq $t5, $a1, extract_now
    mul $t3, $t3, $t4
    addi $t5, $t5, 1
    j scale_loop

extract_now:
    div $a0, $t3         # Divide number by 10^position
    mflo $t6             # $t6 = parsed quotient
    div $t6, $t4         # Quotient divided by 10
    mfhi $v0             # Remainder is the exact target digit
    jr $ra