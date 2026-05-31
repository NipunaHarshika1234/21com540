.data
 fact_msg: .asciiz "Enter Integer number : "
 
.text
.globl main

main:
 la $a0, fact_msg
 li $v0, 4
 syscall

 li $v0, 5
 syscall
 
 move $t0, $v0
 
 li $t1, 1
 
factorial_loop:
 blez $t0, loop_end
 
 mul $t1, $t1, $t0
 
 subi $t0, $t0, 1
 
 j factorial_loop
 
loop_end:
 
 move $a0, $t1
 li $v0, 1
 syscall
 
 li $v0, 10
 syscall