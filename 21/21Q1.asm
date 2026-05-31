# Q1_util.asm
# This file contains utility subprograms for common I/O tasks.

.data
    tab_char: .asciiz "\t"    # Stores a tab character for part (e)

.text
# Make subprograms globally accessible to other files
.globl exit_program
.globl print_string
.globl prompt_integer
.globl print_integer
.globl print_tab

# -------------------------------------------------------------
# a) Subprogram to exit from an assembly program
# -------------------------------------------------------------
exit_program:
    li $v0, 10          # System call code 10 = exit program
    syscall             # Execute exit

# -------------------------------------------------------------
# b) Subprogram to print a String
# Input: $a0 must contain the memory address of the string
# -------------------------------------------------------------
print_string:
    li $v0, 4           # System call code 4 = print string
    syscall             # Execute print
    jr $ra              # Return to the caller

# -------------------------------------------------------------
# c) Subprogram to prompt/read an Integer
# Output: Returns the integer entered by the user in $v0
# -------------------------------------------------------------
prompt_integer:
    li $v0, 5           # System call code 5 = read integer
    syscall             # Execute read (result is automatically stored in $v0)
    jr $ra              # Return to the caller

# -------------------------------------------------------------
# d) Subprogram to print an Integer
# Input: $a0 must contain the integer value to print
# -------------------------------------------------------------
print_integer:
    li $v0, 1           # System call code 1 = print integer
    syscall             # Execute print
    jr $ra              # Return to the caller

# -------------------------------------------------------------
# e) Subprogram to print a Tab
# -------------------------------------------------------------
print_tab:
    la $a0, tab_char    # Load address of the tab string into $a0
    li $v0, 4           # System call code 4 = print string
    syscall             # Execute print
    jr $ra              # Return to the caller