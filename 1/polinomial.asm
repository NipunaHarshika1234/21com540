.data
    prompt: .asciiz "Enter the value of x: "
    result: .asciiz "The value of the polynomial P is: "

.text
.globl main
main:
    # 1. Print prompt
    li $v0, 4
    la $a0, prompt
    syscall

    # 2. Read integer input x
    li $v0, 5
    syscall
    move $t0, $v0        # $t0 = x

    # 3. Compute 3x^2
    mul $t1, $t0, $t0    # $t1 = x^2
    li $t2, 3
    mul $t1, $t1, $t2    # $t1 = 3 * x^2

    # 4. Compute 5x
    li $t2, 5
    mul $t2, $t2, $t0    # $t2 = 5 * x

    # 5. Compute 3x^2 + 5x - 7
    add $t1, $t1, $t2    # $t1 = 3x^2 + 5x
    subi $t1, $t1, 7     # $t1 = 3x^2 + 5x - 7

    # 6. Print result string
    li $v0, 4
    la $a0, result
    syscall

    # 7. Print calculation result
    li $v0, 1
    move $a0, $t1
    syscall

    # 8. Exit program
    li $v0, 10
    syscall