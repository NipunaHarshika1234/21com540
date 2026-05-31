.data

# File Name: Q1_util.asm
# Description: Utility subprograms for common I/O operations

.text
# ---------------------------------------------------------
# a) Exit from an assembly program
# ---------------------------------------------------------
.globl exit_program
exit_program:
    li $v0, 10          # Syscall code 10: Exit program
    syscall

# ---------------------------------------------------------
# b) Print a String
# Arguments: $a0 = address of the null-terminated string
# ---------------------------------------------------------
.globl print_string
print_string:
    li $v0, 4           # Syscall code 4: Print string
    syscall
    jr $ra              # Return to caller

# ---------------------------------------------------------
# c) Prompt / Read an Integer
# Arguments: $a0 = address of the prompt string
# Returns:   $v0 = integer read from user
# ---------------------------------------------------------
.globl prompt_integer
prompt_integer:
    # Save $ra because we are calling another subprogram inside
    addi $sp, $sp, -4   
    sw $ra, 0($sp)      
    
    jal print_string    # Print the prompt message passed in $a0
    
    li $v0, 5           # Syscall code 5: Read integer
    syscall             # The integer is now stored in $v0
    
    lw $ra, 0($sp)      # Restore return address
    addi $sp, $sp, 4    
    jr $ra              # Return to caller

# ---------------------------------------------------------
# d) Print an Integer
# Arguments: $a0 = integer value to print
# ---------------------------------------------------------
.globl print_integer
print_integer:
    li $v0, 1           # Syscall code 1: Print integer
    syscall
    jr $ra              # Return to caller

# ---------------------------------------------------------
# e) Print a Tab character
# ---------------------------------------------------------
.globl print_tab
print_tab:
    li $v0, 11          # Syscall code 11: Print character
    li $a0, 9           # ASCII value 9 is a Tab character ('\t')
    syscall
    jr $ra              # Return to caller
