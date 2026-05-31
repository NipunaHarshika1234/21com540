.data
 p1: .asciiz "\nInput integer number :"
 result: .asciiz "\nFactorial is :"
 
.text
.globl main

main:
 li $v0, 4
 la $a0, p1
 syscall
 li $v0, 5
 syscall
 
 move $t0, $v0
 
 li $t1, 1
 
fact_loop: 
 blez $t0, print_res
 mul $t1, $t1, $t0
 addi $t0, $t0, -1
 j fact_loop
 
print_res:
 li $v0, 4
 la $a0, result
 syscall
 
 move $a0, $t1
 li $v0, 1
 syscall
 
 li $v0, 10
 syscall
 
