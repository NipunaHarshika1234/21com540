.data
 .align 2
 arry: .word 100, 10, 3, -2, 110, 0, -5, 150, -17, 8
 size: .word 10
 result_msg: .asciiz "The smallest number is :"
 
.text
.globl main

main: 
 la $t0, arry
 lw $t1, size
 
 lw $t2, 0($t0)
 
 addi $t0, $t0, 4
 subi $t1, $t1, 1
 
array_loop:
 beq $t1, $zero, loop_done
 
 lw $t3, 0($t0)
 
 bge $t3, $t2, skip_update
 
 move $t2, $t3
 
skip_update:
 addi $t0, $t0, 4
 subi $t1, $t1, 1
 j array_loop
 
loop_done:
 la $a0, result_msg
 li $v0, 4
 syscall
 
 move $a0, $t2
 li $v0, 1
 syscall
 
 li $v0, 10
 syscall