.data
 space: .byte ' '
 
.text
main:
 li $t0, 9
 
loop:
 move $a0, $t0
 li $v0, 1
 syscall
 
 li $v0, 11
 lbu $a0, space
 syscall
 
 addi $t0, $t0, -1
 
 bgtz $t0, loop
 
 li $v0, 10
 syscall
 