.data
 prompt1: .asciiz "\nEnter a number :"
 prompt2: .asciiz "Enter a second number :"
 prompt3: .asciiz "Entar a third number :"
 msgSmall: .asciiz "\nSmallest of three number is : "

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
 la $a0, prompt3
 syscall
 li $v0, 5
 syscall
 move $t2, $v0
 
 move $s0, $t0
 
 ble $s0, $t1, check_third
 move $s0, $t1
 
check_third:
 ble $s0, $t2, print_result
 move $s0, $t2
 
print_result:
 li $v0, 4
 la $a0, msgSmall
 syscall
 
 move $a0, $s0
 li $v0, 1
 syscall
 
 li $v0, 10
 syscall