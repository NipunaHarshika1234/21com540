.data
    prompt: .asciiz "Enter the radius of the circle: "
    result: .asciiz "The area of the circle is: "
    pi:     .float 3.14159265

.text
.globl main
main:
    # 1. Print prompt
    li $v0, 4
    la $a0, prompt
    syscall

    # 2. Read float input (radius) -> saved into $f0
    li $v0, 6
    syscall              # $f0 now contains radius 'r'

    # 3. Calculate r^2
    mul.s $f1, $f0, $f0  # $f1 = r * r

    # 4. Load PI constant and calculate Area
    lwc1 $f2, pi         # $f2 = 3.14159265
    mul.s $f1, $f1, $f2  # $f1 = pi * r^2

    # 5. Print result string
    li $v0, 4
    la $a0, result
    syscall

    # 6. Print float result
    li $v0, 2
    mov.s $f12, $f1      # Move result to $f12 for printing
    syscall

    # 7. Exit program
    li $v0, 10
    syscall