.data
array: .word 55, 27, 13, 5, 44, 32, 17, 36
size:  .word 8

message1: .asciiz "Array elements: "
message2: .asciiz "\nMaximum value: "
message3: .asciiz "\nSummation: "
space:    .asciiz " "

.text
.globl main

main:

    li $v0, 4
    la $a0, message1
    syscall

    la $a0, array
    lw $a1, size
    jal printArray

    la $a0, array
    lw $a1, size
    jal findMax

    li $v0, 4
    la $a0, message2
    syscall

    move $a0, $v1
    li $v0, 1
    syscall

    la $a0, array
    lw $a1, size
    jal findSum

    li $v0, 4
    la $a0, message3
    syscall

    move $a0, $v1
    li $v0, 1
    syscall

    li $v0, 10
    syscall


printArray:

    move $t0, $a0 
    move $t1, $a1 
    li $t2, 0 

printLoop:

     beq $t2, $t1, endPrint

     lw $t3, 0($t0)

     li $v0, 1
     move $a0, $t3
     syscall


     li $v0, 4
     la $a0, space
     syscall

     addi $t0, $t0, 4
     addi $t2, $t2, 1

j printLoop

endPrint:
   jr $ra


findMax:

   move $t0, $a0
   move $t1, $a1

   lw $t2, 0($t0) 

   li $t3, 1

maxLoop:

   beq $t3, $t1, endMax

   addi $t0, $t0, 4
   lw $t4, 0($t0)

   ble $t4, $t2, skip

   move $t2, $t4

skip:
   addi $t3, $t3, 1
   j maxLoop

endMax:
   move $v1, $t2
   jr $ra


findSum:

   move $t0, $a0
   move $t1, $a1

   li $t2, 0 
   li $t3, 0 

sumLoop:

   beq $t3, $t1, endSum

   lw $t4, 0($t0)

   add $t2, $t2, $t4

   addi $t0, $t0, 4
   addi $t3, $t3, 1

j sumLoop
endSum:
 move $v1, $t2
 jr $ra