.data
    prompt: .asciiz "Enter temperature in Celsius: "
    result: .asciiz "Temperature in Fahrenheit: "

.text
.globl main
main:
    # 1. Print prompt
    li $v0, 4
    la $a0, prompt
    syscall

    # 2. Read Celsius integer input
    li $v0, 5
    syscall
    move $t0, $v0        # $t0 = C

    # 3. Perform calculation: F = (C * 9 / 5) + 32
    li $t1, 9
    mul $t0, $t0, $t1    # $t0 = C * 9
    
    li $t2, 5
    div $t0, $t0, $t2    # $t0 = (C * 9) / 5
    
    addi $t0, $t0, 32    # $t0 = ((C * 9) / 5) + 32

    # 4. Print result string
    li $v0, 4
    la $a0, result
    syscall

    # 5. Print final Fahrenheit integer
    li $v0, 1
    move $a0, $t0
    syscall

    # 6. Exit program
    li $v0, 10
    syscall