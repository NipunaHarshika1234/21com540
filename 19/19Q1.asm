.data
 prompt1: .asciiz "\nEnter First single digit : "
 prompt2: .asciiz "Enter second single digit"
 msgSum: .asciiz "\nSum :"
 msgSub: .asciiz "\nSub :"
 msgMul: .asciiz "\nMul :"
 msgDiv: .asciiz "\nDiv :"
 
.text
.globl main

main:
 li $v0, 4
 la $a0, prompt1
 syscall
 li $v0, 5
 syscall
 move $t0, $v0
 
 li $v0, 4
 la $a0, prompt2
 syscall
 li $v0, 5
 syscall
 move $t1, $v0
 
 li $v0, 4
 la $a0, msgSum
 syscall
 add $a0, $t0, $t1
 li $v0, 1
 syscall
 
 li $v0, 4
 la $a0, msgSub
 syscall
 sub $a0, $t0, $t1
 li $v0, 1
 syscall
 
 li $v0, 4
 la $a0, msgMul
 syscall
 mul $a0, $t0, $t1
 li $v0, 1
 syscall
 
 li $v0, 4
 la $a0, msgDiv
 syscall
 div $t0, $t1
 mflo $a0
 li $v0, 1
 syscall
 
 li $v0, 10
 syscall
 
 