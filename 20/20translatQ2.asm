.data 
 miles: .asciiz "Enter the number of miles diven : "
 gallons: .asciiz "Enter the number of gallons used : "
 mpg: .asciiz "Your mpg = "

.text
.globl main
main:
 li $v0, 4
 la $a0, miles
 syscall
 li $v0, 5
 syscall
 
 move $t0, $v0
 
 li $v0, 4
 la $a0, gallons
 syscall
 li $v0, 5
 syscall
 move $t1, $v0
 
 div $t0, $t1
 mflo $t2
 
 li $v0, 4
 la $a0, mpg
 syscall
 
 li $v0, 1
 move $a0, $t2
 syscall
 
 li $v0, 10
 syscall
 
 